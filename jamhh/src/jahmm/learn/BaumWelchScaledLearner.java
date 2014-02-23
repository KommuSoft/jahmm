/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package jahmm.learn;

import jahmm.ForwardBackwardScaledCalculator;
import jahmm.Hmm;
import jahmm.observables.Observation;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import jutlis.tuples.Tuple3;

/**
 * An implementation of the Baum-Welch learning algorithm. It uses a scaling
 * mechanism so as to avoid underflows.
 * <p>
 * For more information on the scaling procedure, read <i>Rabiner</i> and
 * <i>Juang</i>'s <i>Fundamentals of speech recognition</i> (Prentice Hall,
 * 1993).
 */
public class BaumWelchScaledLearner extends BaumWelchLearner {

    private static final Logger LOG = Logger.getLogger(BaumWelchScaledLearner.class.getName());

    /**
     * Initializes a Baum-Welch algorithm implementation.
     */
    public BaumWelchScaledLearner() {
    }

    @Override
    protected <O extends Observation> Tuple3<double[][], double[][], Double> getAlphaBetaProbability(Hmm<O> hmm, List<? extends O> obsSeq) {
        return ForwardBackwardScaledCalculator.Instance.computeAll(hmm, obsSeq);
    }

    /* Here, the xi (and, thus, gamma) values are not divided by the
     probability of the sequence because this probability might be
     too small and induce an underflow. xi[t][i][j] still can be
     interpreted as P[q_t = i and q_(t+1) = j | obsSeq, hmm] because
     we assume that the scaling factors are such that their product
     is equal to the inverse of the probability of the sequence. */
    /**
     *
     * @param <O>
     * @param sequence
     * @param abp
     * @param hmm
     * @return
     */
    @Override
    protected <O extends Observation> double[][][] estimateXi(List<? extends O> sequence, Tuple3<double[][], double[][], Double> abp, Hmm<O> hmm) {
        if (sequence.size() <= 1) {
            throw new IllegalArgumentException("Observation sequence too short");
        }
        double xi[][][] = new double[sequence.size() - 1][hmm.nbStates()][hmm.nbStates()];

        double[][] alpha = abp.getItem1();
        double[][] beta = abp.getItem2();

        Iterator<? extends O> seqIterator = sequence.iterator();
        seqIterator.next();

        for (int t = 0; t < sequence.size() - 1; t++) {
            O observation = seqIterator.next();

            for (int i = 0; i < hmm.nbStates(); i++) {
                for (int j = 0; j < hmm.nbStates(); j++) {
                    xi[t][i][j] = alpha[t][i] * hmm.getAij(i, j) * hmm.getOpdf(j).probability(observation) * beta[t + 1][j];
                }
            }
        }

        return xi;
    }
}
