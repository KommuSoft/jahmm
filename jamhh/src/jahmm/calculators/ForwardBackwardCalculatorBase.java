package jahmm.calculators;

import jahmm.Hmm;
import jahmm.RegularHmm;
import jahmm.observables.Observation;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import jutlis.lists.ListArray;
import jutlis.tuples.Tuple3;
import jutlis.tuples.Tuple3Base;

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
public abstract class ForwardBackwardCalculatorBase<TAlpha, TBeta, TObs extends Observation, TInt extends Observation, THmm extends Hmm<TObs, TInt>> implements ForwardBackwardCalculator<TAlpha, TBeta, TObs, TInt, THmm> {

    protected ForwardBackwardCalculatorBase() {
    }

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

    protected abstract double computeProbability(List<? extends TInt> oseq, THmm hmm, Collection<ComputationType> flags, TAlpha alpha, TBeta beta);

    @Override
    public Tuple3<TAlpha, TBeta, Double> computeAll(THmm hmm, List<? extends TInt> oseq) {
        TAlpha alpha = computeAlpha(hmm, oseq);
        TBeta beta = computeBeta(hmm, oseq);
        double probability = computeProbability(oseq, hmm, EnumSet.of(ComputationType.ALPHA), alpha, beta);
        return new Tuple3Base<>(alpha, beta, probability);
    }

    /**
     * Computes the probability of occurrence of an observation sequence given a
     * Hidden Markov Model.
     *
     * @param <TObs>
     * @param hmm A Hidden Markov Model;
     * @param oseq An observation sequence.
     * @param flags How the computation should be done. See the
     * {@link ComputationType ComputationType} enum.
     * @return
     */
    @Override
    public double computeProbability(THmm hmm, Collection<ComputationType> flags, List<? extends TInt> oseq) {
        if (oseq.isEmpty()) {
            throw new IllegalArgumentException("Invalid empty sequence");
        }

        TAlpha alpha = null;
        TBeta beta = null;

        if (flags.contains(ComputationType.ALPHA)) {
            alpha = computeAlpha(hmm, oseq);
        }

        if (flags.contains(ComputationType.BETA)) {
            beta = computeBeta(hmm, oseq);
        }

        return computeProbability(oseq, hmm, flags, alpha, beta);
    }

}
