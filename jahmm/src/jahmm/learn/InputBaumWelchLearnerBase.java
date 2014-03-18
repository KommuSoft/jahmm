package jahmm.learn;

import jahmm.InputHmm;
import jahmm.calculators.ForwardBackwardCalculator;
import jahmm.calculators.InputForwardBackwardCalculatorBase;
import jahmm.observables.InputObservationTuple;
import jahmm.observables.Observation;
import java.util.List;

/**
 *
 * @author kommusoft
 */
public class InputBaumWelchLearnerBase<TObs extends Observation, TInt extends Enum<TInt>> extends BaumWelchLearnerBase<TObs, InputObservationTuple<TInt, TObs>, InputHmm<TObs, TInt>, double[][], double[][]> implements InputBaumWelchLearner<TObs, TInt> {

    @Override
    public InputHmm<TObs, TInt> iterate(InputHmm<TObs, TInt> hmm, List<? extends List<? extends InputObservationTuple<TInt, TObs>>> sequences) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @SuppressWarnings("unchecked")
    protected ForwardBackwardCalculator<double[][], double[][], TObs, InputObservationTuple<TInt, TObs>, InputHmm<TObs, TInt>> getCalculator() {
        return InputForwardBackwardCalculatorBase.Instance;
    }

}
