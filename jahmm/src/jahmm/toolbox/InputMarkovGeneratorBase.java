package jahmm.toolbox;

import jahmm.InputHmm;
import jahmm.observables.InputObservationTuple;
import jahmm.observables.Observation;
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

    @Override
    public void newSequence() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public InputObservationTuple<TIn, TObs> interaction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<InputObservationTuple<TIn, TObs>> interactionSequence(int length) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
