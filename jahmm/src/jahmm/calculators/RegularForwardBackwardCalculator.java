package jahmm.calculators;

import jahmm.RegularHmm;
import jahmm.observables.Observation;

/**
 *
 * @author kommusoft
 */
public interface RegularForwardBackwardCalculator<TObs extends Observation> extends ForwardBackwardCalculator<double[][], double[][], TObs, TObs, RegularHmm<TObs>> {

}
