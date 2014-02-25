package jahmm;

import jahmm.observables.InputObservationTuple;
import jahmm.observables.Observation;

/**
 *
 * @author kommusoft
 * @param <TIn> The type of input of the InputHmm.
 * @param <TObs> The type of observations of the InputHmm.
 */
public interface InputHmm<TObs extends Observation,TIn extends Enum<TIn>> extends Hmm<TObs, InputObservationTuple<TIn, TObs>> {

    public abstract void splitInput(final TIn originalIn, final TIn... newIns) throws IllegalArgumentException;

    public abstract void mergeInput(final TIn newIn, final TIn... originalIns) throws IllegalArgumentException;

    public abstract int getInputIndex(TIn input);

}
