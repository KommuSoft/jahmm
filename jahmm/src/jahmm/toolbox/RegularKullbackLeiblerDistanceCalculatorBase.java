/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package jahmm.toolbox;

import jahmm.Hmm;
import jahmm.RegularHmm;
import jahmm.calculators.RegularForwardBackwardCalculatorBase;
import jahmm.observables.Observation;
import java.util.List;
import java.util.logging.Logger;

/**
 * Computes the distance between HMMs.
 * <p>
 * The distance metric is similar to the Kullback-Leibler distance defined on
 * distributions. More information can be found in
 * <i>A Probabilistic Distance Measure For HMMs</i> by
 * <i>Rabiner</i> and <i>Juang</i> (AT&T Technical Journal, vol. 64, Feb. 1985,
 * pages 391-408).
 * <p>
 * This distance measure is not symmetric: <code>distance(hmm1, hmm2)</code> is
 * not necessary equal to <code>distance(hmm2, hmm1)</code>. To get a symmetric
 * distance definition, compute
 * <code>(distance(hmm1, hmm2) + distance(hmm2, hmm1)) / 2</code>.
 */
public class RegularKullbackLeiblerDistanceCalculatorBase<TObs extends Observation, TInt extends Observation, THmm extends Hmm<TObs, TInt>> {

    private static final Logger LOG = Logger.getLogger(RegularKullbackLeiblerDistanceCalculatorBase.class.getName());
    private int sequencesLength = 1000;
    private int nbSequences = 10;

    /**
     * Creates a new instance of a KullbackLeiblerDistanceCalculator.
     */
    public RegularKullbackLeiblerDistanceCalculatorBase() {
        this(1000);
    }

    /**
     * Creates a new instance of a KullbackLeiblerDistanceCalculator.
     *
     * @param sequencesLength The given initial sequence length.
     */
    public RegularKullbackLeiblerDistanceCalculatorBase(int sequencesLength) {
        this(sequencesLength, 10);
    }

    /**
     * Creates a new instance of a KullbackLeiblerDistanceCalculator.
     *
     * @param sequencesLength The given initial sequence length.
     * @param nbSequences The given initial number of sequences.
     */
    public RegularKullbackLeiblerDistanceCalculatorBase(int sequencesLength, int nbSequences) {
        this.sequencesLength = sequencesLength;
        this.nbSequences = nbSequences;
    }

    /**
     * Computes the Kullback-Leibler distance between two HMMs.
     *
     * @param <O>
     * @param hmm1 The first HMM against which the distance is computed. The
     * distance is measured with regard to this HMM (this must be defined since
     * the Kullback-Leibler distance is not symmetric).
     * @param hmm2 The second HMM against which the distance is computed.
     * @return The distance between <code>hmm1</code> and <code>hmm2</code> with
     * regard to <code>hmm1</code>
     */
    public double distance(THmm hmm1, THmm hmm2) {
        /*double distance = 0.0d;
        for (int i = 0; i < nbSequences; i++) {
            List<O> oseq = new RegularMarkovGeneratorBase<>(hmm1).observationSequence(sequencesLength);
            @SuppressWarnings("unchecked")
            double da = RegularForwardBackwardCalculatorBase.Instance.computeProbability(hmm1, oseq);
            @SuppressWarnings("unchecked")
            double db = RegularForwardBackwardCalculatorBase.Instance.computeProbability(hmm2, oseq);
            distance += da - db;
        }
        return distance / (nbSequences * sequencesLength);*/
        return 0.0d;
    }

    /**
     * Returns the number of sequences generated to estimate a distance.
     *
     * @return The number of generated sequences.
     */
    public int getNbSequences() {
        return nbSequences;
    }

    /**
     * Sets the number of sequences generated to estimate a distance.
     *
     * @param nb The number of generated sequences.
     */
    public void setNbSequences(int nb) {
        this.nbSequences = nb;
    }

    /**
     * Returns the length of sequences generated to estimate a distance.
     *
     * @return The sequences length.
     */
    public int getSequencesLength() {
        return sequencesLength;
    }

    /**
     * Sets the length of sequences generated to estimate a distance.
     *
     * @param length The sequences length.
     */
    public void setSequencesLength(int length) {
        this.sequencesLength = length;
    }
}
