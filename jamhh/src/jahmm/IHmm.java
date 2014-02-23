/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package jahmm;

import jahmm.observables.InputObservationTuple;
import jahmm.observables.Observation;
import jahmm.observables.OpdfFactory;
import jahmm.observables.Opdf;
import static jahmm.HmmBase.generatePi;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
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
 */
public class IHmm<In, Out extends Observation> extends HmmBase<Out, double[][][], ArrayList<Opdf<Out>>, InputObservationTuple<In, Out>> {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(IHmm.class.getName());

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

    /**
     * Creates a new IHMM. Each state has the same <i>pi</i> value and the
     * transition probabilities are all equal.
     *
     * @param nbSymbols The (strictly positive) number of input symbols of the
     * IHMM.
     * @param nbStates The (strictly positive) number of states of the IHMM.
     * @param opdfFactory A pdf generator that is used to build the pdfs
     * associated to each state.
     */
    public IHmm(int nbSymbols, int nbStates, OpdfFactory<? extends Opdf<Out>> opdfFactory) {
        super(generatePi(nbStates), generateA(nbSymbols, nbStates), new ArrayList<Opdf<Out>>(nbStates));
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
     */
    public IHmm(double[] pi, double[][][] a, List<? extends Opdf<Out>> opdfs) {
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
     */
    public IHmm(double[] pi, double[][][] a, Opdf<Out>... opdfs) {
        this(pi, a, new ListArray<>(opdfs));
    }

    /**
     * Creates a new IHMM. The parameters of the created HMM set to
     * <code>null</code> specified and must be set using the appropriate
     * methods.
     *
     * @param nbSymbols The (strictly positive) number of states of the HMM.
     * @param nbStates The (strictly positive) number of states of the HMM.
     */
    protected IHmm(int nbSymbols, int nbStates) {
        super(generatePi(nbStates), generateA(nbSymbols, nbStates), new ArrayList<Opdf<Out>>(nbStates));
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
    public IHmm<In, Out> clone()
            throws CloneNotSupportedException {
        IHmm<In, Out> ihmm = new IHmm<>(nbSymbols(), nbStates());
        //TODO
        return ihmm;
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
    public Opdf<Out> getOpdf(int stateNb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double lnProbability(List<? extends InputObservationTuple<In, Out>> oseq) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int[] mostLikelyStateSequence(List<? extends InputObservationTuple<In, Out>> oseq) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double probability(List<? extends InputObservationTuple<In, Out>> oseq) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double probability(List<? extends InputObservationTuple<In, Out>> oseq, int[] sseq) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fold(Iterable<? extends InputObservationTuple<In, Out>> interaction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
