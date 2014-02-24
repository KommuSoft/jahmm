package jahmm.calculators;

import jahmm.RegularHmmBase;
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
public interface ForwardBackwardCalculator<TAlpha, TBeta> {

    public abstract <O extends Observation> TAlpha computeAlpha(RegularHmmBase<? super O> hmm, Collection<O> oseq);

    public abstract <O extends Observation> TAlpha computeAlpha(RegularHmmBase<? super O> hmm, O... oseq);

    public abstract <O extends Observation> TBeta computeBeta(RegularHmmBase<? super O> hmm, List<O> oseq);

    public abstract <O extends Observation> TBeta computeBeta(RegularHmmBase<? super O> hmm, O... oseq);

    public abstract <O extends Observation> Tuple3<TAlpha, TBeta, Double> computeAll(RegularHmmBase<? super O> hmm, List<O> oseq);

    public abstract <O extends Observation> Tuple3<TAlpha, TBeta, Double> computeAll(RegularHmmBase<? super O> hmm, O... oseq);

    public abstract <O extends Observation> double computeProbability(RegularHmmBase<O> hmm, Collection<ComputationType> flags, List<? extends O> oseq);

    public abstract <O extends Observation> double computeProbability(RegularHmmBase<O> hmm, List<? extends O> oseq);

    public abstract <O extends Observation> double computeProbability(RegularHmmBase<O> hmm, Collection<ComputationType> flags, O... oseq);

    public abstract <O extends Observation> double computeProbability(RegularHmmBase<O> hmm, O... oseq);

}
