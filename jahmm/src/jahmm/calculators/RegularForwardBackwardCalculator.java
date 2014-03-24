package jahmm.calculators;

import jahmm.RegularHmm;
import jahmm.observables.Observation;

/**
 *
 * @author kommusoft
 */
public interface RegularForwardBackwardCalculator<TObs extends Observation, THmm extends RegularHmm<TObs,THmm>> extends ForwardBackwardCalculator<double[][], double[][], TObs, TObs, THmm> {

}
