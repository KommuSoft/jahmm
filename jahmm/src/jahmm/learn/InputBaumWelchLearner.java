package jahmm.learn;

import jahmm.InputHmm;
import jahmm.observables.InputObservationTuple;
import jahmm.observables.Observation;

/**
 *
 * @author kommusoft
 * @param <TObs> The type of observations regarding the Hidden Markov Model.
 * @param <TIn> The type of interactions regarding the Hidden Markov Model.
 * @param <THmm> The type of the Hidden Markov Model.
 */
public interface InputBaumWelchLearner<TObs extends Observation, TIn, THmm extends InputHmm<TObs, TIn, THmm>> extends BaumWelchLearner<TObs, InputObservationTuple<TIn, TObs>, THmm> {

}
