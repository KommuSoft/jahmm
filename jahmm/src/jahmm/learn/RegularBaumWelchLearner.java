package jahmm.learn;

import jahmm.RegularHmm;
import jahmm.observables.Observation;

/**
 *
 * @author kommusoft
 * @param <TObs> The type of observations regarding the Regular Hidden Markov Model.
 */
public interface RegularBaumWelchLearner<TObs extends Observation> extends BaumWelchLearner<TObs, TObs, RegularHmm<TObs>> {

}
