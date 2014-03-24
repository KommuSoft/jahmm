package jahmm.learn;

import jahmm.RegularHmm;
import jahmm.observables.Observation;

/**
 *
 * @author kommusoft
 * @param <TObs> The type of observations regarding the Regular Hidden Markov
 * Model.
 * @param <THmm> The type of the Hidden Markov Model.
 */
public interface RegularBaumWelchLearner<TObs extends Observation,THmm extends RegularHmm<TObs,THmm>> extends BaumWelchLearner<TObs, TObs, THmm> {

}
