/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package jahmm;

import static jahmm.HmmBase.generatePi;
import jahmm.calculators.InputForwardBackwardCalculator;
import jahmm.calculators.InputForwardBackwardCalculatorBase;
import jahmm.calculators.InputForwardBackwardScaledCalculatorBase;
import jahmm.observables.InputObservationTuple;
import jahmm.observables.Observation;
import jahmm.observables.Opdf;
import jahmm.observables.OpdfFactory;
import jahmm.toolbox.InputMarkovGeneratorBase;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import jutils.ArrayUtils;
import jutils.Tagable;
import jutils.collections.CollectionUtils;
import jutlis.lists.ListArray;

/**
 * An implementation of an Input Hidden Markov Model as formulated by Falko
 * Bause. Falko Bause defines in "Input Output Hidden Markov Models: for the
 * Aggregation of Performance Models" an Input Hidden Markov Model: a Markov
 * Model where in each state the next state and the observation depends on an
 * input from a finite domain.
 *
 * @param <TIn> The type of input of the InputHmm.
 * @param <TObs> The type of observations of the InputHmm.
 * @note The A matrix has the following structure: A_{i,j,k} means the
 * probability of moving from state i to j given input k.
 */
public class InputHmmBase<TObs extends Observation, TIn> extends HmmBase<TObs, double[][][], Object[][], InputObservationTuple<TIn, TObs>, InputHmmBase<TObs, TIn>> implements InputHmm<TObs, TIn, InputHmmBase<TObs, TIn>> {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(InputHmmBase.class.getName());

    /**
     * Generates a clone of the given A-matrix.
     *
     * @param a The original A-matrix to make a clone from.
     * @return A new A-matrix that contains all the elements of the original
     * A-matrix.
     * @throws IllegalArgumentException If the given matrix is not effective.
     * @throws IllegalArgumentException If one of the rows of the given matrix
     * is not effective.
     * @throws IllegalArgumentException If the matrix contains no rows.
     * @throws IllegalArgumentException If the matrix contains no columns.
     * @throws IllegalArgumentException If some of the rows of the given matrix
     * have a different length.
     * @throws IllegalArgumentException If the matrix is not square (the number
     * of elements in each column is not equal to the number of rows).
     */
    protected static double[][][] cloneA(double[][][] a) throws IllegalArgumentException {
        if (a == null) {
            throw new IllegalArgumentException("'A' is not effective.");
        }
        int n = a.length;
        if (a.length <= 0x00) {
            throw new IllegalArgumentException("'A' must contain at least one row.");
        }
        double[][] row = a[0x00];
        if (row == null) {
            throw new IllegalArgumentException("All rows of 'A' must be effective.");
        }
        int m = a[0x00].length;
        if (m <= 0x00) {
            throw new IllegalArgumentException("'A' must contain at least one column.");
        }
        double[][][] clone = new double[n][m][];
        double[] column;
        for (int i = 0; i < n; i++) {
            row = a[i];
            if (row == null) {
                throw new IllegalArgumentException("All rows of 'A' must be effective.");
            }
            if (a[i].length != m) {
                throw new IllegalArgumentException("'A' is not a consistent matrix.");
            }
            for (int j = 0; j < m; j++) {
                column = row[j];
                if (column == null) {
                    throw new IllegalArgumentException("'A' contains a column that is not effective.");
                }
                if (a[i][j].length != n) {
                    throw new IllegalArgumentException("'A' is not a square matrix.");
                }
                clone[i][j] = a[i][j].clone();
            }
        }
        return clone;
    }

    /**
     * Generates a new A-matrix based on the given number of symbols and the
     * given number of states.
     *
     * @param nbSymbols The given number of symbols.
     * @param nbStates The given number of states.
     * @return An A-matrix with dimensions nbStates x nbSymbols x nbStates where
     * for each (state,input) couple, the values are uniformly distributed over
     * the several end states.
     * @throws IllegalArgumentException if the given number of states is smaller
     * than one.
     * @throws IllegalArgumentException if the given number of symbols is
     * smaller than one.
     */
    protected static double[][][] generateA(int nbSymbols, int nbStates) throws IllegalArgumentException {
        if (nbSymbols >= 0x01 && nbStates >= 0x01) {
            double[][][] a = new double[nbStates][nbSymbols][nbStates];
            double inv = 1.0d / nbStates;
            for (int i = 0x00; i < nbStates; i++) {
                for (int j = 0x00; j < nbSymbols; j++) {
                    for (int k = 0x00; k < nbStates; k++) {
                        a[i][j][k] = inv;
                    }
                }
            }
            return a;
        } else {
            throw new IllegalArgumentException("The number of states and symbols must both be larger or equal to one.");
        }
    }

