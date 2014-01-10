/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package be.ac.ulg.montefiore.run.jahmm;

import java.io.Serializable;
import java.text.NumberFormat;
import static java.text.NumberFormat.getInstance;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;


public class Hmm<O extends Observation>
        implements Serializable, Cloneable {

    private double pi[];
    private double a[][];
    private ArrayList<Opdf<O>> opdfs;

    /**
     * Creates a new HMM. Each state has the same <i>pi</i> value and the
     * transition probabilities are all equal.
     *
     * @param nbStates The (strictly positive) number of states of the HMM.
     * @param opdfFactory A pdf generator that is used to build the pdfs
     * associated to each state.
     */
    public Hmm(int nbStates, OpdfFactory<? extends Opdf<O>> opdfFactory) {
        if (nbStates <= 0) {
            throw new IllegalArgumentException("Number of states must be "
                    + "strictly positive");
        }

        pi = new double[nbStates];
        a = new double[nbStates][nbStates];
        opdfs = new ArrayList<>(nbStates);

        for (int i = 0; i < nbStates; i++) {
            pi[i] = 1. / nbStates;
            opdfs.add(opdfFactory.factor());

            for (int j = 0; j < nbStates; j++) {
                a[i][j] = 1. / nbStates;
            }
        }
    }

    /**
     * Creates a new HMM. All the HMM parameters are given as arguments.
     *
     * @param pi The initial probability values.  <code>pi[i]</code> is the
     * initial probability of state <code>i</code>. This array is copied.
     * @param a The state transition probability array. <code>a[i][j]</code> is
     * the probability of going from state <code>i</code> to state
     * <code>j</code>. This array is copied.
     * @param opdfs The observation distributions.  <code>opdfs.get(i)</code> is
     * the observation distribution associated with state <code>i</code>. The
     * distributions are not copied.
     */
    public Hmm(double[] pi, double[][] a, List<? extends Opdf<O>> opdfs) {
        if (a.length == 0 || pi.length != a.length
                || opdfs.size() != a.length) {
            throw new IllegalArgumentException("Wrong parameter");
        }

        this.pi = pi.clone();
        this.a = new double[a.length][];

        for (int i = 0; i < a.length; i++) {
            if (a[i].length != a.length) {
                throw new IllegalArgumentException("'A' is not a square"
                        + "matrix");
            }
            this.a[i] = a[i].clone();
        }

        this.opdfs = new ArrayList<>(opdfs);
    }

    /**
     * Creates a new HMM. The parameters of the created HMM set to
     * <code>null</code> specified and must be set using the appropriate
     * methods.
     *
     * @param nbStates The (strictly positive) number of states of the HMM.
     */
    protected Hmm(int nbStates) {
        if (nbStates <= 0) {
            throw new IllegalArgumentException("Number of states must be "
                    + "positive");
        }

        pi = new double[nbStates];
        a = new double[nbStates][nbStates];
        opdfs = new ArrayList<>(nbStates);

        for (int i = 0; i < nbStates; i++) {
            opdfs.add(null);
        }
    }

    /**
     * Returns the number of states of this HMM.
     *
     * @return The number of states of this HMM.
     */
    public int nbStates() {
        return pi.length;
    }

    /**
     * Returns the <i>pi</i> value associated with a given state.
     *
     * @param stateNb A state number such that
     * <code>0 &le; stateNb &lt; nbStates()</code>
     * @return The <i>pi</i> value associated to <code>stateNb</code>.
     */
    public double getPi(int stateNb) {
        return pi[stateNb];
    }

    /**
     * Sets the <i>pi</i> value associated with a given state.
     *
     * @param stateNb A state number such that
     * <code>0 &le; stateNb &lt; nbStates()</code>.
     * @param value The <i>pi</i> value to associate to state number
     * <code>stateNb</code>
     */
    public void setPi(int stateNb, double value) {
        pi[stateNb] = value;
    }

    /**
     * Returns the opdf associated with a given state.
     *
     * @param stateNb A state number such that
     * <code>0 &le; stateNb &lt; nbStates()</code>.
     * @return The opdf associated to state <code>stateNb</code>.
     */
    public Opdf<O> getOpdf(int stateNb) {
        return opdfs.get(stateNb);
    }

    /**
     * Sets the opdf associated with a given state.
     *
     * @param stateNb A state number such that
     * <code>0 &le; stateNb &lt; nbStates()</code>.
     * @param opdf An observation probability function.
     */
    public void setOpdf(int stateNb, Opdf<O> opdf) {
        opdfs.set(stateNb, opdf);
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
    public int[] mostLikelyStateSequence(List<? extends O> oseq) {
        return (new ViterbiCalculator(oseq, this)).stateSequence();
    }

    /**
     * Returns the probability of an observation sequence given this HMM.
     *
     * @param oseq A non-empty observation sequence.
     * @return The probability of this sequence.
     */
    public double probability(List<? extends O> oseq) {
        return (new ForwardBackwardCalculator(oseq, this)).probability();
    }

    /**
     * Returns the neperian logarithm of observation sequence's probability
     * given this HMM. A <i>scaling</i> procedure is used in order to avoid
     * underflows when computing the probability of long sequences.
     *
     * @param oseq A non-empty observation sequence.
     * @return The probability of this sequence.
     */
    public double lnProbability(List<? extends O> oseq) {
        return (new ForwardBackwardScaledCalculator(oseq, this)).
                lnProbability();
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
    public double probability(List<? extends O> oseq, int[] sseq) {
        if (oseq.size() != sseq.length || oseq.isEmpty()) {
            throw new IllegalArgumentException();
        }

        double probability = getPi(sseq[0]);

        Iterator<? extends O> oseqIterator = oseq.iterator();

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
    public String toString(NumberFormat nf) {
        String s = "HMM with " + nbStates() + " state(s)\n";

        for (int i = 0; i < nbStates(); i++) {
            s += "\nState " + i + "\n";
            s += "  Pi: " + getPi(i) + "\n";
            s += "  Aij:";

            for (int j = 0; j < nbStates(); j++) {
                s += " " + nf.format(getAij(i, j));
            }
            s += "\n";

            s += "  Opdf: " + getOpdf(i).toString(nf) + "\n";
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
        return toString(getInstance());
    }

    /**
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public Hmm<O> clone()
            throws CloneNotSupportedException {
        Hmm<O> hmm = new Hmm<>(nbStates());

        hmm.pi = pi.clone();
        hmm.a = a.clone();

        for (int i = 0; i < a.length; i++) {
            hmm.a[i] = a[i].clone();
        }

        for (int i = 0; i < hmm.opdfs.size(); i++) {
            hmm.opdfs.set(i, opdfs.get(i).clone());
        }

        return hmm;
    }

    private static final long serialVersionUID = 2L;
    private static final Logger LOG = Logger.getLogger(Hmm.class.getName());
}
