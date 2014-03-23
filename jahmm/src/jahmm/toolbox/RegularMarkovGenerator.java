package jahmm.toolbox;

import jahmm.RegularHmm;
import jahmm.observables.Observation;
import java.util.List;

/**
 * An interface describing a tool to generate a list of observations based on a
 * Regular Hidden Markov Model.
 *
 * @author kommusoft
 * @param <TObs> The type of observations regarding the Hidden Markov Model.
 */
public interface RegularMarkovGenerator<TObs extends Observation> extends MarkovGenerator<TObs, TObs, RegularHmm<TObs>> {

    /**
     * Generates a new (pseudo) random observation.
     *
     * @return The generated observation.
     */
    TObs observation();

    /**
     * Generates a new (pseudo) random observation sequence and start a new one.
     *
     * @param length The length of the sequence.
     * @return An observation sequence.
     */
    List<TObs> observationSequence(int length);

}
