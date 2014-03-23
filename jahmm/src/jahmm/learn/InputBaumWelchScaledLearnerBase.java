package jahmm.learn;

import jahmm.InputHmm;
import jahmm.calculators.ForwardBackwardCalculator;
import jahmm.calculators.InputForwardBackwardScaledCalculatorBase;
import jahmm.observables.InputObservationTuple;
import jahmm.observables.Observation;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import jutlis.tuples.Tuple3;

/**
 *
 * @author kommusoft
 * @param <TObservation> The type of observations regarding the Hidden Markov
 * Model.
 * @param <TInteraction> The type of interactions regarding the Hidden Markov
 * Model.
 */
public class InputBaumWelchScaledLearnerBase<TObservation extends Observation, TInteraction> extends InputBaumWelchLearnerBase<TObservation, TInteraction> {

    private static final Logger LOG = Logger.getLogger(RegularBaumWelchScaledLearnerBase.class.getName());

    /**
     * Initializes a Baum-Welch algorithm implementation.
     */
    public InputBaumWelchScaledLearnerBase() {
    }

    /**
     * Gets the relevant calculator for the Baum-Welch learner.
     *
     * @return The relevant calculator for the Baum-Welch learner.
     */
    @Override
    @SuppressWarnings("unchecked")
    protected ForwardBackwardCalculator<double[][], double[][], TObservation, InputObservationTuple<TInteraction, TObservation>, InputHmm<TObservation, TInteraction>> getCalculator() {
        return InputForwardBackwardScaledCalculatorBase.Instance;
    }

    /**
     * Here, the xi (and, thus, gamma) values are not divided by the probability
     * of the sequence because this probability might be too small and induce an
     * underflow. xi[t][i][j] still can be interpreted as P[q_t = i and q_(t+1)
     * = j | obsSeq, hmm] because we assume that the scaling factors are such
     * that their product is equal to the inverse of the probability of the
     * sequence.
     *
     * @param sequence The given sequence to estimate the Xi values for.
     * @param abp A tuple containing the alpha- and beta-values and the
     * probability of the sequence.
     * @param hmm The hidden Markov Model.
     * @return The estimated xi-values.
     */
    @Override
    protected double[][][] estimateXi(List<? extends InputObservationTuple<TInteraction, TObservation>> sequence, Tuple3<double[][], double[][], Double> abp, InputHmm<TObservation, TInteraction> hmm) {
        if (sequence.size() <= 1) {
            throw new IllegalArgumentException("Observation sequence too short");
        }
        double[][] a = abp.getItem1();
        double[][] b = abp.getItem2();
        double xi[][][] = new double[sequence.size() - 1][hmm.nbStates()][hmm.nbStates()];
        Iterator<? extends InputObservationTuple<TInteraction, TObservation>> seqIterator = sequence.iterator();
        seqIterator.next();
        for (int t = 0; t < sequence.size() - 1; t++) {
            InputObservationTuple<TInteraction, TObservation> interaction = seqIterator.next();
            for (int i = 0; i < hmm.nbStates(); i++) {
                for (int j = 0; j < hmm.nbStates(); j++) {
                    xi[t][i][j] = a[t][i] * hmm.getAixj(i, interaction.getInput(), j) * hmm.getOpdf(j, interaction.getInput()).probability(interaction.getObservation()) * b[t + 1][j];
                }
            }
        }

        return xi;
    }

}
