/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package jahmm;

import static jahmm.HmmBase.generatePi;
import jahmm.observables.InputObservationTuple;
import jahmm.observables.Observation;
import jahmm.observables.Opdf;
import jahmm.observables.OpdfFactory;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import jutils.ArrayUtils;
import jutils.collections.CollectionUtils;
import jutlis.lists.ListArray;

/**
 * Main Input-Hmm class; it implements an Hidden Markov Model with an input
 * layer. An IHMM is composed of:
 * <ul>
 * <li><i>states</i>: each state has a given probability of being initial
 * (<i>pi</i>) and an associated observation probability function (<i>opdf</i>).
 * Each state is associated to an index; the first state is numbered 0, the last
 * n-1 (where n is the number of states in the HMM); this number is given as an
 * argument to the various functions to refer to the matching state. </li>
 * <li><i>transition probabilities</i>: that is, the probability of going from
 * state <i>i</i> to state <i>j</i> (<i>a<sub>i,j</sub></i>).</li>
 * <li><i>inputs</i>: a sequence of inputs.</li>
 * </ul>
 * <p>
 * Important objects extensively used with HMMs are {@link Observation
 * Observation}s, observation sequences and set of observation sequences. An
 * observation sequence is simply a {@link List List} of
 * {@link Observation Observation}s (in the right order, the i-th element of the
 * vector being the i-th element of the sequence). A set of observation
 * sequences is a {@link java.util.List List} of such sequences.
 *
 * @param <TIn> The type of input of the InputHmm.
 * @param <TOut> The type of observations of the InputHmm.
 * @note The A matrix has the following structure: A_{i,j,k} means the
 * probability of moving from state i to j given input k.
 */
public class InputHmmBase<TIn extends Enum<TIn>, TOut extends Observation> extends HmmBase<TOut, double[][][], ArrayList<Opdf<TOut>>, InputObservationTuple<TIn, TOut>> implements InputHmm<TIn, TOut> {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(InputHmmBase.class.getName());

    protected static double[][][] cloneA(double[][][] a) {
        int n = a.length;
        int m = a[0x00].length;
        double[][][] clone = new double[n][][];
        for (int i = 0; i < n; i++) {
            if (a[i].length != m) {
                throw new IllegalArgumentException("'A' is not a consistent matrix.");
            }
            for (int j = 0; j < m; j++) {
                if (a[i][j].length != n) {
                    throw new IllegalArgumentException("'A' is not a square matrix.");
                }
                clone[i][j] = a[i][j].clone();
            }
        }
        return clone;
    }

    protected static double[][][] generateA(int nbSymbols, int nbStates) {
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
    }
    private final HashMap<TIn, Integer> indexRegister = new HashMap<>();