    protected static <TObs extends Observation> Object[][] generateB(int nbStates, int nbSymbols, OpdfFactory<? extends Opdf<TObs>> opdfFactory) {
        Object[][] b = new Object[nbStates][nbSymbols];
        for (int i = 0x00; i < nbStates; i++) {
            for (int j = 0x00; j < nbSymbols; j++) {
                b[i][j] = opdfFactory.generate();
            }
        }
        return b;
    }

    protected static <TObs extends Observation> Object[][] generateB(int nbStates, int nbSymbols, Iterable<? extends Iterable<? extends Opdf<TObs>>> opdfs) throws CloneNotSupportedException {
        Object[][] b = new Object[nbStates][nbSymbols];
        Iterator<? extends Iterable<? extends Opdf<TObs>>> opdfsi = opdfs.iterator();
        for (int i = 0x00; i < nbStates && opdfsi.hasNext(); i++) {
            Iterator<? extends Opdf<TObs>> opdfsj = opdfsi.next().iterator();
            for (int j = 0x00; j < nbSymbols && opdfsj.hasNext(); j++) {
                b[i][j] = opdfsj.next();
            }
        }
        return b;
    }

    @SuppressWarnings("unchecked")
    protected static <TObs extends Observation> Object[][] cloneB(Object[][] original) throws CloneNotSupportedException {
        int m = original.length;
        int n = original[0x00].length;
        Object[][] b = new Object[m][n];
        for (int i = 0x00; i < m; i++) {
            for (int j = 0x00; j < n; j++) {
                b[i][j] = ((Opdf<TObs>) original[i][j]).clone();
            }
        }
        return b;

    }

    private final HashMap<TIn, Integer> indexRegister = new HashMap<>();

    /**
     * Creates a new IHMM. Each state has the same <i>pi</i> value and the
     * transition probabilities are all equal.
     *
     * @param nbStates The (strictly positive) number of states of the IHMM.
     * @param opdfFactory A pdf generator that is used to build the pdfs
     * associated to each state.
     * @param possibleInput The possible input of that may occur.
     */
    public InputHmmBase(int nbStates, OpdfFactory<? extends Opdf<TObs>> opdfFactory, Iterable<TIn> possibleInput) {
        super(generatePi(nbStates), generateA(CollectionUtils.size(possibleInput), nbStates), generateB(nbStates, CollectionUtils.size(possibleInput), opdfFactory));
        generateInputIndices(possibleInput);
        this.checkConstraints();
    }

    @SuppressWarnings("unchecked")
    public InputHmmBase(int nbStates, OpdfFactory<? extends Opdf<TObs>> opdfFactory, TIn... possibleInputs) {
        this(nbStates, opdfFactory, new ListArray<>(possibleInputs));
    }

    public InputHmmBase(double[] pi, double[][][] a, Iterable<? extends Iterable<? extends Opdf<TObs>>> opdfs, Iterable<TIn> possibleInput) throws CloneNotSupportedException {
        super(pi.clone(), cloneA(a), generateB(a.length, CollectionUtils.size(possibleInput), opdfs));
        this.generateInputIndices(possibleInput);
        this.checkConstraints();
    }

    /**
     * Creates a new IHMM. All the HMM parameters are given as arguments.
     *
     * @param pi The initial probability values.  <code>pi[i]</code> is the
     * initial probability of state <code>i</code>. This array is copied.
     * @param a The state transition probability array. <code>a[i][j][k]</code>
     * is the probability of going from state <code>k</code> given input symbol
     * <code>j</code> to state <code>j</code>. This array is copied.
     * @param opdfs The observation distributions.  <code>opdfs.get(i)</code> is
     * the observation distribution associated with state <code>i</code>. The
     * distributions are not copied.
     * @param possibleInput The possible input that may occur
     * @throws java.lang.CloneNotSupportedException
     */
    protected InputHmmBase(double[] pi, double[][][] a, Object[][] opdfs, Map<TIn, Integer> possibleInput) throws CloneNotSupportedException {
        super(pi.clone(), cloneA(a), cloneB(opdfs));
        this.checkConstraints();
        CollectionUtils.putAll(this.indexRegister, possibleInput);
    }

