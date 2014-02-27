package jahmm;

import jahmm.observables.InputObservationTuple;
import jahmm.observables.Observation;

/**
 *
 * @author kommusoft
 * @param <TIn> The type of input of the InputHmm.
 * @param <TObs> The type of observations of the InputHmm.
 */
public interface InputHmm<TObs extends Observation, TIn extends Enum<TIn>> extends Hmm<TObs, InputObservationTuple<TIn, TObs>> {

    public abstract void splitInput(final TIn originalIn, final TIn... newIns) throws IllegalArgumentException;

    public abstract void mergeInput(final TIn newIn, final TIn... originalIns) throws IllegalArgumentException;

    //public abstract void resetInput(final Iterable<TIn> inputs);

    //public abstract void resetInput(final Class<TIn> enumClass);

    public abstract int getInputIndex(TIn input);

    /**
     * Returns the probability of going from hidden state <i>i</i> to hidden
     * state <i>j</i> given input <i>x</i>.
     *
     * @param i The initial state.
     * @param x The given input.
     * @param j The final state.
     * @return The probability of going from hidden state <i>i</i> to hidden
     * state <i>j</i> given input <i>x</i>.
     */
    public abstract double getAixj(int i, TIn x, int j);

}
