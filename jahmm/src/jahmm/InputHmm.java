package jahmm;

import jahmm.observables.InputObservationTuple;
import jahmm.observables.Observation;
import jahmm.observables.Opdf;
import jutils.Tagable;

/**
 * An interface describing an Input Hidden Markov Model as formulated by Falko
 * Bause. Falko Bause defines in "Input Output Hidden Markov Models: for the
 * Aggregation of Performance Models" an Input Hidden Markov Model: a Markov
 * Model where in each state the next state and the observation depends on an
 * input from a finite domain.
 *
 * @author kommusoft
 * @param <TIn> The type of input of the InputHmm.
 * @param <TObs> The type of observations of the InputHmm.
 */
public interface InputHmm<TObs extends Observation, TIn extends Enum<TIn>> extends Hmm<TObs, InputObservationTuple<TIn, TObs>> {

    /**
     * Split the original given input into a list of new (expected) set of
     * input. The probability values associated with the new inputs are
     * considered to be equal to the original input.
     *
     * @param originalIn The original given input.
     * @param newIns The new (expected) inputs.
     * @throws IllegalArgumentException If the given original input is not
     * effective or not registered in the Hidden Markov Model.
     * @throws IllegalArgumentException If the given new inputs are not
     * effective or already registered.
     */
    public abstract void splitInput(final TIn originalIn, final TIn... newIns) throws IllegalArgumentException;

    /**
     * Merges the given list of original inputs into a new (expected) input. The
     * probability values associated with the given list of the original inputs
     * are used to calculate averages that are used by the new input.
     *
     * @param newIn The new (expected) input to be registered.
     * @param originalIns The original inputs to merge.
     * @throws IllegalArgumentException If the given new input is already
     * registered or not effective.
     * @throws IllegalArgumentException If the given list of original inputs is
     * not effective or one of the original inputs is not effective or not
     * registered.
     */
    public abstract void mergeInput(final TIn newIn, final TIn... originalIns) throws IllegalArgumentException;

    /**
     * Gets the index associated with the given input.
     *
     * @param input The given input.
     * @return The index associated with the given input.
     */
    public abstract int getInputIndex(TIn input);

    /**
     * Gets the index associated with the input stored in the tag of the given
     * input.
     *
     * @param input The given input.
     * @return The index associated with the input stored in the tag of the
     * given input.
     */
    public abstract int getInputIndex(Tagable<TIn> input);

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

    /**
     * Returns the probability of going from hidden state <i>i</i> to hidden
     * state <i>j</i> given input <i>x</i>.
     *
     * @param i The initial state.
     * @param x The index of the given input.
     * @param j The final state.
     * @return The probability of going from hidden state <i>i</i> to hidden
     * state <i>j</i> given input <i>x</i>.
     */
    public abstract double getAixj(int i, int x, int j);

    /**
     * Returns the probability of going from hidden state <i>i</i> to hidden
     * state <i>j</i> given input <i>x</i>.
     *
     * @param i The initial state.
     * @param x A tagable containing given input.
     * @param j The final state.
     * @return The probability of going from hidden state <i>i</i> to hidden
     * state <i>j</i> given input <i>x</i>.
     */
    public abstract double getAixj(int i, Tagable<TIn> x, int j);

    /**
     * Sets the probability of going from hidden state <i>i</i> to hidden state
     * <i>j</i> given input <i>x</i>.
     *
     * @param i The initial state.
     * @param x The given input.
     * @param j The final state.
     * @param aixj The new probability of a transition from state <i>i</i> to
     * state <i>j</i> given input <i>x</i>.
     */
    public abstract void setAixj(int i, TIn x, int j, double aixj);

    /**
     * Sets the probability of going from hidden state <i>i</i> to hidden state
     * <i>j</i> given input <i>x</i>.
     *
     * @param i The initial state.
     * @param x The index of the given input.
     * @param j The final state.
     * @param aixj The new probability of a transition from state <i>i</i> to
     * state <i>j</i> given input <i>x</i>.
     */
    public abstract void setAixj(int i, int x, int j, double aixj);

    /**
     * Sets the probability of going from hidden state <i>i</i> to hidden state
     * <i>j</i> given input <i>x</i>.
     *
     * @param i The initial state.
     * @param x A tagable containing given input.
     * @param j The final state.
     * @param aixj The new probability of a transition from state <i>i</i> to
     * state <i>j</i> given input <i>x</i>.
     */
    public abstract void setAixj(int i, Tagable<TIn> x, int j, double aixj);

    /**
     * Gets the number of input symbols.
     *
     * @return The number of input symbols.
     */
    public abstract int nbSymbols();

    /**
     * Returns the opdf associated with a given state.
     *
     * @param stateNb A state number such that
     * <code>0 &le; stateNb &lt; nbStates()</code>.
     * @param inputNb An input number such that
     * <code>0 &le; inputNb &lt; nbInputs()</code>
     * @return The opdf associated to state <code>stateNb</code>.
     */
    public abstract Opdf<TObs> getOpdf(int stateNb, int inputNb);

    /**
     * Returns the opdf associated with a given state.
     *
     * @param stateNb A state number such that
     * <code>0 &le; stateNb &lt; nbStates()</code>.
     * @param inputNb An input number such that
     * <code>0 &le; inputNb &lt; nbInputs()</code>
     * @return The opdf associated to state <code>stateNb</code>.
     */
    public abstract Opdf<TObs> getOpdf(int stateNb, TIn inputNb);

    /**
     * Returns the opdf associated with a given state.
     *
     * @param stateNb A state number such that
     * <code>0 &le; stateNb &lt; nbStates()</code>.
     * @param inputNb An input number such that
     * <code>0 &le; inputNb &lt; nbInputs()</code>
     * @return The opdf associated to state <code>stateNb</code>.
     */
    public abstract Opdf<TObs> getOpdf(int stateNb, Tagable<TIn> inputNb);

}
