/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulg.montefiore.run.jahmm;

import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import jutils.tuples.Tuple3;
import jutlis.lists.ListArray;

/**
 *
 * @author kommusoft
 */
public abstract class ForwardBackwardCalculatorBase<TAlpha, TBeta> implements AbstractForwardBackwardCalculator<TAlpha, TBeta> {

    @Override
    public <O extends Observation> TAlpha computeAlpha(Hmm<? super O> hmm, O... oseq) {
        return this.computeAlpha(hmm, new ListArray<>(oseq));
    }

    @Override
    public <O extends Observation> TBeta computeBeta(Hmm<? super O> hmm, O... oseq) {
        return this.computeBeta(hmm, new ListArray<>(oseq));
    }

    @Override
    public <O extends Observation> Tuple3<TAlpha, TBeta, Double> computeAll(Hmm<? super O> hmm, O... oseq) {
        return this.computeAll(hmm, new ListArray<>(oseq));
    }

    /**
     * Computes the probability of occurrence of an observation sequence given a
     * Hidden Markov Model. This computation computes the <code>alpha</code>
     * array as a side effect.
     *
     * @param <O>
     * @param oseq
     * @param hmm
     * @return
     * @see #ForwardBackwardCalculator(List, Hmm, EnumSet)
     */
    @Override
    public <O extends Observation> double computeProbability(Hmm<O> hmm, List<? extends O> oseq) {
        return computeProbability(hmm, EnumSet.of(ComputationType.ALPHA), oseq);
    }

    /**
     * Computes the probability of occurrence of an observation sequence given a
     * Hidden Markov Model. This computation computes the <code>alpha</code>
     * array as a side effect.
     *
     * @param <O>
     * @param oseq
     * @param flags determines which tasks should be executed by the calculator
     * @param hmm
     * @return
     * @see #ForwardBackwardCalculator(List, Hmm, EnumSet)
     */
    @Override
    public <O extends Observation> double computeProbability(Hmm<O> hmm, Collection<ComputationType> flags, O... oseq) {
        return this.computeProbability(hmm, flags, new ListArray<>(oseq));
    }

    /**
     * Computes the probability of occurrence of an observation sequence given a
     * Hidden Markov Model. This computation computes the <code>alpha</code>
     * array as a side effect.
     *
     * @param <O>
     * @param oseq
     * @param hmm
     * @return
     * @see #ForwardBackwardCalculator(List, Hmm, EnumSet)
     */
    @Override
    public <O extends Observation> double computeProbability(Hmm<O> hmm, O... oseq) {
        return this.computeProbability(hmm, new ListArray<>(oseq));
    }

}
