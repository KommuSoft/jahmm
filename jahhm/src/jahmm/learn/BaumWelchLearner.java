package jahmm.learn;

import jahmm.Hmm;
import jahmm.observables.Observation;
import java.util.List;

/**
 * The interface of the Baum-Welch learner. An iterative algorithm that aims to
 * maximize the probability of the given list of observation sequences.
 *
 * @author kommusoft
 * @param <TObs> The type of the observations related to the hidden Markov
 * Model.
 * @param <TInt> The type of interaction of the hidden Markov Model, interaction
 * should at least include the observations.
 * @param <THmm> The type of the hidden Markov model.
 */
public interface BaumWelchLearner<TObs extends Observation, TInt extends Observation, THmm extends Hmm<TObs, TInt>> {

    /**
     * Returns the number of iterations performed by the {@link #learn} method.
     *
     * @return The number of iterations performed.
     */
    public abstract int getNbIterations();

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
     * @param sequences The observation sequences on which the learning is
     * based. Each sequence must have a length higher or equal to 2.
     * @param nbIterations The number of iterations the algorithm uses.
     * @return The HMM that best matches the set of observation sequences given
     * (according to the Baum-Welch algorithm).
     */
    public abstract THmm learn(THmm initialHmm, List<? extends List<? extends TInt>> sequences, int nbIterations);

    /**
     * Sets the number of iterations performed by the {@link #learn} method.
     *
     * @param nb The (positive) number of iterations to perform.
     */
    public abstract void setNbIterations(int nb);

}
