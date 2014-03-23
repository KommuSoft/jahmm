package jahmm.toolbox;

import jahmm.InputHmm;
import jahmm.observables.InputObservationTuple;
import jahmm.observables.Observation;
import jahmm.observables.Opdf;
import jahmm.observables.TypedObservation;

/**
 *
 * @author kommusoft
 * @param <TObs> The type of observations regarding the Hidden Markov Model.
 * @param <TIn> The type of interactions regarding the Hidden Markov Model.
 * @param <THmm> The type of the Hidden Markov Model.
 */
public interface InputMarkovGenerator<TObs extends Observation, TIn, THmm extends InputHmm<TObs,TIn,THmm>> extends MarkovGenerator<TObs, InputObservationTuple<TIn, TObs>, THmm> {

    /**
     * Gets the assigned input distribution that generates input values.
     *
     * @return The assigned input distribution that generates input values.
     */
    public abstract Opdf<? extends TypedObservation<TIn>> getInputDistribution();

}
