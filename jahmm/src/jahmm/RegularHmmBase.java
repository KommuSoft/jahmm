/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license. See the LICENSE file.
 */
package jahmm;

import jahmm.calculators.RegularForwardBackwardCalculator;
import jahmm.calculators.RegularForwardBackwardCalculatorBase;
import jahmm.calculators.RegularForwardBackwardScaledCalculatorBase;
import jahmm.calculators.ViterbiCalculator;
import jahmm.observables.Observation;
import jahmm.observables.Opdf;
import jahmm.observables.OpdfFactory;
import jahmm.toolbox.MarkovGenerator;
import jahmm.toolbox.RegularMarkovGeneratorBase;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import jutlis.lists.ListArray;

/**
 * Main Hmm class; it implements a Hidden Markov Model. An HMM is composed of:
 * <ul>
 * <li><i>states</i>: each state has a given probability of being initial
 * (<i>pi</i>) and an associated observation probability function (<i>opdf</i>).
 * Each state is associated to an index; the first state is numbered 0, the last
 * n-1 (where n is the number of states in the HMM); this number is given as an
 * argument to the various functions to refer to the matching state. </li>
 * <li><i>transition probabilities</i>: that is, the probability of going from
 * state <i>i</i> to state <i>j</i> (<i>a<sub>i,j</sub></i>).</li>
 * </ul>
 * <p>
 * Important objects extensively used with HMMs are {@link Observation
 * Observation}s, observation sequences and set of observation sequences. An
 * observation sequence is simply a {@link List List} of
 * {@link Observation Observation}s (in the right order, the i-th element of the
 * vector being the i-th element of the sequence). A set of observation
 * sequences is a {@link java.util.List List} of such sequences.
 * 
* @param <TObs> the type of the observations.
 */
public class RegularHmmBase<TObs extends Observation> extends HmmBase<TObs, double[][], List<Opdf<TObs>>, TObs, RegularHmmBase<TObs>> implements RegularHmm<TObs, RegularHmmBase<TObs>> {

    private static final long serialVersionUID = 2L;
    private static final Logger LOG = Logger.getLogger(RegularHmmBase.class.getName());

    protected static double[][] cloneA(double[][] a) {
        int n = a.length;
        double[][] clone = new double[n][];
        for (int i = 0; i < n; i++) {
            if (a[i].length != n) {
                throw new IllegalArgumentException("'A' is not a square matrix");
            }
            clone[i] = a[i].clone();
        }
        return clone;
    }

    protected static double[][] generateA(int nbStates) {
        double[][] a = new double[nbStates][nbStates];
        double inv = 1.0d / nbStates;
        for (int i = 0x00; i < nbStates; i++) {
            for (int j = 0x00; j < nbStates; j++) {
                a[i][j] = inv;
            }
        }
        return a;
    }

    /**
     * Creates a new HMM. Each state has the same <i>pi</i> value and the
     * transition probabilities are all equal.
     *
     * @param nbStates The (strictly positive) number of states of the HMM.
     * @param opdfFactory A pdf generator that is used to build the pdfs
     * associated to each state.
     */
    public RegularHmmBase(int nbStates, OpdfFactory<? extends Opdf<TObs>> opdfFactory) {
        super(generatePi(nbStates), generateA(nbStates), new ArrayList<Opdf<TObs>>(nbStates));
        for (int i = 0; i < nbStates; i++) {
            b.add(opdfFactory.generate());
        }
        this.checkConstraints();
    }

    /**
     * Creates a new HMM. All the HMM parameters are given as arguments.
     *
     * @param pi The initial probability values. <code>pi[i]</code> is the
     * initial probability of state <code>i</code>. This array is copied.
     * @param a The state transition probability array. <code>a[i][j]</code> is
     * the probability of going from state <code>i</code> to state
     * <code>j</code>. This array is copied.
     * @param opdfs The observation distributions. <code>opdfs.get(i)</code> is
     * the observation distribution associated with state <code>i</code>. The
     * distributions are not copied.
     */
    public RegularHmmBase(double[] pi, double[][] a, List<? extends Opdf<TObs>> opdfs) {
        super(pi.clone(), cloneA(a), new ArrayList<>(opdfs));
        this.checkConstraints();
    }

    public RegularHmmBase(double[] pi, double[][] a, Opdf<TObs>... opdfs) {
        this(pi, a, new ListArray<>(opdfs));
    }

