package jahmm.learn;

import jahmm.Hmm;
import jahmm.RegularHmm;
import jahmm.observables.Observation;
import java.util.List;
import jutlis.tuples.Tuple3;

public abstract class BaumWelchLearnerGammaBase<TObs extends Observation, TInt extends Observation, THmm extends Hmm<TObs, TInt,THmm>, TAlpha, TBeta, TADen> extends BaumWelchLearnerBase<TObs, TInt, THmm, TAlpha, TBeta, double[][][], double[][], TADen> {

    /**
     * gamma[][] could be computed directly using the alpha and beta arrays, but
     * this (slower) method is preferred because it doesn't change if the xi
     * array has been scaled (and should be changed with the scaled alpha and
     * beta arrays).
     *
     * @param xi
     * @return
     */
    @Override
    protected double[][] estimateGamma(List<? extends TInt> sequence, Tuple3<TAlpha, TBeta, Double> abp, THmm hmm, double[][][] xi) {
        double[][] gamma = new double[xi.length + 1][xi[0].length];
        for (int t = 0; t < xi.length; t++) {
            for (int i = 0; i < xi[0].length; i++) {
                for (int j = 0; j < xi[0].length; j++) {
                    gamma[t][i] += xi[t][i][j];
                }
            }
        }
        for (int j = 0; j < xi[0].length; j++) {
            for (int i = 0; i < xi[0].length; i++) {
                gamma[xi.length][j] += xi[xi.length - 1][i][j];
            }
        }
        return gamma;
    }

    /**
     * Sets the pi-values of the Hidden Markov Model based on the gamma values.
     *
     * @param nhmm The Hidden Markov Model to modify.
     * @param allGamma The set of values
     */
    @Override
    protected void setPiValues(THmm nhmm, double[][][] allGamma) {
        int nO = allGamma.length;
        int nI = allGamma[0x00][0x00].length;
        for (int i = 0; i < nI; i++) {
            double total = 0.0d;
            for (int o = 0; o < nO; o++) {
                total += allGamma[o][0][i];
            }
            nhmm.setPi(i, total / nO);
        }
    }

}
