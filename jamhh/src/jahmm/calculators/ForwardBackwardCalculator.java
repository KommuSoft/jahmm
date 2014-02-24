/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package jahmm.calculators;

import jahmm.RegularHmmBase;
import jahmm.observables.Observation;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import jutlis.tuples.Tuple3;
import jutlis.tuples.Tuple3Base;

/**
 * This class can be used to compute the probability of a given observations
 * sequence for a given HMM. Once the probability has been computed, the object
 * holds various information such as the <i>alpha</i> (and possibly
 * <i>beta</i>) array, as described in <i>Rabiner</i> and <i>Juang</i>.
 * <p>
 * Computing the <i>beta</i> array requires a O(1) access time to the
 * observation sequence to get a theoretically optimal performance.
 */
public class ForwardBackwardCalculator extends ForwardBackwardCalculatorBase<double[][], double[][]> {

    public static final ForwardBackwardCalculator Instance = new ForwardBackwardCalculator();
    private static final Logger LOG = Logger.getLogger(ForwardBackwardCalculator.class.getName());

    /**
     *
     */
    protected ForwardBackwardCalculator() {
    }

    protected <O extends Observation> double computeProbability(List<O> oseq, RegularHmmBase<? super O> hmm, Collection<ComputationType> flags, double[][] alpha, double[][] beta) {
        double probability = 0.;
        int n = hmm.nbStates();
        double[] tmp;
        if (flags.contains(ComputationType.ALPHA)) {
            tmp = alpha[oseq.size() - 1];
            for (int i = 0; i < n; i++) {
                probability += tmp[i];
            }
        } else {
            tmp = beta[0x00];
            O observation = oseq.get(0x00);
            for (int i = 0; i < n; i++) {
                probability += hmm.getPi(i) * hmm.getOpdf(i).probability(observation) * tmp[i];
            }
        }
        return probability;
    }

    /**
     * Computes the probability of occurrence of an observation sequence given a
     * Hidden Markov Model.
     *
     * @param <O>
     * @param hmm A Hidden Markov Model;
     * @param oseq An observation sequence.
     * @param flags How the computation should be done. See the
     * {@link ComputationType ComputationType} enum.
     * @return
     */
    @Override
    public <O extends Observation> double computeProbability(RegularHmmBase<O> hmm, Collection<ComputationType> flags, List<? extends O> oseq) {
        if (oseq.isEmpty()) {
            throw new IllegalArgumentException("Invalid empty sequence");
        }

        double[][] alpha = null, beta = null;

        if (flags.contains(ComputationType.ALPHA)) {
            alpha = computeAlpha(hmm, oseq);
        }

        if (flags.contains(ComputationType.BETA)) {
            beta = computeBeta(hmm, oseq);
        }

        return computeProbability(oseq, hmm, flags, alpha, beta);
    }

    /**
     * Computes the content of the alpha array
     *
     * @param <O>
     * @param hmm
     * @param oseq
     * @return alpha[t][i] = P(O(1), O(2),..., O(t+1), i(t+1) = i+1 | hmm), that
     * is the probability of the beginning of the state sequence (up to time
     * t+1) with the (t+1)th state being i+1.
     */
    @Override
    public <O extends Observation> double[][] computeAlpha(RegularHmmBase<? super O> hmm, Collection<O> oseq) {
        int T = oseq.size();
        int s = hmm.nbStates();
        double[][] alpha = new double[T][s];
        T--;

        Iterator<O> seqIterator = oseq.iterator();
        O observation;
        if (seqIterator.hasNext()) {
            observation = seqIterator.next();

            for (int i = 0; i < hmm.nbStates(); i++) {
                alpha[0][i] = hmm.getPi(i) * hmm.getOpdf(i).probability(observation);
            }

            for (int t = 0; t < T; t++) {
                observation = seqIterator.next();

                for (int j = 0; j < s; j++) {
                    double sum = 0.;
                    for (int i = 0; i < s; i++) {
                        sum += alpha[t][i] * hmm.getAij(i, j);
                    }
                    alpha[t + 0x01][j] = sum * hmm.getOpdf(j).probability(observation);
                }
            }
        }
        return alpha;
    }

    /* Computes the content of the beta array.  Needs a O(1) access time
     to the elements of oseq to get a theoretically optimal algorithm. */
    @Override
    public <O extends Observation> double[][] computeBeta(RegularHmmBase<? super O> hmm, List<O> oseq) {
        int t = oseq.size();
        int s = hmm.nbStates();
        double[][] beta = new double[t][s];
        t--;
        O observation;

        for (int i = 0; i < s; i++) {
            beta[t][i] = 1.0d;
        }

        for (; t > 0;) {
            for (int i = 0; i < s; i++) {
                observation = oseq.get(t);
                double sum = 0.0d;
                for (int j = 0; j < s; j++) {
                    sum += beta[t][j] * hmm.getAij(i, j) * hmm.getOpdf(j).probability(observation);
                }
                beta[t - 0x01][i] = sum;
            }
            t--;
        }
        return beta;
    }

    @Override
    public <O extends Observation> Tuple3<double[][], double[][], Double> computeAll(RegularHmmBase<? super O> hmm, List<O> oseq) {
        double[][] alpha = computeAlpha(hmm, oseq);
        double[][] beta = computeBeta(hmm, oseq);
        double probability = computeProbability(oseq, hmm, EnumSet.of(ComputationType.ALPHA), alpha, beta);
        return new Tuple3Base<>(alpha, beta, probability);
    }
}
