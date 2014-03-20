package jahmm.calculators;

import jahmm.InputHmm;
import jahmm.observables.InputObservationTuple;
import jahmm.observables.Observation;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import jutils.probability.ProbabilityUtils;
import jutlis.tuples.Tuple3;
import jutlis.tuples.Tuple3Base;

public final class InputForwardBackwardScaledCalculatorBase<TObs extends Observation, TInt> extends InputForwardBackwardCalculatorBase<TObs, TInt> {

    private static final Logger LOG = Logger.getLogger(InputForwardBackwardScaledCalculatorBase.class.getName());

    public static final InputForwardBackwardScaledCalculatorBase Instance = new InputForwardBackwardScaledCalculatorBase();

    private InputForwardBackwardScaledCalculatorBase() {
    }

    private double computeProbability(double[] ctFactors) {
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
     * @param <TObs>
     * @param hmm A Hidden Markov Model;
     * @param oseq An observations sequence.
     * @param flags How the computation should be done. See the
     * {@link ForwardBackwardCalculator.ComputationType}. The alpha array is
     * always computed.
     * @return The probability of the given sequence of observations.
     */
    @Override
    public double computeProbability(InputHmm<TObs, TInt> hmm, Collection<ComputationType> flags, List<? extends InputObservationTuple<TInt, TObs>> oseq) {
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
    public double[][] computeAlpha(InputHmm<TObs, TInt> hmm, Collection<? extends InputObservationTuple<TInt, TObs>> oseq, double... ctFactors) {
        int T = ctFactors.length;
        int s = hmm.nbStates();
        Iterator<? extends InputObservationTuple<TInt, TObs>> seqIterator = oseq.iterator();
        double[][] alpha = new double[T][s];
        if (seqIterator.hasNext()) {

            InputObservationTuple<TInt, TObs> observation = seqIterator.next();

            for (int i = 0x00; i < s; i++) {
                alpha[0x00][i] = hmm.getPi(i) * hmm.getOpdf(i, observation.getInput()).probability(observation.getObservation());
            }

            ctFactors[0x00] = ProbabilityUtils.scale(alpha[0x00]);

            for (int t = 1; t < T; t++) {
                observation = seqIterator.next();

                for (int i = 0; i < s; i++) {
                    double sum = 0.0d;
                    for (int j = 0; j < s; j++) {
                        sum += alpha[t - 1][j] * hmm.getAixj(j, observation.getItem1(), i);
                    }
                    alpha[t][i] = sum * hmm.getOpdf(i, observation.getInput()).probability(observation.getObservation());
                }
                ctFactors[t] = ProbabilityUtils.scale(alpha[t]);
            }
        }
        return alpha;
    }

    /* Computes the content of the scaled beta array.  The scaling factors are
     those computed for alpha. */
    public double[][] computeBeta(InputHmm<TObs, TInt> hmm, List<? extends InputObservationTuple<TInt, TObs>> oseq, double... ctFactors) {
        int T = ctFactors.length;
        int s = hmm.nbStates();
        double[][] beta = new double[T][s];
        for (int i = 0; i < s; i++) {
            beta[T - 1][i] = 1.0d / ctFactors[T - 1];
        }

        for (int t = T - 2; t >= 0; t--) {
            InputObservationTuple<TInt, TObs> observation = oseq.get(t + 1);
            for (int i = 0; i < s; i++) {
                double sum = 0.;
                for (int j = 0; j < s; j++) {
                    sum += beta[t + 1][j] * hmm.getAixj(i, observation.getItem1(), j) * hmm.getOpdf(j, observation.getInput()).probability(observation.getObservation());
                }
                beta[t][i] = sum;
                beta[t][i] /= ctFactors[t];
            }
        }
        return beta;
    }

    @Override
    public Tuple3<double[][], double[][], Double> computeAll(InputHmm<TObs, TInt> hmm, List<? extends InputObservationTuple<TInt, TObs>> oseq) {
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