    /**
     * Creates a new HMM. The parameters of the created HMM set to
     * <code>null</code> specified and must be set using the appropriate
     * methods.
     *
     * @param nbStates The (strictly positive) number of states of the HMM.
     */
    protected RegularHmmBase(int nbStates) {
        super(generatePi(nbStates), generateA(nbStates), new ArrayList<Opdf<TObs>>(nbStates));
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
     * Returns the opdf associated with a given state.
     *     
* @param stateNb A state number such that
     * <code>0 &le; stateNb &lt; nbStates()</code>.
     * @return The opdf associated to state <code>stateNb</code>.
     */
    @Override
    public Opdf<TObs> getOpdf(int stateNb) {
        return b.get(stateNb);
    }

    /**
     * Sets the opdf associated with a given state.
     *     
* @param stateNb A state number such that
     * <code>0 &le; stateNb &lt; nbStates()</code>.
     * @param opdf An observation probability function.
     */
    public void setOpdf(int stateNb, Opdf<TObs> opdf) {
        b.set(stateNb, opdf);
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
     * <code>i</code> to state <code>j</code>.
     */
    @Override
    public double getAij(int i, int j) {
        return a[i][j];
    }

    /**
     * Sets the probability associated to the transition going from state
     * <i>i</i> to state <i>j</i> (<i>A<sub>i,j</sub></i>).
     *     
* @param i The first state number such that
     * <code>0 &le; i &lt; nbStates()</code>.
     * @param j The second state number such that
     * <code>0 &le; j &lt; nbStates()</code>.
     * @param value The value of <i>A<sub>i,j</sub></i>.
     */
    public void setAij(int i, int j, double value) {
        a[i][j] = value;
    }

    /**
     * Returns an array containing the most likely state sequence matching an
     * observation sequence given this HMM. This sequence <code>I</code>
     * maximizes the probability of <code>P[I|O,Model]</code> where
     * <code>O</code> is the observation sequence and <code>Model</code> this
     * HMM model.
     *     
* @param oseq A non-empty observation sequence.
     * @return An array containing the most likely sequence of state numbers.
     * This array can be modified.
     */
    @Override
    public int[] mostLikelyStateSequence(List<? extends TObs> oseq) {
        return (new ViterbiCalculator(oseq, this)).stateSequence();
    }

    /**
     * Returns the probability of an observation sequence given this HMM.
     *     
* @param oseq A non-empty observation sequence.
     * @return The probability of this sequence.
     */
    @Override
    public double probability(List<? extends TObs> oseq) {
        return RegularForwardBackwardCalculatorBase.Instance.computeProbability(this, oseq);
    }

    /**
     * Returns the natural logarithm of observation sequences probability given
     * this HMM. A <i>scaling</i> procedure is used in order to avoid underflows
     * when computing the probability of long sequences.
     *     
* @param oseq A non-empty observation sequence.
     * @return The probability of this sequence.
     */
    @Override
    public double lnProbability(List<? extends TObs> oseq) {
        return RegularForwardBackwardScaledCalculatorBase.Instance.computeProbability(this, oseq);
    }

    /**
     * Returns the probability of an observation sequence along a state sequence
     * given this HMM.
     *     
* @param oseq A non-empty observation sequence.
     * @param sseq An array containing a sequence of state numbers. The length
     * of this array must be equal to the length of <code>oseq</code>
     * @return The probability P[oseq,sseq|H], where H is this HMM.
     */
    @Override
    public double probability(List<? extends TObs> oseq, int[] sseq) {
        if (oseq.size() != sseq.length || oseq.isEmpty()) {
            throw new IllegalArgumentException();
        }

        double probability = getPi(sseq[0]);

        Iterator<? extends TObs> oseqIterator = oseq.iterator();

        for (int i = 0; i < sseq.length - 1; i++) {
            probability
                    *= getOpdf(sseq[i]).probability(oseqIterator.next())
                    * getAij(sseq[i], sseq[i + 1]);
        }

        return probability * getOpdf(sseq[sseq.length - 1]).
                probability(oseq.get(sseq.length - 1));
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

            s += " Opdf: " + getOpdf(i).toString(nf) + "\n";
        }

        return s;
    }

    /**
     * Gives a description of this HMM.
     *     
* @return A textual description of this HMM.
     */
    @Override
    public String toString() {
        return toString(NumberFormat.getInstance());
    }

    /**
     * Creates a duplicate object of the HMM.
     *
     * @return An IHHM that contains the same date as this object.
     * @throws CloneNotSupportedException An exception such that classes lower
     * in the hierarchy can fail to clone.
     */
    @Override
    public RegularHmmBase<TObs> clone() throws CloneNotSupportedException {
        return new RegularHmmBase<>(this.pi, this.a, this.b);
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
                    tot += a[k][j] * pia[k];
                }
                pib[j] = tot;
            }
        }
        if ((n & 0x01) != 0x00) {
            System.arraycopy(pib, 0, pi, 0, m);
        }
    }

    /**
     * Gets the relevant forward backward calculator for the Hidden Markov
     * Model.
     *
     * @return The relevant forward backward calculator for the Hidden Markov
     * Model.
     */
    @Override
    public RegularForwardBackwardCalculator<TObs, RegularHmmBase<TObs>> getForwardBackwardCalculator() {
        return RegularForwardBackwardCalculatorBase.Instance;
    }

    /**
     * Gets the relevant forward backward scaled calculator for the Hidden
     * Markov Model.
     *
     * @return The relevant forward backward scaled calculator for the Hidden
     * Markov Model.
     */
    @Override
    public RegularForwardBackwardCalculator<TObs, RegularHmmBase<TObs>> getForwardBackwardScaledCalculator() {
        return RegularForwardBackwardScaledCalculatorBase.Instance;
    }

    @Override
    public MarkovGenerator<TObs, TObs, RegularHmmBase<TObs>> getMarkovGenerator() {
        return new RegularMarkovGeneratorBase<>(this);
    }
}
