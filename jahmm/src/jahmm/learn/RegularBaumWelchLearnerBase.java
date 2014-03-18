/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package jahmm.learn;

import jahmm.RegularHmm;
import jahmm.calculators.ForwardBackwardCalculator;
import jahmm.calculators.RegularForwardBackwardCalculatorBase;
import jahmm.observables.Observation;
import jahmm.observables.Opdf;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import jutlis.tuples.Tuple3;

/**
 * An implementation of the Baum-Welch learning algorithm. This algorithm finds
 * a HMM that models a set of observation sequences.
 *
 * @param <TObs>
 */
public class RegularBaumWelchLearnerBase<TObs extends Observation> extends BaumWelchLearnerGammaBase<TObs, TObs, RegularHmm<TObs>, double[][], double[][], double[]> implements RegularBaumWelchLearner<TObs> {

    private static final Logger LOG = Logger.getLogger(RegularBaumWelchLearnerBase.class.getName());

    /**
     * Initializes a Baum-Welch instance.
     */
    public RegularBaumWelchLearnerBase() {
    }

    /**
     * Here, the xi (and, thus, gamma) values are not divided by the probability
     * of the sequence because this probability might be too small and induce an
     * underflow. xi[t][i][j] still can be interpreted as P[q_t = i and q_(t+1)
     * = j | obsSeq, hmm] because we assume that the scaling factors are such
     * that their product is equal to the inverse of the probability of the
     * sequence.
     *
     * @param sequence The sequence of interactions.
     * @param abp A tuple containing alpha- and beta-values and the probability
     * of the given interaction sequence.
     * @param hmm The given Hidden Markov Model.
     * @return The estimated Xi values.
     */
    @Override
    protected double[][][] estimateXi(List<? extends TObs> sequence, Tuple3<double[][], double[][], Double> abp, RegularHmm<TObs> hmm) {
        if (sequence.size() <= 1) {
            throw new IllegalArgumentException("Observation sequence too short");
        }
        double[][] a = abp.getItem1();
        double[][] b = abp.getItem2();
        double pinv = 1.0d / abp.getItem3();
        double[][][] xi = new double[sequence.size() - 1][hmm.nbStates()][hmm.nbStates()];
        Iterator<? extends TObs> seqIterator = sequence.iterator();
        seqIterator.next();
        for (int t = 0; t < sequence.size() - 1; t++) {
            TObs o = seqIterator.next();
            for (int i = 0; i < hmm.nbStates(); i++) {
                for (int j = 0; j < hmm.nbStates(); j++) {
                    xi[t][i][j] = a[t][i] * hmm.getAij(i, j) * hmm.getOpdf(j).probability(o) * b[t + 1][j] * pinv;
                }
            }
        }
        return xi;
    }

    /**
     * Gets the relevant calculator.
     *
     * @return The relevant calculator.
     */
    @Override
    @SuppressWarnings("unchecked")
    protected ForwardBackwardCalculator<double[][], double[][], TObs, TObs, RegularHmm<TObs>> getCalculator() {
        return RegularForwardBackwardCalculatorBase.Instance;
    }

    /**
     * Creates a new instance of the â-denominator based on the given Hidden
     * Markov Model.
     *
     * @param hmm The given Hidden Markov Model.
     * @return A new instance of the â-denominator.
     */
    @Override
    protected double[] createADenominator(RegularHmm<TObs> hmm) {
        return new double[hmm.nbStates()];
    }

    /**
     * Creates a new instance of the â-numerator based on the given Hidden
     * Markov Model.
     *
     * @param hmm The given Hidden Markov Model.
     * @return A new instance of the â-numerator.
     */
    @Override
    protected double[][] createANumerator(RegularHmm<TObs> hmm) {
        return new double[hmm.nbStates()][hmm.nbStates()];
    }

    /**
     * Updates the â-values based on the given Gamma and Xi values.
     *
     * @param gamma The gamma values of the sequence.
     * @param xi The xi values of the sequence.
     * @param aijDen The denominators of the â-values.
     * @param aijNum The numerators of the â-values.
     */
    @Override
    protected void updateAbarXiGamma(RegularHmm<TObs> hmm, List<? extends TObs> obsSeq, double[][][] xi, double[][] gamma, double[][] aijNum, double[] aijDen) {
        int I = aijDen.length;
        int T = xi.length;
        for (int i = 0; i < I; i++) {
            for (int t = 0; t < T; t++) {
                aijDen[i] += gamma[t][i];

                for (int j = 0; j < I; j++) {
                    aijNum[i][j] += xi[t][i][j];
                }
            }
        }
    }

    /**
     * Sets the a-values of the Hidden Markov Model based on the values of the
     * â-values.
     *
     * @param hmm The Hidden Markov Model to modify.
     * @param aijNum The numerators of the â-values.
     * @param aijDen The denominators of the â-values.
     */
    @Override
    protected void setAValues(RegularHmm<TObs> hmm, double[][] aijNum, double[] aijDen) {
        for (int i = 0; i < hmm.nbStates(); i++) {
            if (aijDen[i] > 0.) { // State i is reachable
                for (int j = 0; j < hmm.nbStates(); j++) {
                    hmm.setAij(i, j, aijNum[i][j] / aijDen[i]);
                }
            }
        }
    }

    /**
     * Sets the pi-values of the Hidden Markov Model based on the gamma values.
     *
     * @param nhmm The Hidden Markov Model to modify.
     * @param allGamma The set of values
     */
    @Override
    protected void setPiValues(RegularHmm<TObs> nhmm, double[][][] allGamma) {
        int O = allGamma.length;
        int I = allGamma[0x00][0x00].length;
        for (int i = 0; i < I; i++) {
            double total = 0.0d;
            for (int o = 0; o < O; o++) {
                total += allGamma[o][0][i];
            }
            nhmm.setPi(i, total / O);
        }
    }

    /**
     * Sets the pdf values based on the given sequence of interactions and the
     * given gamma values.
     *
     * @param nhmm The given Hidden Markov Model to modify.
     * @param sequences The given sequence of interactions.
     * @param allGamma The list of all gamma values calculated based on all the
     * lists of interactions.
     */
    @Override
    protected void setPdfValues(RegularHmm<TObs> nhmm, List<? extends List<? extends TObs>> sequences, double[][][] allGamma) {
        int I = nhmm.nbStates();
        for (int i = 0; i < I; i++) {
            List<TObs> observations = KMeansLearner.flat(sequences);
            double[] weights = new double[observations.size()];
            double sum = 0.;
            int j = 0;

            int o = 0;
            for (List<? extends TObs> obsSeq : sequences) {
                for (int t = 0; t < obsSeq.size(); t++, j++) {
                    sum += weights[j] = allGamma[o][t][i];
                }
                o++;
            }

            for (j--; j >= 0; j--) {
                weights[j] /= sum;
            }

            Opdf<TObs> opdf = nhmm.getOpdf(i);
            opdf.fit(observations, weights);
        }
    }

}
