package jahmm;

import jahmm.observables.InputObservationTuple;
import jahmm.observables.Observation;

/**
 *
 * @author kommusoft
 * @param <TIn> The type of input of the InputHmm.
 * @param <TOut> The type of observations of the InputHmm.
 */
public interface InputHmm<TIn extends Enum<TIn>, TOut extends Observation> extends Hmm<TOut, InputObservationTuple<TIn, TOut>> {

    public abstract void splitInput(final TIn originalIn, final TIn... newIns) throws IllegalArgumentException;

    public abstract void mergeInput(final TIn newIn, final TIn... originalIns) throws IllegalArgumentException;

    public abstract int getInputIndex(TIn input);

}
