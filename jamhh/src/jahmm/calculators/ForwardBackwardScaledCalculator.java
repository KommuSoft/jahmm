/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package jahmm.calculators;

import jahmm.RegularHmmBase;
import jahmm.observables.Observation;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import jutils.probability.ProbabilityUtils;
import jutlis.tuples.Tuple3;
import jutlis.tuples.Tuple3Base;

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
    private static final Logger LOG = Logger.getLogger(ForwardBackwardScaledCalculator.class.getName());

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
    public <O extends Observation> double computeProbability(RegularHmmBase<O> hmm, Collection<ComputationType> flags, List<? extends O> oseq) {
        if (oseq.isEmpty()) {
            throw new IllegalArgumentException();
        }

        int t = oseq.size();

        double[] ctFactors = new double[t];
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
     * @return
     */
    public <O extends Observation> double[][] computeAlpha(RegularHmmBase<? super O> hmm, Collection<O> oseq, double... ctFactors) {
        int T = ctFactors.length;
        int s = hmm.nbStates();
        Iterator<? extends O> seqIterator = oseq.iterator();
        double[][] alpha = new double[T][s];
        if (seqIterator.hasNext()) {

            O observation = seqIterator.next();

            for (int i = 0x00; i < s; i++) {
                alpha[0x00][i] = hmm.getPi(i) * hmm.getOpdf(i).probability(observation);
            }

            ctFactors[0x00] = ProbabilityUtils.scale(alpha[0x00]);

            for (int t = 1; t < T; t++) {
                observation = seqIterator.next();

                for (int i = 0; i < s; i++) {
                    double sum = 0.0d;
                    for (int j = 0; j < s; j++) {
                        sum += alpha[t - 1][j] * hmm.getAij(j, i);
                    }
                    alpha[t][i] = sum * hmm.getOpdf(i).probability(observation);
                }
                ctFactors[t] = ProbabilityUtils.scale(alpha[t]);
            }
        }
        return alpha;
    }

    /* Computes the content of the scaled beta array.  The scaling factors are
     those computed for alpha. */
    public <O extends Observation> double[][] computeBeta(RegularHmmBase<? super O> hmm, List<O> oseq, double... ctFactors) {
        int T = ctFactors.length;
        int s = hmm.nbStates();
        double[][] beta = new double[T][s];
        for (int i = 0; i < s; i++) {
            beta[T - 1][i] = 1.0d / ctFactors[T - 1];
        }

        for (int t = T - 2; t >= 0; t--) {
            O observation = oseq.get(t + 1);
            for (int i = 0; i < s; i++) {
                double sum = 0.;
                for (int j = 0; j < s; j++) {
                    sum += beta[t + 1][j] * hmm.getAij(i, j) * hmm.getOpdf(j).probability(observation);
                }
                beta[t][i] = sum;
                beta[t][i] /= ctFactors[t];
            }
        }
        return beta;
    }

    @Override
    public <O extends Observation> Tuple3<double[][], double[][], Double> computeAll(RegularHmmBase<? super O> hmm, List<O> oseq) {
        if (oseq.isEmpty()) {
            throw new IllegalArgumentException();
        }
        int t = oseq.size();
        double[] ctFactors = new double[t];
        double[][] alpha = computeAlpha(hmm, oseq, ctFactors);
        double[][] beta = computeBeta(hmm, oseq, ctFactors);
        double probability = computeProbability(ctFactors);
        return new Tuple3Base<>(alpha, beta, probability);
    }
}
