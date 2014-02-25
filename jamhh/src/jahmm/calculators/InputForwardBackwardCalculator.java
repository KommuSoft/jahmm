package jahmm.calculators;

import jahmm.InputHmm;
import jahmm.observables.Observation;

/**
 *
 * @author kommusoft
 */
public interface InputForwardBackwardCalculator<TObs extends Observation, TIns extends Observation> extends ForwardBackwardCalculator<double[][][],double[][][],TObs,TIns,InputHmm<TObs,TIns>> {
    
}
