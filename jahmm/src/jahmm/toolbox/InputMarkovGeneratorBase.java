package jahmm.toolbox;

import jahmm.InputHmm;
import jahmm.observables.InputObservationTuple;
import jahmm.observables.Observation;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author kommusoft
 * @param <TObs> The type of observations regarding the Hidden Markov Model.
 * @param <TIn> The type of interactions regarding the Hidden Markov Model.
 */
public class InputMarkovGeneratorBase<TObs extends Observation, TIn> extends MarkovGeneratorBase<TObs, InputObservationTuple<TIn, TObs>, InputHmm<TObs, TIn>> implements InputMarkovGenerator<TObs, TIn> {

    private static final Logger LOG = Logger.getLogger(InputMarkovGeneratorBase.class.getName());

    public InputMarkovGeneratorBase(InputHmm<TObs, TIn> hmm) {
        super(hmm);
    }

    /**
     * Generates a new (pseudo) random observation.
     *
     * @return The generated observation.
     */
    @Override
    public InputObservationTuple<TIn, TObs> interaction() {
        /*TObs o = hmm.getOpdf(stateNb).generate();
         double rand = Math.random();
         for (int j = 0; j < hmm.nbStates() - 1; j++) {
         if ((rand -= hmm.getAij(stateNb, j)) < 0) {
         stateNb = j;
         return o;
         }
         }

         stateNb = hmm.nbStates() - 1;
         return o;*/
        return null;
    }

    /**
     * Generates a new (pseudo) random observation sequence and start a new one.
     *
     * @param length The length of the sequence.
     * @return An observation sequence.
     */
    @Override
    public List<InputObservationTuple<TIn, TObs>> interactionSequence(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Positive length required");
        }
        ArrayList<InputObservationTuple<TIn, TObs>> sequence = new ArrayList<>(length);
        while (length-- > 0) {
            sequence.add(interaction());
        }
        newSequence();
        return sequence;
    }

}
