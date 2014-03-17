package jahmm.learn;

import jahmm.InputHmm;
import jahmm.RegularHmm;
import jahmm.observables.InputObservationTuple;
import jahmm.observables.Observation;
import java.util.List;

/**
 *
 * @author kommusoft
 */
public class InputBaumWelchLearner<TObs extends Observation, TIn extends Enum<TIn>> implements BaumWelchLearner<TObs, InputObservationTuple<TIn, TObs>, InputHmm<TObs, TIn>> {

    @Override
    public int getNbIterations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RegularHmm iterate(InputHmm<TObs, TIn> hmm, List<? extends List<? extends InputObservationTuple<TIn, TObs>>> sequences) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public InputHmm<TObs, TIn> learn(InputHmm<TObs, TIn> initialHmm, List<? extends List<? extends InputObservationTuple<TIn, TObs>>> sequences) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNbIterations(int nb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