    private void generateInputIndices(Iterable<TIn> possibleInput) {
        this.indexRegister.clear();
        int i = 0x00;
        for (TIn inp : possibleInput) {
            this.indexRegister.put(inp, i);
            i++;
        }
    }

    private void checkConstraints() {
        if (a.length == 0 || pi.length != a.length || b.length != a.length || b[0x00].length != a[0x00].length) {
            throw new IllegalArgumentException("Wrong dimensions");
        }
    }

    public Map<TIn, Integer> getIndexRegister() {
        return Collections.unmodifiableMap(this.indexRegister);
    }

    /**
     * Returns the number of states of this HMM.
     *
     * @return The number of states of this HMM.
     */
    @Override
    public int nbStates() {
        return a.length;
    }

    /**
     * Returns the number of symbols of this IHMM.
     *
     * @return The number of symbols of this IHMM.
     */
    @Override
    public int nbSymbols() {
        return a[0x00].length;
    }

    /**
     * Creates a duplicate object of the given input Hidden Markov Model.
     *
     * @return An IHHM that contains the same date as this object.
     * @throws CloneNotSupportedException An exception such that classes lower
     * in the hierarchy can fail to clone.
     */
    @Override
    public InputHmmBase<TObs, TIn> clone() throws CloneNotSupportedException {
        return new InputHmmBase<>(this.pi, this.a, this.b, this.indexRegister);
    }

    /**
     * Returns the probability associated with the transition going from state
     * <i>i</i> to state <i>j</i> (<i>a<sub>i,j</sub></i>).
     *
     * @param i The first state number such that
     * <code>0 &le; i &lt; nbStates()</code>.
     * @param j The second state number such that
     * <code>0 &le; j &lt; nbStates()</code>.
     * @return The probability associated to the transition going from
     * <code>i</code> to state <code>j</code> regardless of the input.
     */
    @Override
    public double getAij(int i, int j) {
        double total = 0.0d;
        double[][] row = this.a[i];
        int n = row.length;
        for (int k = 0x00; k < n; k++) {
            total += row[k][j];
        }
        return total;
    }

    /**
     * Returns the probability associated with the transition going from state
     * <i>i</i> to state <i>j</i> (<i>a<sub>i,j</sub></i>).
     *
     * @param i The first state number such that
     * <code>0 &le; i &lt; nbStates()</code>.
     * @param j The second state number such that
     * <code>0 &le; j &lt; nbStates()</code>.
     * @param k The input symbol such that
     * <code>0 &le; k &lt; nbSymbols()</code>.
     * @return The probability associated to the transition going from
     * <code>i</code> to state <code>j</code>.
     */
    public double getAij(int i, int k, int j) {
        return a[i][k][j];
    }

    /**
     * Gives a description of this IHMM.
     *
     * @return A textual description of this IHMM.
     */
    @Override
    public String toString() {
        return toString(NumberFormat.getInstance());
    }

    /**
     * Gives a description of this HMM.
     *
     * @param nf A number formatter used to print numbers (e.g. Aij values).
     * @return A textual description of this HMM.
     */
    @Override
    public String toString(NumberFormat nf) {
        String s = "HMM with " + nbStates() + " state(s)\n";

        for (int i = 0; i < nbStates(); i++) {
            s += "\nState " + i + "\n";
            s += " Pi: " + getPi(i) + "\n";
            s += " Aij:";

            for (int j = 0; j < nbStates(); j++) {
                s += " " + nf.format(getAij(i, j));
            }
            s += "\n";

            s += " Opdf: ";//TODO+ getOpdf(i).toString(nf) + "\n";
        }

        return s;
    }

