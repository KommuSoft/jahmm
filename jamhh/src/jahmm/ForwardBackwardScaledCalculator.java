/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package jahmm;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * This class can be used to compute the probability of a given observations
 * sequence for a given HMM.
 * <p>
 * This class implements the scaling method explained in <i>Rabiner</i> and
 * <i>Juang</i>, thus the {@link #alphaElement(int,int) alphaElement} and
 * {@link #betaElement(int,int) betaElement} return the scaled alpha and beta
 * elements. The <code>alpha</code> array must always be computed because the
 * scaling factors are computed together with it.
 * <p>
 * For more information on the scaling procedure, read <i>Rabiner</i> and
 * <i>Juang</i>'s <i>Fundamentals of speech recognition</i> (Prentice Hall,
 * 1993).
 */
public final class ForwardBackwardScaledCalculator extends ForwardBackwardCalculator {

    public static final ForwardBackwardScaledCalculator Instance = new ForwardBackwardScaledCalculator();

    /* Normalize alpha[t] and put the normalization factor in ctFactors[t] */
    private static void scale(double[] ctFactors, double[][] array, int t) {
        double[] table = array[t];
        double sum = 0.;

        for (int i = 0; i < table.length; i++) {
            sum += table[i];
        }

        ctFactors[t] = sum;
        for (int i = 0; i < table.length; i++) {
            table[i] /= sum;
        }
    }

    protected ForwardBackwardScaledCalculator() {
    }

    private <O extends Observation> double computeProbability(double[] ctFactors) {
        double lnProbability = 0.;
        int T = ctFactors.length;

        for (int t = 0; t < T; t++) {
            lnProbability += Math.log(ctFactors[t]);
        }

        return Math.exp(lnProbability);
    }

    /**
     * Computes the probability of occurrence of an observation sequence given a
     * Hidden Markov Model. The algorithms implemented use scaling to avoid
     * underflows.
     *
     * @param <O>
     * @param hmm A Hidden Markov Model;
     * @param oseq An observations sequence.
     * @param flags How the computation should be done. See the
     * {@link ForwardBackwardCalculator.ComputationType}. The alpha array is
     * always computed.
     * @return The probability of the given sequence of observations.
     */
    @Override
    public <O extends Observation> double computeProbability(Hmm<O> hmm, Collection<ComputationType> flags, List<? extends O> oseq) {
        if (oseq.isEmpty()) {
            throw new IllegalArgumentException();
        }

        int T = oseq.size();

        double[] ctFactors = new double[T];
        double[][] alpha = null, beta = null;

        alpha = computeAlpha(hmm, oseq, ctFactors);

        if (flags.contains(ComputationType.BETA)) {
            beta = computeBeta(hmm, oseq, ctFactors);
        }

        return computeProbability(ctFactors);
    }

    /* Computes the content of the scaled alpha array */
    /**
     *
     * @param <O
     * @param ctFactors>
     * @param hmm
     * @param oseq
     */
    public <O extends Observation> double[][] computeAlpha(Hmm<? super O> hmm, Collection<O> oseq, double[] ctFactors) {
        int T = ctFactors.length;
        int N = hmm.nbStates();
        Iterator<? extends O> seqIterator = oseq.iterator();
        double[][] alpha = new double[T][N];
        if (seqIterator.hasNext()) {

            O observation = seqIterator.next();

            for (int i = 0; i < N; i++) {
                alpha[0][i] = hmm.getPi(i) * hmm.getOpdf(i).probability(observation);
            }
            scale(ctFactors, alpha, 0);

            for (int t = 1; t < T; t++) {
                observation = seqIterator.next();

                for (int i = 0; i < N; i++) {
                    double sum = 0.;
                    for (int j = 0; j < N; j++) {
                        sum += alpha[t - 1][j] * hmm.getAij(j, i);
                    }
                    alpha[t][i] = sum * hmm.getOpdf(i).probability(observation);
                }
                scale(ctFactors, alpha, t);
            }
        }
        return alpha;
    }

    /* Computes the content of the scaled beta array.  The scaling factors are
     those computed for alpha. */
    public <O extends Observation> double[][] computeBeta(Hmm<? super O> hmm, List<O> oseq, double[] ctFactors) {
        int T = ctFactors.length;
        int N = hmm.nbStates();
        double[][] beta = new double[T][N];
        for (int i = 0; i < N; i++) {
            beta[T - 1][i] = 1. / ctFactors[T - 1];
        }

        for (int t = T - 2; t >= 0; t--) {
            O observation = oseq.get(t + 1);
            for (int i = 0; i < N; i++) {
                double sum = 0.;
                for (int j = 0; j < N; j++) {
                    sum += beta[t + 1][j] * hmm.getAij(i, j)
                            * hmm.getOpdf(j).probability(observation);
                }
                beta[t][i] = sum;
                beta[t][i] /= ctFactors[t];
            }
        }
        return beta;
    }
}
