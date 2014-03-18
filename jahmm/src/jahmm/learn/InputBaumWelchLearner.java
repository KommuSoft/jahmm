package jahmm.learn;

import jahmm.InputHmm;
import jahmm.observables.InputObservationTuple;
import jahmm.observables.Observation;

/**
 *
 * @author kommusoft
 * @param <TObs> The type of observations regarding the Hidden Markov Model.
 * @param <TIn> The type of interactions regarding the Hidden Markov Model.
 */
public interface InputBaumWelchLearner<TObs extends Observation, TIn extends Enum<TIn>> extends BaumWelchLearner<TObs, InputObservationTuple<TIn, TObs>, InputHmm<TObs, TIn>> {
    
}
