package jahmm.calculators;

import jahmm.InputHmm;
import jahmm.observables.InputObservationTuple;
import jahmm.observables.Observation;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import jutlis.tuples.Tuple3;

/**
 * An alpha-beta calculator that calculates
 *
 * @author kommusoft
 */
public class InputForwardBackwardCalculatorBase<TObs extends Observation, TInt extends Enum<TInt>> extends ForwardBackwardCalculatorRaw<double[][], double[][], TObs, InputObservationTuple<TInt, TObs>, InputHmm<TObs, TInt>> implements InputForwardBackwardCalculator<TObs, TInt> {

    private static final InputForwardBackwardCalculatorBase Instance = new InputForwardBackwardCalculatorBase();

    private static final Logger LOG = Logger.getLogger(InputForwardBackwardCalculatorBase.class.getName());

    private InputForwardBackwardCalculatorBase() {
    }

    @Override
    public double[][] computeAlpha(InputHmm<TObs, TInt> hmm, Collection<? extends InputObservationTuple<TInt, TObs>> oseq) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double[][] computeBeta(InputHmm<TObs, TInt> hmm, List<? extends InputObservationTuple<TInt, TObs>> oseq) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tuple3<double[][], double[][], Double> computeAll(InputHmm<TObs, TInt> hmm, List<? extends InputObservationTuple<TInt, TObs>> oseq) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double computeProbability(InputHmm<TObs, TInt> hmm, Collection<ComputationType> flags, List<? extends InputObservationTuple<TInt, TObs>> oseq) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
