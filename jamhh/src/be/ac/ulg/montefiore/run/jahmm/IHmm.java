/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package be.ac.ulg.montefiore.run.jahmm;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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
 * @param <O> the type of observations
 */
public class IHmm<O extends Observation> extends Hmm<O> {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(IHmm.class.getName());

    protected double pi[];
    protected double a[][][];
    protected ArrayList<Opdf<O>> opdfs;

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
    public IHmm(int nbSymbols, int nbStates, OpdfFactory<? extends Opdf<O>> opdfFactory) {
        if (nbSymbols <= 0) {
            throw new IllegalArgumentException("Number of symbols must be strictly positive");
        }
        pi = new double[nbStates];
        a = new double[nbStates][nbSymbols][nbStates];
        opdfs = new ArrayList<>(nbStates);

        double ac = 1. / (nbStates*nbSymbols);
        for (int i = 0; i < nbStates; i++) {
            pi[i] = 1. / nbStates;
            opdfs.add(opdfFactory.factor());
            for (int j = 0; j < nbSymbols; j++) {
                for (int k = 0; k < nbStates; k++) {
                    a[i][j][k] = ac;
                }
            }
        }
    }

    /**
     * Creates a new IHMM. All the HMM parameters are given as arguments.
     *
     * @param pi The initial probability values.  <code>pi[i]</code> is the
     * initial probability of state <code>i</code>. This array is copied.
     * @param a The state transition probability array. <code>a[i][j][k]</code> is
     * the probability of going from state <code>k</code> given input symbol <code>j</code> to state
     * <code>j</code>. This array is copied.
     * @param opdfs The observation distributions.  <code>opdfs.get(i)</code> is
     * the observation distribution associated with state <code>i</code>. The
     * distributions are not copied.
     */
    public IHmm(double[] pi, double[][][] a, List<? extends Opdf<O>> opdfs) {
        
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
        if (nbSymbols <= 0) {
            throw new IllegalArgumentException("Number of symbols must be strictly positive");
        }
        if (nbStates <= 0) {
            throw new IllegalArgumentException("Number of symbols must be strictly positive");
        }
        pi = new double[nbStates];
        a = new double[nbStates][nbSymbols][nbStates];
        opdfs = new ArrayList<>(nbStates);

        double ac = 1. / (nbStates*nbSymbols);
        for (int i = 0; i < nbStates; i++) {
            pi[i] = 1. / nbStates;
            for (int j = 0; j < nbSymbols; j++) {
                for (int k = 0; k < nbStates; k++) {
                    a[i][j][k] = ac;
                }
            }
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
    public Hmm<O> clone()
            throws CloneNotSupportedException {
        IHmm<O> ihmm = new IHmm<>(nbSymbols(), nbStates());
        return ihmm;
    }

}
