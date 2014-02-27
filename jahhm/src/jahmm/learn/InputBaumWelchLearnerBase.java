package jahmm.learn;

import jahmm.InputHmm;
import jahmm.calculators.ForwardBackwardCalculator;
import jahmm.observables.InputObservationTuple;
import jahmm.observables.Observation;
import java.util.List;
import jutlis.tuples.Tuple3;

public class InputBaumWelchLearnerBase<TObs extends Observation, TInp extends Enum<TInp>> extends BaumWelchLearnerBase<TObs, InputObservationTuple<TInp, TObs>, InputHmm<TObs, TInp>, double[][], double[][], double[][][], double[][][]> implements InputBaumWelchLearner<TObs, TInp> {

    @Override
    protected ForwardBackwardCalculator<double[][], double[][], TObs, InputObservationTuple<TInp, TObs>, InputHmm<TObs, TInp>> getCalculator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public InputHmm<TObs, TInp> iterate(InputHmm<TObs, TInp> hmm, List<? extends List<? extends InputObservationTuple<TInp, TObs>>> sequences) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected double[][][] estimateXi(InputHmm<TObs, TInp> hmm, List<? extends InputObservationTuple<TInp, TObs>> sequence, Tuple3<double[][], double[][], Double> abp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected double[][][] estimateGamma(InputHmm<TObs, TInp> hmm, List<? extends InputObservationTuple<TInp, TObs>> sequence, Tuple3<double[][], double[][], Double> abp, double[][][] xi) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
