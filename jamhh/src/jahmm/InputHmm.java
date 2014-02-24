package jahmm;

import jahmm.observables.InputObservationTuple;
import jahmm.observables.Observation;

/**
 *
 * @author kommusoft
 * @param <TIn> The type of input of the InputHmm.
 * @param <TOut> The type of observations of the InputHmm.
 */
public interface InputHmm<TIn, TOut extends Observation> extends Hmm<TOut, InputObservationTuple<TIn, TOut>> {

    /**
     *
     * @param originalIn
     * @param newIns
     */
    public abstract void splitInput(TIn originalIn, TIn... newIns);

    public abstract void mergeInput(TIn newIn, TIn originalIns);

}