    /**
     * Creates a new IHMM. Each state has the same <i>pi</i> value and the
     * transition probabilities are all equal.
     *
     * @param nbSymbols The (strictly positive) number of input symbols of the
     * IHMM.
     * @param nbStates The (strictly positive) number of states of the IHMM.
     * @param opdfFactory A pdf generator that is used to build the pdfs
     * associated to each state.
     * @param possibleInput The possible input of that may occur.
     */
    public InputHmmBase(int nbSymbols, int nbStates, OpdfFactory<? extends Opdf<TOut>> opdfFactory, Iterable<TIn> possibleInput) {
        super(generatePi(nbStates), generateA(nbSymbols, nbStates), new ArrayList<Opdf<TOut>>(nbStates));
        for (int i = 0; i < nbStates; i++) {
            b.add(opdfFactory.factor());
        }
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
     */
    public InputHmmBase(double[] pi, double[][][] a, List<? extends Opdf<TOut>> opdfs, Iterable<TIn> possibleInput) {
        super(pi.clone(), cloneA(a), new ArrayList<>(opdfs));
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
     */
    protected InputHmmBase(double[] pi, double[][][] a, List<? extends Opdf<TOut>> opdfs, Map<TIn, Integer> possibleInput) {
        super(pi.clone(), cloneA(a), new ArrayList<>(opdfs));
        this.checkConstraints();
        CollectionUtils.putAll(this.indexRegister, possibleInput);
    }

    /**
     * Creates a new IHMM. The parameters of the created HMM set to
     * <code>null</code> specified and must be set using the appropriate
     * methods.
     *
     * @param nbSymbols The (strictly positive) number of states of the HMM.
     * @param nbStates The (strictly positive) number of states of the HMM.
     * @param possibleInput The possible input that may occur
     */
    protected InputHmmBase(int nbSymbols, int nbStates, Iterable<TIn> possibleInput) {
        super(generatePi(nbStates), generateA(nbSymbols, nbStates), new ArrayList<Opdf<TOut>>(nbStates));
        for (int i = 0; i < nbStates; i++) {
            this.b.add(null);
        }
        this.checkConstraints();
    }

    private void checkConstraints() {
        if (a.length == 0 || pi.length != a.length || b.size() != a.length) {
            throw new IllegalArgumentException("Wrong dimensions");
        }
    }

    private void initializeIndexRegister(Iterable<TIn> possibleInput) {
        this.indexRegister.clear();
        int i = 0x00;
        for (TIn obs : possibleInput) {
            this.indexRegister.put(obs, i);
            i++;
        }
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
    public InputHmmBase<TIn, TOut> clone() throws CloneNotSupportedException {
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
        int n = a[0x00].length;
        for (int k = 0x00; k < n; k++) {
            total += a[i][k][j];
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
        for (int i = 0x00; i < n; i++) {
            tmp = pia;
            pia = pib;
            pib = tmp;
            for (int j = 0x00; j < m; j++) {
                double tot = 0.0d;
                for (int k = 0x00; k < m; k++) {
                    tot += 1.0d;//TODO
                }
                pib[j] = tot;
            }
        }
        if ((n & 0x01) != 0x00) {
            System.arraycopy(pib, 0, pi, 0, m);
        }
    }

    @Override
    public Opdf<TOut> getOpdf(int stateNb) {
        return this.b.get(stateNb);
    }

    @Override
    public double lnProbability(List<? extends InputObservationTuple<TIn, TOut>> oseq) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int[] mostLikelyStateSequence(List<? extends InputObservationTuple<TIn, TOut>> oseq) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double probability(List<? extends InputObservationTuple<TIn, TOut>> oseq) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double probability(List<? extends InputObservationTuple<TIn, TOut>> oseq, int[] sseq) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fold(Iterable<? extends InputObservationTuple<TIn, TOut>> interaction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void splitInput(final TIn originalIn, final TIn... newIns) {
        if (newIns != null && newIns.length > 0x00) {
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
                        System.arraycopy(ai, 0, newa, 0, origix);
                        System.arraycopy(ai, origix + 0x01, newa, origix, offset - origix - 0x01);
                        newa[offset] = source;
                        for (int j = offset + 0x01; j < idxPtr; j++) {
                            newa[j] = Arrays.copyOf(source, nSource);
                        }
                        this.a[i] = ai;
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
        if (originalIns != null && originalIns.length > 0x00) {
            if (originalIns.length > 0x01) {
                final ListArray<TIn> originalList = new ListArray<>(originalIns);
                final ArrayList<Integer> ids = new ArrayList<>();
                CollectionUtils.removeAllLinked(this.indexRegister, originalList, ids);
                Collections.sort(ids);
                final double scale = 1.0d / ids.size();
                final int first = ids.get(0x00);
                final List<Integer> subl = ids.subList(0x01, ids.size() - 0x01);
                CollectionUtils.incrementValueByIndex(this.indexRegister, subl, -0x01);
                this.indexRegister.put(newIn, first);
                final int nState = indexRegister.size();
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
                CollectionUtils.replaceKey(indexRegister, originalIns[0x00], newIn);
            }
        } else {
            throw new IllegalArgumentException("Cannot merge zero branches.");
        }
    }

}