    @Override
    public void fold(int n) {
        int m = pi.length;
        double[] pia = new double[m], pib = this.pi, tmp;
        double[][] colA = this.collapsedA();
        double val;
        for (int t = 0x00; t < n; t++) {
            tmp = pia;
            pia = pib;
            pib = tmp;
            for (int i = 0x00; i < m; i++) {
                val = 0.0d;
                for (int j = 0x00; j < m; j++) {
                    val += colA[j][i] * pia[j];
                }
                pib[i] = val;
            }
        }
        if ((n & 0x01) != 0x00) {
            System.arraycopy(pib, 0, this.pi, 0, m);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Opdf<TObs> getOpdf(int stateNb, int symbolNb) {
        return (Opdf<TObs>) this.b[stateNb][symbolNb];
    }

    @Override
    public int[] mostLikelyStateSequence(List<? extends InputObservationTuple<TIn, TObs>> oseq) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double probability(List<? extends InputObservationTuple<TIn, TObs>> oseq, int[] sseq) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void splitInput(final TIn originalIn, final TIn... newIns) {
        if (originalIn != null && newIns != null && newIns.length > 0x00) {
            if (newIns.length > 0x00) {
                Integer originalIndex = this.indexRegister.remove(originalIn);
                CollectionUtils.incrementValueByIndex(this.indexRegister, new ListArray<>(originalIndex), -0x01);
                if (originalIndex != null) {
                    final int origix = originalIndex;
                    final int offset = this.indexRegister.size();
                    int idxPtr = offset;
                    for (TIn newIn : newIns) {
                        if (!this.indexRegister.containsKey(newIn)) {
                            this.indexRegister.put(newIn, idxPtr);
                            idxPtr++;
                        }
                    }
                    for (int i = 0x00; i < a.length; i++) {
                        double[][] ai = this.a[i];
                        double[] source = ai[origix];
                        int nSource = source.length;
                        double[][] newa = new double[idxPtr][];
                        for (int j = 0x00; j < origix; j++) {
                            newa[j] = ai[j];
                        }
                        for (int j = origix; j < offset; j++) {
                            newa[j] = ai[j + 0x01];
                        }
                        newa[offset] = source;
                        for (int j = offset + 0x01; j < idxPtr; j++) {
                            newa[j] = Arrays.copyOf(source, nSource);
                        }
                        this.a[i] = newa;
                    }
                } else {
                    throw new UnsupportedOperationException("Cannot split a currently unknown input.");
                }
            } else {
                CollectionUtils.replaceKey(indexRegister, originalIn, newIns[0x00]);
            }
        } else {
            throw new IllegalArgumentException("Cannot split to zero branches.");
        }
    }

    @Override
    public void mergeInput(final TIn newIn, final TIn... originalIns) {
        if (newIn != null && originalIns != null && originalIns.length > 0x00) {
            if (originalIns.length > 0x01) {
                final ListArray<TIn> originalList = new ListArray<>(originalIns);
                final ArrayList<Integer> ids = new ArrayList<>(originalIns.length);
                CollectionUtils.removeAll(this.indexRegister, originalList, ids);
                Collections.sort(ids);
                final double scale = 1.0d / ids.size();
                final int first = ids.get(0x00);
                final List<Integer> subl = ids.subList(0x01, ids.size());
                CollectionUtils.incrementValueByIndex(this.indexRegister, subl, -0x01);
                this.indexRegister.put(newIn, first);
                final int nState = this.indexRegister.size();
                //TODO weight by Pi?
                for (int i = 0x00; i < a.length; i++) {
                    double[][] ai = a[i];
                    double[] aif = ai[first];
                    for (int snd : subl) {
                        double[] ais = ai[snd];
                        for (int j = 0x00; j < ais.length; j++) {
                            aif[j] += ais[j];
                        }
                    }
                    for (int j = 0x00; j < aif.length; j++) {
                        aif[j] *= scale;
                    }
                    double[][] newa = new double[nState][];
                    ArrayUtils.copySkipArray(newa, ai, subl);
                    a[i] = newa;
                }
            } else {
                CollectionUtils.replaceKey(this.indexRegister, originalIns[0x00], newIn);
            }
        } else {
            throw new IllegalArgumentException("Cannot merge zero branches.");
        }
    }

    @Override
    public int getInputIndex(TIn x) {
        return this.indexRegister.get(x);
    }

    @Override
    public double getAixj(int i, TIn x, int j) {
        return this.getAixj(i, this.getInputIndex(x), j);
    }

    @Override
    public double getAixj(int i, Tagable<TIn> x, int j) {
        return this.getAixj(i, this.getInputIndex(x), j);
    }

    @Override
    public int getInputIndex(Tagable<TIn> input) {
        return this.getInputIndex(input.getTag());
    }

    @Override
    public double getAixj(int i, int x, int j) {
        return this.a[i][x][j];
    }

    @Override
    public void setAixj(int i, TIn x, int j, double aixj) {
        this.setAixj(i, this.getInputIndex(x), j, aixj);
    }

    @Override
    public void setAixj(int i, int x, int j, double aixj) {
        this.a[i][x][j] = aixj;
    }

    @Override
    public void setAixj(int i, Tagable<TIn> x, int j, double aixj) {
        this.setAixj(i, this.getInputIndex(x), j, aixj);
    }

    @Override
    public Opdf<TObs> getOpdf(int stateNb, TIn inputNb) {
        return this.getOpdf(stateNb, this.getInputIndex(inputNb));
    }

    @Override
    public Opdf<TObs> getOpdf(int stateNb, Tagable<TIn> inputNb) {
        return this.getOpdf(stateNb, this.getInputIndex(inputNb.getTag()));
    }

    /**
     * Creates a A-matrix independent of the input.
     *
     * @return An A-matrix given the input is uniformly distributed.
     * @note The input is assumed to be uniformly distributed.
     */
    @Override
    public double[][] collapsedA() {
        int N = this.nbStates();
        int M = this.nbSymbols();
        double[][] colA = new double[N][N];
        for (int i = 0x00; i < N; i++) {
            for (int k = 0x00; k < N; k++) {
                double val = 0.0d;
                for (int j = 0x00; j < M; j++) {
                    val += this.a[i][j][k];
                }
                colA[i][k] = val;
            }
        }
        return colA;
    }

    @Override
    public void fold(TIn input) {
        int m = pi.length;
        double[] pib = new double[m], pia = this.pi;
        int k = this.getInputIndex(input);
        double val;
        double[][] cola = new double[m][];
        for (int j = 0x00; j < m; j++) {
            cola[j] = this.a[j][k];
        }
        for (int i = 0x00; i < m; i++) {
            val = 0.0d;
            for (int j = 0x00; j < m; j++) {
                val += cola[j][i] * pia[j];
            }
            pib[i] = val;
        }
        System.arraycopy(pib, 0, this.pi, 0, m);
    }

    @Override
    public void fold(Iterable<? extends TIn> inputs) {
        int m = pi.length;
        boolean copy = false;
        int oldk = -0x01;
        double[][] cola = new double[m][];
        double[] pia = new double[m], pib = this.pi, tmp;
        double val;
        for (TIn input : inputs) {
            copy = !copy;
            int k = this.getInputIndex(input);
            tmp = pia;
            pia = pib;
            pib = tmp;
            if (oldk != k) {
                for (int j = 0x00; j < m; j++) {
                    cola[j] = this.a[j][k];
                }
                oldk = k;
            }
            for (int i = 0x00; i < m; i++) {
                val = 0.0d;
                for (int j = 0x00; j < m; j++) {
                    val += cola[j][i] * pia[j];
                }
                pib[i] = val;
            }
        }
        if (copy) {
            System.arraycopy(pib, 0, this.pi, 0, m);
        }
    }

    /**
     * Gets the set of all registered inputs.
     *
     * @return The set of all registered inputs.
     */
    @Override
    public Collection<TIn> getRegisteredInputs() {
        return this.indexRegister.keySet();
    }

    /**
     * Gets the relevant forward backward calculator for the Hidden Markov
     * Model.
     *
     * @return The relevant forward backward calculator for the Hidden Markov
     * Model.
     */
    @Override
    public InputForwardBackwardCalculator<TObs, TIn, InputHmmBase<TObs, TIn>> getForwardBackwardCalculator() {
        return InputForwardBackwardCalculatorBase.Instance;
    }

    /**
     * Gets the relevant forward backward scaled calculator for the Hidden
     * Markov Model.
     *
     * @return The relevant forward backward scaled calculator for the Hidden
     * Markov Model.
     */
    @Override
    public InputForwardBackwardCalculator<TObs, TIn, InputHmmBase<TObs, TIn>> getForwardBackwardScaledCalculator() {
        return InputForwardBackwardScaledCalculatorBase.Instance;
    }

    @Override
    public InputMarkovGeneratorBase<TObs, TIn, InputHmmBase<TObs, TIn>> getMarkovGenerator() {
        return new InputMarkovGeneratorBase<>(this);
    }

}
