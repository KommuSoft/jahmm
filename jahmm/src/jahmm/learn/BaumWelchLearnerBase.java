package jahmm.learn;

import jahmm.Hmm;
import jahmm.observables.Observation;
import java.util.List;

/**
 * A basic implementation of the 
 * @author kommusoft
 * @param <TObs> The type of observations regarding the Hidden Markov Model.
 * @param <TInt> The type of interactions regarding the Hidden Markov Model.
 * @param <THmm> The type of the Hidden Markov Model.
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

    /**
     * Does a fixed number of iterations (see {@link #getNbIterations}) of the
     * Baum-Welch algorithm.
     *
     * @param initialHmm An initial estimation of the expected HMM. This
     * estimate is critical as the Baum-Welch algorithm only find local minima
     * of its likelihood function.
     * @param nbIterations The number of iterations in the learning process.
     * @param sequences The observation sequences on which the learning is
     * based. Each sequence must have a length higher or equal to 2.
     * @return The HMM that best matches the set of observation sequences given
     * (according to the Baum-Welch algorithm).
     */
    @Override
    public THmm learn(THmm initialHmm, int nbIterations, List<? extends List<? extends TInt>> sequences) {
        THmm hmm = initialHmm;
        for (int i = 0; i < nbIterations; i++) {
            hmm = iterate(hmm, sequences);
        }
        return hmm;
    }

    /**
     * Does a fixed number of iterations (see {@link #getNbIterations}) of the
     * Baum-Welch algorithm.
     *
     * @param initialHmm An initial estimation of the expected HMM. This
     * estimate is critical as the Baum-Welch algorithm only find local minima
     * of its likelihood function.
     * @param sequences The observation sequences on which the learning is
     * based. Each sequence must have a length higher or equal to 2.
     * @return The HMM that best matches the set of observation sequences given
     * (according to the Baum-Welch algorithm).
     */
    @Override
    public THmm learn(THmm initialHmm, List<? extends List<? extends TInt>> sequences) {
        return this.learn(initialHmm, this.getNbIterations(), sequences);
    }

}
