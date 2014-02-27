/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jahmm.learn;

import jahmm.Hmm;
import jahmm.calculators.ForwardBackwardCalculator;
import jahmm.observables.Observation;
import java.util.List;
import jutlis.tuples.Tuple3;

/**
 *
 * @author kommusoft
 * @param <TObs>
 * @param <TInt>
 * @param <THmm>
 */
public abstract class BaumWelchLearnerBase<TObs extends Observation, TInt extends Observation, THmm extends Hmm<TObs, TInt>,TAlpha,TBeta> implements BaumWelchLearner<TObs, TInt, THmm> {

    /**
     * Number of iterations performed by the {@link #learn} method.
     */
    protected int nbIterations = 9;

    public BaumWelchLearnerBase() {
    }

    /**
     * Returns the number of iterations performed by the {@link #learn} method.
     *
     * @return The number of iterations performed.
     */
    @Override
    public int getNbIterations() {
        return nbIterations;
    }

    /**
     * Sets the number of iterations performed by the {@link #learn} method.
     *
     * @param nb The (positive) number of iterations to perform.
     */
    @Override
    public void setNbIterations(int nb) {
        if (nb < 0) {
            throw new IllegalArgumentException("Positive number expected");
        }
        nbIterations = nb;
    }
    
    protected Tuple3<TAlpha, TBeta, Double> getAlphaBetaProbability(THmm hmm, List<? extends TInt> obsSeq) {
        return this.getCalculator().computeAll(hmm, obsSeq);
    }
    
    protected abstract ForwardBackwardCalculator<TAlpha,TBeta,TObs,TInt,THmm> getCalculator ();

}
