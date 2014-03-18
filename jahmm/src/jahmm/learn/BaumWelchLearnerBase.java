package jahmm.learn;

import jahmm.Hmm;
import jahmm.observables.Observation;

/**
 *
 * @author kommusoft
 * @param <TObs>
 * @param <TInt>
 * @param <THmm>
 */
public abstract class BaumWelchLearnerBase<TObs extends Observation, TInt extends Observation, THmm extends Hmm<TObs, TInt>> implements BaumWelchLearner<TObs, TInt, THmm> {

    /**
     * Number of iterations performed by the {@link #learn} method.
     */
    protected int nbIterations = 9;

    protected BaumWelchLearnerBase() {
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

}
