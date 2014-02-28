/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package jahmm.learn;

import jahmm.RegularHmm;
import jahmm.calculators.ForwardBackwardCalculator;
import jahmm.calculators.RegularForwardBackwardScaledCalculatorBase;
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
public class RegularBaumWelchScaledLearnerBase<TObs extends Observation> extends RegularBaumWelchLearnerBase<TObs> {

    private static final Logger LOG = Logger.getLogger(RegularBaumWelchScaledLearnerBase.class.getName());

    /**
     * Initializes a Baum-Welch algorithm implementation.
     */
    public RegularBaumWelchScaledLearnerBase() {
    }

    @Override
    @SuppressWarnings("unchecked")
    protected ForwardBackwardCalculator<double[][], double[][], TObs, TObs, RegularHmm<TObs>> getCalculator() {
        return RegularForwardBackwardScaledCalculatorBase.Instance;
    }

    /**
     * Here, the xi (and, thus, gamma) values are not divided by the probability
     * of the sequence because this probability might be too small and induce an
     * underflow. xi[t][i][j] still can be interpreted as P[q_t = i and q_(t+1)
     * = j | obsSeq, hmm] because we assume that the scaling factors are such
     * that their product is equal to the inverse of the probability of the
     * sequence.
     *
     * @param sequence
     * @param abp
     * @param hmm
     * @return
     */
    @Override
    protected double[][][] estimateXi(List<? extends TObs> sequence, Tuple3<double[][], double[][], Double> abp, RegularHmm<TObs> hmm) {
        if (sequence.size() <= 1) {
            throw new IllegalArgumentException("Observation sequence too short");
        }
        double xi[][][] = new double[sequence.size() - 1][hmm.nbStates()][hmm.nbStates()];

        double[][] alpha = abp.getItem1();
        double[][] beta = abp.getItem2();

        Iterator<? extends TObs> seqIterator = sequence.iterator();
        seqIterator.next();

        for (int t = 0; t < sequence.size() - 1; t++) {
            TObs observation = seqIterator.next();

            for (int i = 0; i < hmm.nbStates(); i++) {
                for (int j = 0; j < hmm.nbStates(); j++) {
                    xi[t][i][j] = alpha[t][i] * hmm.getAij(i, j) * hmm.getOpdf(j).probability(observation) * beta[t + 1][j];
                }
            }
        }

        return xi;
    }
}
