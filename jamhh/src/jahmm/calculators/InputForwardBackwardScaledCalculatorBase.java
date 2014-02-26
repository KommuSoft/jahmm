package jahmm.calculators;

import jahmm.observables.Observation;
import java.util.logging.Logger;

public final class InputForwardBackwardScaledCalculatorBase<TObs extends Observation, TInt extends Enum<TInt>> extends InputForwardBackwardCalculatorBase<TObs, TInt> {

    private static final Logger LOG = Logger.getLogger(InputForwardBackwardScaledCalculatorBase.class.getName());

    private InputForwardBackwardScaledCalculatorBase() {
    }

}
