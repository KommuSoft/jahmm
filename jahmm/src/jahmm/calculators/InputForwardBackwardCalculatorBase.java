package jahmm.calculators;

import jahmm.InputHmm;
import jahmm.observables.InputObservationTuple;
import jahmm.observables.Observation;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/**
 * An alpha-beta calculator that calculates the alpha and beta values for a
 * given InputHiddenMarkovModel. This calculator is based on the work of Falko
 * Bause.
 *
 * @author kommusoft
 * @param <TObs>
 * @param <TInt>
 */
public class InputForwardBackwardCalculatorBase<TObs extends Observation, TInt, THmm extends InputHmm<TObs,TInt,THmm>> extends ForwardBackwardCalculatorRaw<double[][], double[][], TObs, InputObservationTuple<TInt, TObs>,THmm> implements InputForwardBackwardCalculator<TObs, TInt,THmm> {

    public static final InputForwardBackwardCalculatorBase Instance = new InputForwardBackwardCalculatorBase();

    private static final Logger LOG = Logger.getLogger(InputForwardBackwardCalculatorBase.class.getName());

    protected InputForwardBackwardCalculatorBase() {
    }

    @Override
    public double[][] computeAlpha(THmm hmm, Collection<? extends InputObservationTuple<TInt, TObs>> oseq) {//TODO: mod?
        int T = oseq.size();
        int s = hmm.nbStates();
        double[][] alpha = new double[T][s];
        T--;

        Iterator<? extends InputObservationTuple<TInt, TObs>> seqIterator = oseq.iterator();
        InputObservationTuple<TInt, TObs> observation;
        if (seqIterator.hasNext()) {
            observation = seqIterator.next();

            for (int i = 0; i < hmm.nbStates(); i++) {
                alpha[0][i] = hmm.getPi(i) * hmm.getOpdf(i, observation.getInput()).probability(observation.getObservation());
            }

            for (int t = 0; t < T; t++) {
                observation = seqIterator.next();

                for (int j = 0; j < s; j++) {
                    double sum = 0.;
                    for (int i = 0; i < s; i++) {
                        sum += alpha[t][i] * hmm.getAixj(i, observation.getItem1(), j);
                    }
                    alpha[t + 0x01][j] = sum * hmm.getOpdf(j, observation.getInput()).probability(observation.getObservation());
                }
            }
        }
        return alpha;
    }

    @Override
    public double[][] computeBeta(THmm hmm, List<? extends InputObservationTuple<TInt, TObs>> oseq) {
        int t = oseq.size();
        int s = hmm.nbStates();
        double[][] beta = new double[t][s];
        t--;
        InputObservationTuple<TInt, TObs> observation;

        for (int i = 0; i < s; i++) {
            beta[t][i] = 1.0d;
        }

        for (; t > 0;) {
            for (int i = 0; i < s; i++) {
                observation = oseq.get(t);
                double sum = 0.0d;
                for (int j = 0; j < s; j++) {
                    sum += beta[t][j] * hmm.getAixj(i, observation.getItem1(), j) * hmm.getOpdf(j, observation.getInput()).probability(observation.getObservation());
                }
                beta[t - 0x01][i] = sum;
            }
            t--;
        }
        return beta;
    }

    @Override
    protected double computeProbability(List<? extends InputObservationTuple<TInt, TObs>> oseq, THmm hmm, Collection<ComputationType> flags, double[][] alpha, double[][] beta) {
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
            InputObservationTuple<TInt, TObs> observation = oseq.get(0x00);
            for (int i = 0; i < n; i++) {
                probability += hmm.getPi(i) * hmm.getOpdf(i, observation.getInput()).probability(observation.getObservation()) * tmp[i];
            }
        }
        return probability;
    }

}
