package jahmm.calculators;

import jahmm.Hmm;
import jahmm.observables.Observation;
import java.util.Collection;
import java.util.List;
import jutlis.tuples.Tuple3;

/**
 *
 * @author kommusoft
 * @param <TAlpha> The type of the alpha values.
 * @param <TBeta> The type of the beta values.
 */
public interface AbstractForwardBackwardCalculator<TAlpha, TBeta> {

    public abstract <O extends Observation> TAlpha computeAlpha(Hmm<? super O> hmm, Collection<O> oseq);

    public abstract <O extends Observation> TAlpha computeAlpha(Hmm<? super O> hmm, O... oseq);

    public abstract <O extends Observation> TBeta computeBeta(Hmm<? super O> hmm, List<O> oseq);

    public abstract <O extends Observation> TBeta computeBeta(Hmm<? super O> hmm, O... oseq);

    public abstract <O extends Observation> Tuple3<TAlpha, TBeta, Double> computeAll(Hmm<? super O> hmm, List<O> oseq);

    public abstract <O extends Observation> Tuple3<TAlpha, TBeta, Double> computeAll(Hmm<? super O> hmm, O... oseq);

    public abstract <O extends Observation> double computeProbability(Hmm<O> hmm, Collection<ComputationType> flags, List<? extends O> oseq);

    public abstract <O extends Observation> double computeProbability(Hmm<O> hmm, List<? extends O> oseq);

    public abstract <O extends Observation> double computeProbability(Hmm<O> hmm, Collection<ComputationType> flags, O... oseq);

    public abstract <O extends Observation> double computeProbability(Hmm<O> hmm, O... oseq);

}
