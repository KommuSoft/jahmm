package jahmm.learn;

import jahmm.RegularHmm;
import jahmm.observables.Observation;

/**
 *
 * @author kommusoft
 */
public interface RegularBaumWelchLearner<TObs extends Observation> extends BaumWelchLearner<TObs, TObs, RegularHmm<TObs>> {

}
