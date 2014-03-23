package jahmm.toolbox;

import jahmm.Hmm;
import jahmm.observables.Observation;
import java.util.List;

/**
 *
 * An interface that enables generating a sequence of interactions based on a
 * given Hidden Markov Model.
 *
 * @author kommusoft
 * @param <TObs> The type of observations regarding the Hidden Markov Model.
 * @param <TInt> The type of interactions regarding the Hidden Markov Model.
 * @param <THmm> The type of the Hidden Markov Model.
 */
public interface MarkovGenerator<TObs extends Observation, TInt extends Observation, THmm extends Hmm<TObs, TInt>> {

    /**
     * Finds a new state according to the initial (pi) probabilities of each
     * state.
     */
    void newSequence();

    /**
     * Generates a new (pseudo) random interaction.
     *
     * @return The generated observation.
     */
    TInt interaction();

    /**
     * Generates a new (pseudo) random interaction sequence and start a new one.
     *
     * @param length The length of the sequence.
     * @return An observation sequence.
     */
    List<TInt> interactionSequence(int length);

    /**
     * Returns the state number of the current state.
     *
     * @return A state number.
     */
    int stateNb();

    /**
     * Gets the stored Hidden Markov Model.
     *
     * @return The stored hidden Markov Model.
     */
    THmm getHmm();

}
