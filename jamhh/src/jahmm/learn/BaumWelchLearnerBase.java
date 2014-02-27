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
 * @param <TAlpha>
 * @param <TBeta>
 * @param <TXi>
 * @param <TGamma>
 */
public abstract class BaumWelchLearnerBase<TObs extends Observation, TInt extends Observation, THmm extends Hmm<TObs, TInt>, TAlpha, TBeta, TXi, TGamma> implements BaumWelchLearner<TObs, TInt, THmm> {

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

    /**
     * Returns the alpha and beta values of the hidden Markov model for the
     * given interactions together with the probability of the observations.
     *
     * @param hmm The given hidden Markov Model.
     * @param obsSeq The given list of interactions.
     * @return A tuple containing the alpha and beta values for the given
     * sequence of interactions.
     */
    protected Tuple3<TAlpha, TBeta, Double> getAlphaBetaProbability(THmm hmm, List<? extends TInt> obsSeq) {
        return this.getCalculator().computeAll(hmm, obsSeq);
    }

    /**
     * Returns the relevant calculator to calculate alpha- and beta-values and
     * probability of a given sequence.
     *
     * @return The relevant calculator to calculate alpha- and beta-values and
     * probability of a given sequence.
     */
    protected abstract ForwardBackwardCalculator<TAlpha, TBeta, TObs, TInt, THmm> getCalculator();

    /**
     * Calculates the estimated Xi values of the given sequence of interactions
     * and the given hidden Markov Model.
     *
     * @param hmm The hidden Markov model to compute the gamma values of.
     * @param sequence The sequence of interactions to learn from.
     * @param abp A tuple containing the alpha- and beta- values of the sequence
     * together with the probability of the sequence.
     * @return The estimated xi values.
     */
    protected abstract TXi estimateXi(THmm hmm, List<? extends TInt> sequence, Tuple3<TAlpha, TBeta, Double> abp);

    /**
     * Calculates the estimated Gamma values of the given sequence of
     * interactions and the given hidden Markov Model.
     *
     * @param hmm The hidden Markov model to compute the gamma values of.
     * @param sequence The sequence of interactions to learn from.
     * @param abp A tuple containing the alpha- and beta- values of the sequence
     * together with the probability of the sequence.
     * @param xi The estimated xi variables.
     * @return The estimated gamma values.
     *
     * @note Gamma can be computed directly using the alpha and beta arrays, but
     * this (slower) method is preferred because it doesn't change if the xi
     * array has been scaled (and should be changed with the scaled alpha and
     * beta arrays).
     */
    protected abstract TGamma estimateGamma(THmm hmm, List<? extends TInt> sequence, Tuple3<TAlpha, TBeta, Double> abp, TXi xi);

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
        return this.learn(initialHmm, sequences, this.getNbIterations());
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
     * @param nbIterations The number of iterations that the algorithm uses.
     * @return The HMM that best matches the set of observation sequences given
     * (according to the Baum-Welch algorithm).
     */
    @Override
    public THmm learn(THmm initialHmm, List<? extends List<? extends TInt>> sequences, int nbIterations) {
        THmm hmm = initialHmm;
        for (int i = 0; i < nbIterations; i++) {
            hmm = iterate(hmm, sequences);
        }
        return hmm;
    }

}
