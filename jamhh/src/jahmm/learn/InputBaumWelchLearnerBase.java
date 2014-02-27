package jahmm.learn;

import jahmm.InputHmm;
import jahmm.calculators.ForwardBackwardCalculator;
import jahmm.calculators.InputForwardBackwardCalculatorBase;
import jahmm.observables.InputObservationTuple;
import jahmm.observables.Observation;
import java.util.List;


public class InputBaumWelchLearnerBase<TObs extends Observation, TInp extends Enum<TInp>> extends BaumWelchLearnerBase<TObs, InputObservationTuple<TInp,TObs>, InputHmm<TObs,TInp>,double[],double[]> implements InputBaumWelchLearner<TObs,TInp> {

    @Override
    public InputHmm<TObs, TInp> iterate(InputHmm<TObs, TInp> hmm, List<? extends List<? extends InputObservationTuple<TInp, TObs>>> sequences) {
        return null;
    }

    @Override
    public InputHmm<TObs, TInp> learn(InputHmm<TObs, TInp> initialHmm, List<? extends List<? extends InputObservationTuple<TInp, TObs>>> sequences) {
        return null;
    }

    @Override
    protected ForwardBackwardCalculator<double[], double[], TObs, InputObservationTuple<TInp, TObs>, InputHmm<TObs, TInp>> getCalculator() {
        return InputForwardBackwardCalculatorBase.Instance;
    }
    
}
