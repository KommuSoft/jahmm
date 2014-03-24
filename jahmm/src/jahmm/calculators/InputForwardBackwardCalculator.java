package jahmm.calculators;

import jahmm.InputHmm;
import jahmm.observables.InputObservationTuple;
import jahmm.observables.Observation;

/**
 *
 * @author kommusoft
 * @param <TObs>
 * @param <TInput>
 */
public interface InputForwardBackwardCalculator<TObs extends Observation, TInput, THmm extends InputHmm<TObs,TInput,THmm>> extends ForwardBackwardCalculator<double[][], double[][], TObs, InputObservationTuple<TInput, TObs>, THmm> {

}
