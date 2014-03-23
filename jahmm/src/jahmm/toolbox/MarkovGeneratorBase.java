package jahmm.toolbox;

import jahmm.Hmm;
import jahmm.observables.InputObservationTuple;
import jahmm.observables.Observation;
import java.util.ArrayList;
import java.util.List;
import jutils.probability.ProbabilityUtils;

/**
 *
 * @author kommusoft
 * @param <TObs> The type of observations regarding the Hidden Markov Model.
 * @param <TInt> The type of interactions regarding the Hidden Markov Model.
 * @param <THmm> The type of the Hidden Markov Model.
 */
public abstract class MarkovGeneratorBase<TObs extends Observation, TInt extends Observation, THmm extends Hmm<TObs, TInt>> implements MarkovGenerator<TObs, TInt, THmm> {

    protected final THmm hmm;
    protected int stateNb;

    protected MarkovGeneratorBase(THmm hmm) {
        if (hmm == null) {
            throw new IllegalArgumentException("Invalid null HMM");
        }

        this.hmm = hmm;
        newSequence();
    }

    /**
     * Returns the state number of the current state.
     *
     * @return A state number.
     */
    @Override
    public int stateNb() {
        return stateNb;
    }

    /**
     * Finds a new state according to the initial (pi) probabilities of each
     * state.
     */
    @Override
    public void newSequence() {
        int index = ProbabilityUtils.getRandomIndexScaled(hmm.getPis());
        if (index < 0x00) {
            index = this.hmm.nbStates() - 1;
        }
        this.stateNb = index;
    }

    /**
     * Gets the stored Hidden Markov Model.
     *
     * @return The stored hidden Markov Model.
     */
    @Override
    public THmm getHmm() {
        return this.hmm;
    }

    /**
     * Generates a new (pseudo) random observation sequence and start a new one.
     *
     * @param length The length of the sequence.
     * @return An observation sequence.
     */
    @Override
    public List<TInt> interactionSequence(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Positive length required");
        }
        ArrayList<TInt> sequence = new ArrayList<>(length);
        while (length-- > 0) {
            sequence.add(interaction());
        }
        newSequence();
        return sequence;
    }

}
