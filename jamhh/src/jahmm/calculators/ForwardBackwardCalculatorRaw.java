package jahmm.calculators;

import jahmm.Hmm;
import jahmm.observables.Observation;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import jutlis.lists.ListArray;
import jutlis.tuples.Tuple3;

/**
 * A utility class that delegates a large number of methods to a small set of
 * methods that need to be implemented.
 *
 * @author kommusoft
 * @param <TAlpha> The type of alpha (in most cases a nD-array of doubles).
 * @param <TBeta> The type of beta (in most cases a nD-array of doubles;
 * equivalent to TAlpha).
 * @param <TObs>
 * @param <TInt>
 * @param <THmm>
 */
public abstract class ForwardBackwardCalculatorRaw<TAlpha, TBeta, TObs extends Observation, TInt extends Observation, THmm extends Hmm<TObs, TInt>> implements ForwardBackwardCalculator<TAlpha, TBeta, TObs, TInt, THmm> {

    @Override
    public TAlpha computeAlpha(THmm hmm, TInt... oseq) {
        return this.computeAlpha(hmm, new ListArray<>(oseq));
    }

    @Override
    public TBeta computeBeta(THmm hmm, TInt... oseq) {
        return this.computeBeta(hmm, new ListArray<>(oseq));
    }

    @Override
    public Tuple3<TAlpha, TBeta, Double> computeAll(THmm hmm, TInt... oseq) {
        return this.computeAll(hmm, new ListArray<>(oseq));
    }

    /**
     * Computes the probability of occurrence of an observation sequence given a
     * Hidden Markov Model. This computation computes the <code>alpha</code>
     * array as a side effect.
     *
     * @param <TInt>
     * @param oseq
     * @param hmm
     * @return
     * @see #ForwardBackwardCalculator(List, Hmm, EnumSet)
     */
    @Override
    public double computeProbability(THmm hmm, List<? extends TInt> oseq) {
        return computeProbability(hmm, EnumSet.of(ComputationType.ALPHA), oseq);
    }

    /**
     * Computes the probability of occurrence of an observation sequence given a
     * Hidden Markov Model. This computation computes the <code>alpha</code>
     * array as a side effect.
     *
     * @param <TInt>
     * @param oseq
     * @param flags determines which tasks should be executed by the calculator
     * @param hmm
     * @return
     * @see #ForwardBackwardCalculator(List, Hmm, EnumSet)
     */
    @Override
    public double computeProbability(THmm hmm, Collection<ComputationType> flags, TInt... oseq) {
        return this.computeProbability(hmm, flags, new ListArray<>(oseq));
    }

    /**
     * Computes the probability of occurrence of an observation sequence given a
     * Hidden Markov Model. This computation computes the <code>alpha</code>
     * array as a side effect.
     *
     * @param <TInt>
     * @param oseq
     * @param hmm
     * @return
     * @see #ForwardBackwardCalculator(List, Hmm, EnumSet)
     */
    @Override
    public double computeProbability(THmm hmm, TInt... oseq) {
        return this.computeProbability(hmm, new ListArray<>(oseq));
    }

}
