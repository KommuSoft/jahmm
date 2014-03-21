package jahmm.calculators;

import jahmm.InputHmm;
import jahmm.observables.InputObservationTuple;
import jahmm.observables.Observation;

/**
 *
 * @author kommusoft
 * @param <TObs>
 * @param <TInt>
 */
public interface InputForwardBackwardCalculator<TObs extends Observation, TInt> extends ForwardBackwardCalculator<double[][], double[][], TObs, InputObservationTuple<TInt, TObs>, InputHmm<TObs, TInt>> {

}
