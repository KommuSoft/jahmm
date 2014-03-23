package jahmm.learn;

import jahmm.Hmm;
import jahmm.observables.Observation;
import java.util.List;

/**
 * An interface representing the structure of a Baum-Welch Learner.
 *
 * @author kommusoft
 * @param <TObs> The type of observations regarding the Hidden Markov Model.
 * @param <TInt> The type of interactions regarding the Hidden Markov Model.
 * @param <THmm> The type of the Hidden Markov Model.
 */
public interface BaumWelchLearner<TObs extends Observation, TInt extends Observation, THmm extends Hmm<TObs, TInt, THmm>> {

    /**
     * Returns the number of iterations performed by the {@link #learn} method.
     *
     * @return The number of iterations performed.
     */
    int getNbIterations();

    /**
     * Performs one iteration of the Baum-Welch algorithm. In one iteration, a
     * new HMM is computed using a previously estimated HMM.
     *
     * @param hmm A previously estimated HMM.
     * @param sequences The observation sequences on which the learning is
     * based. Each sequence must have a length higher or equal to 2.
     * @return A new, updated HMM.
     */
    public abstract THmm iterate(THmm hmm, List<? extends List<? extends TInt>> sequences);

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
    public abstract THmm learn(THmm initialHmm, List<? extends List<? extends TInt>> sequences);

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
    public abstract THmm learn(THmm initialHmm, int nbIterations, List<? extends List<? extends TInt>> sequences);

    /**
     * Sets the number of iterations performed by the {@link #learn} method.
     *
     * @param nb The (positive) number of iterations to perform.
     */
    void setNbIterations(int nb);

}
