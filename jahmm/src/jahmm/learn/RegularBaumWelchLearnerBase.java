/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package jahmm.learn;

import jahmm.Hmm;
import jahmm.RegularHmm;
import jahmm.calculators.ForwardBackwardCalculator;
import jahmm.calculators.RegularForwardBackwardCalculatorBase;
import jahmm.observables.Observation;
import jahmm.observables.Opdf;
import java.util.Arrays;
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
public class RegularBaumWelchLearnerBase<TObs extends Observation> extends BaumWelchLearnerBase<TObs, TObs, RegularHmm<TObs>, double[][], double[][]> implements RegularBaumWelchLearner<TObs> {

    private static final Logger LOG = Logger.getLogger(RegularBaumWelchLearnerBase.class.getName());

    /**
     * Initializes a Baum-Welch instance.
     */
    public RegularBaumWelchLearnerBase() {
    }

    /**
     * Performs one iteration of the Baum-Welch algorithm. In one iteration, a
     * new HMM is computed using a previously estimated HMM.
     *
     * @param hmm A previously estimated HMM.
     * @param sequences The observation sequences on which the learning is
     * based. Each sequence must have a length higher or equal to 2.
     * @return A new, updated HMM.
     */
    @Override
    public RegularHmm<TObs> iterate(RegularHmm<TObs> hmm, List<? extends List<? extends TObs>> sequences) {
        RegularHmm<TObs> nhmm;
        try {
            nhmm = hmm.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }

        /* gamma and xi arrays are those defined by Rabiner and Juang */
        /* allGamma[n] = gamma array associated to observation sequence n */
        double allGamma[][][] = new double[sequences.size()][][];

        /* a[i][j] = aijNum[i][j] / aijDen[i]
         * aijDen[i] = expected number of transitions from state i
         * aijNum[i][j] = expected number of transitions from state i to j
         */
        double aijNum[][] = new double[hmm.nbStates()][hmm.nbStates()];
        double aijDen[] = new double[hmm.nbStates()];

        Arrays.fill(aijDen, 0.);
        for (int i = 0; i < hmm.nbStates(); i++) {
            Arrays.fill(aijNum[i], 0.);
        }

        int g = 0;
        for (List<? extends TObs> obsSeq : sequences) {

            Tuple3<double[][], double[][], Double> abp = getAlphaBetaProbability(hmm, obsSeq);

            double xi[][][] = estimateXi(obsSeq, abp, hmm);
            double gamma[][] = allGamma[g++] = estimateGamma(xi);

            for (int i = 0; i < hmm.nbStates(); i++) {
                for (int t = 0; t < obsSeq.size() - 1; t++) {
                    aijDen[i] += gamma[t][i];

                    for (int j = 0; j < hmm.nbStates(); j++) {
                        aijNum[i][j] += xi[t][i][j];
                    }
                }
            }
        }

        for (int i = 0; i < hmm.nbStates(); i++) {
            if (aijDen[i] == 0.) // State i is not reachable
            {
                for (int j = 0; j < hmm.nbStates(); j++) {
                    nhmm.setAij(i, j, hmm.getAij(i, j));
                }
            } else {
                for (int j = 0; j < hmm.nbStates(); j++) {
                    nhmm.setAij(i, j, aijNum[i][j] / aijDen[i]);
                }
            }
        }

        /* pi computation */
        for (int i = 0; i < hmm.nbStates(); i++) {
            nhmm.setPi(i, 0.);
        }

        for (int o = 0; o < sequences.size(); o++) {
            for (int i = 0; i < hmm.nbStates(); i++) {
                nhmm.setPi(i, nhmm.getPi(i) + allGamma[o][0][i] / sequences.size());
            }
        }

        /* pdfs computation */
        for (int i = 0; i < hmm.nbStates(); i++) {
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

        return nhmm;
    }

    /**
     *
     * @param sequence
     * @param abp
     * @param hmm
     * @return
     */
    protected double[][][] estimateXi(List<? extends TObs> sequence, Tuple3<double[][], double[][], Double> abp, RegularHmm<TObs> hmm) {
        if (sequence.size() <= 1) {
            throw new IllegalArgumentException("Observation sequence too short");
        }

        double[][] a = abp.getItem1();
        double[][] b = abp.getItem2();
        double pinv = 1.0d / abp.getItem3();

        double xi[][][]
                = new double[sequence.size() - 1][hmm.nbStates()][hmm.nbStates()];

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
     * gamma[][] could be computed directly using the alpha and beta arrays, but
     * this (slower) method is preferred because it doesn't change if the xi
     * array has been scaled (and should be changed with the scaled alpha and
     * beta arrays).
     *
     * @param xi
     * @return
     */
    protected double[][] estimateGamma(double[][][] xi) {
        double[][] gamma = new double[xi.length + 1][xi[0].length];

        for (int t = 0; t < xi.length + 1; t++) {
            Arrays.fill(gamma[t], 0.);
        }

        for (int t = 0; t < xi.length; t++) {
            for (int i = 0; i < xi[0].length; i++) {
                for (int j = 0; j < xi[0].length; j++) {
                    gamma[t][i] += xi[t][i][j];
                }
            }
        }

        for (int j = 0; j < xi[0].length; j++) {
            for (int i = 0; i < xi[0].length; i++) {
                gamma[xi.length][j] += xi[xi.length - 1][i][j];
            }
        }

        return gamma;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected ForwardBackwardCalculator<double[][], double[][], TObs, TObs, RegularHmm<TObs>> getCalculator() {
        return RegularForwardBackwardCalculatorBase.Instance;
    }
}
