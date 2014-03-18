package jahmm.learn;

import jahmm.Hmm;
import jahmm.calculators.ForwardBackwardCalculator;
import jahmm.observables.Observation;
import jahmm.observables.Opdf;
import java.util.List;
import jutlis.tuples.Tuple3;

/**
 * A basic implementation of the
 *
 * @author kommusoft
 * @param <TObs> The type of observations regarding the Hidden Markov Model.
 * @param <TInt> The type of interactions regarding the Hidden Markov Model.
 * @param <THmm> The type of the Hidden Markov Model.
 * @param <TAlpha> The type of the alpha-values.
 * @param <TBeta> The type of beta-values.
 * @param <TXi> The type of the xi-estimates.
 * @param <TGamma> The type of the gamma-estimates.
 */
public abstract class BaumWelchLearnerBase<TObs extends Observation, TInt extends Observation, THmm extends Hmm<TObs, TInt>, TAlpha, TBeta, TXi, TGamma, TADen> implements BaumWelchLearner<TObs, TInt, THmm> {

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
     * Gets the relevant calculator.
     *
     * @return The relevant calculator.
     */
    protected abstract ForwardBackwardCalculator<TAlpha, TBeta, TObs, TInt, THmm> getCalculator();

    /**
     * Calculates the alpha and beta value of the given Hidden Markov Model and
     * a list of interactions.
     *
     * @param hmm The given Hidden Markov Model.
     * @param obsSeq The given list of interactions.
     * @return A tuple containing the alpha- and beta-values and the probability
     * of the list of observations.
     */
    @SuppressWarnings("unchecked")
    protected Tuple3<TAlpha, TBeta, Double> getAlphaBetaProbability(THmm hmm, List<? extends TInt> obsSeq) {
        return this.getCalculator().computeAll(hmm, obsSeq);
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

    /**
     * Here, the xi (and, thus, gamma) values are not divided by the probability
     * of the sequence because this probability might be too small and induce an
     * underflow. xi[t][i][j] still can be interpreted as P[q_t = i and q_(t+1)
     * = j | obsSeq, hmm] because we assume that the scaling factors are such
     * that their product is equal to the inverse of the probability of the
     * sequence.
     *
     * @param sequence The sequence of interactions.
     * @param abp A tuple containing alpha- and beta-values and the probability
     * of the given interaction sequence.
     * @param hmm The given Hidden Markov Model.
     * @return The estimated Xi values.
     */
    protected abstract TXi estimateXi(List<? extends TInt> sequence, Tuple3<TAlpha, TBeta, Double> abp, THmm hmm);

    /**
     * gamma[][] could be computed directly using the alpha and beta arrays, but
     * this (slower) method is preferred because it doesn't change if the xi
     * array has been scaled (and should be changed with the scaled alpha and
     * beta arrays).
     *
     * @param sequence The sequence of interactions.
     * @param abp A tuple containing alpha- and beta-values and the probability
     * of the given interaction sequence.
     * @param hmm The given Hidden Markov Model.
     * @param xi The estimated Xi values.
     * @return The estimated Gamma values.
     */
    protected abstract TGamma estimateGamma(List<? extends TInt> sequence, Tuple3<TAlpha, TBeta, Double> abp, THmm hmm, TXi xi);

    /**
     * Creates a new instance of the â-denominator based on the given Hidden
     * Markov Model.
     *
     * @param hmm The given Hidden Markov Model.
     * @return A new instance of the â-denominator.
     */
    protected abstract TADen createADenominator(THmm hmm);

    /**
     * Creates a new instance of the â-numerator based on the given Hidden
     * Markov Model.
     *
     * @param hmm The given Hidden Markov Model.
     * @return A new instance of the â-numerator.
     */
    protected abstract TADen[] createANumerator(THmm hmm);

    /**
     * Updates the â-values based on the given Gamma and Xi values.
     *
     * @param gamma The gamma values of the sequence.
     * @param xi The xi values of the sequence.
     * @param aijDen The denominators of the â-values.
     * @param aijNum The numerators of the â-values.
     */
    protected abstract void updateAbarXiGamma(TXi xi, TGamma gamma, TADen[] aijNum, TADen aijDen);

    /**
     * Performs one iteration of the Baum-Welch algorithm. In one iteration, a
     * new HMM is computed using a previously estimated HMM.
     *
     * @param hmm A previously estimated HMM.
     * @param sequences The observation sequences on which the learning is
     * based. Each sequence must have a length higher or equal to 2.
     * @return A new, updated HMM.
     *
     * gamma and xi arrays are those defined by Rabiner and Juang allGamma[n] =
     * gamma array associated to observation sequence n.
     *
     * a[i][j] = aijNum[i][j] / aijDen[i] aijDen[i] = expected number of
     * transitions from state i aijNum[i][j] = expected number of transitions
     * from state i to j
     */
    @Override
    @SuppressWarnings("unchecked")
    public THmm iterate(THmm hmm, List<? extends List<? extends TInt>> sequences) {
        THmm nhmm;
        try {
            nhmm = (THmm) hmm.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
        TGamma[] allGamma = (TGamma[]) new Object[sequences.size()];

        TADen[] aijNum = this.createANumerator(hmm);
        TADen aijDen = this.createADenominator(hmm);

        int g = 0;
        for (List<? extends TInt> obsSeq : sequences) {

            Tuple3<TAlpha, TBeta, Double> abp = getAlphaBetaProbability(hmm, obsSeq);

            TXi xi = estimateXi(obsSeq, abp, hmm);
            TGamma gamma = allGamma[g++] = estimateGamma(obsSeq, abp, hmm, xi);

            updateAbarXiGamma(xi, gamma, aijNum, aijDen);

            setAValues(nhmm, aijNum, aijDen);
        }

        /* pi computation */
        /*for (int i = 0; i < hmm.nbStates(); i++) {
         double total = 0.0d;
         for (int o = 0; o < sequences.size(); o++) {
         total += allGamma[o][0][i];
         }
         nhmm.setPi(i, total / sequences.size());
         }

         /* pdfs computation */
        /*for (int i = 0; i < hmm.nbStates(); i++) {
         List<TObs> observations = KMeansLearner.flat(sequences);
         double[] weights = new double[observations.size()];
         double sum = 0.;
         int j = 0;

         int o = 0;
         for (List<? extends TObs> obsSeq : sequences) {
         for (int t = 0; t < obsSeq.size(); t++, j++) {
         sum += weights[j] = allGamma[o][t][i];
         }
         o++;
         }

         for (j--; j >= 0; j--) {
         weights[j] /= sum;
         }

         Opdf<TObs> opdf = nhmm.getOpdf(i);
         opdf.fit(observations, weights);
         }
         */
        return nhmm;
    }

    /**
     * Sets the a-values of the Hidden Markov model based on the values of the
     * â-values.
     *
     * @param hmm The Hidden Markov Model to modify.
     * @param aijNum The numerators of the â-values.
     * @param aijDen The denominators of the â-values.
     */
    protected abstract void setAValues(THmm hmm, TADen[] aijNum, TADen aijDen);

}
