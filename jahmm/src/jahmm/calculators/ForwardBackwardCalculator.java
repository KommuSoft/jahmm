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
public interface ForwardBackwardCalculator<TAlpha, TBeta, TObs extends Observation, TInt extends Observation, THmm extends Hmm<TObs, TInt, THmm>> {

    public abstract TAlpha computeAlpha(THmm hmm, Collection<? extends TInt> oseq);

    public abstract TAlpha computeAlpha(THmm hmm, TInt... oseq);

    public abstract TBeta computeBeta(THmm hmm, List<? extends TInt> oseq);

    public abstract TBeta computeBeta(THmm hmm, TInt... oseq);

    public abstract Tuple3<TAlpha, TBeta, Double> computeAll(THmm hmm, List<? extends TInt> oseq);

    public abstract Tuple3<TAlpha, TBeta, Double> computeAll(THmm hmm, TInt... oseq);

    public abstract double computeProbability(THmm hmm, Collection<ComputationType> flags, List<? extends TInt> oseq);

    public abstract double computeProbability(THmm hmm, List<? extends TInt> oseq);

    public abstract double computeProbability(THmm hmm, Collection<ComputationType> flags, TInt... oseq);

    public abstract double computeProbability(THmm hmm, TInt... oseq);

}
