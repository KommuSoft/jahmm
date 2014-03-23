package jahmm;

import jahmm.observables.Observation;

/**
 * The basic implementation of a Hidden Markov Model that all implemented types
 * have in common.
 *
 * @author kommusoft
 * @param <TObs> The type of the observations of the Hidden Markov Model.
 * @param <TAMx> The type of a-values of the Hidden Markov Model.
 * @param <TBMx> The type of b-values of the Hidden Markov Model.
 * @param <TInt> The type of interaction of the hidden Markov Model.
 */
public abstract class HmmBase<TObs extends Observation, TAMx, TBMx, TInt extends Observation> implements Hmm<TObs, TInt> {

    private static final long serialVersionUID = 1L;

    /**
     * Generates an initial distribution of the states for the given number of
     * states.
     *
     * @param nbStates The given number of states.
     * @return A uniform probability vector with the length equal to the number
     * of states.
     */
    protected static double[] generatePi(int nbStates) {
        if (nbStates <= 0) {
            throw new IllegalArgumentException("Number of states must be positive");
        }
        double inv = 1.0d / nbStates;
        double[] pi = new double[nbStates];
        for (int i = 0x00; i < nbStates; i++) {
            pi[i] = inv;
        }
        return pi;
    }

    /**
     * The stored initial state distribution of the Hidden Markov Model.
     */
    protected double pi[];

    /**
     * The stored a-values of the Hidden Markov Model.
     */
    protected TAMx a;

    /**
     * The stored b-values of the Hidden Markov Model.
     */
    protected TBMx b;

    /**
     * Creates a new instance of a Hidden Markov Model with given initial
     * distribution and a- and b-values.
     *
     * @param pi The given initial distribution.
     * @param a The given initial a-values.
     * @param b The given initial b-values.
     */
    protected HmmBase(double pi[], TAMx a, TBMx b) {
        this.pi = pi;
        this.a = a;
        this.b = b;
    }

    /**
     * Creates a duplicate object of the HMM.
     *
     * @return An IHHM that contains the same date as this object.
     * @throws CloneNotSupportedException An exception such that classes lower
     * in the hierarchy can fail to clone.
     */
    @Override
    public abstract HmmBase<TObs, TAMx, TBMx, TInt> clone() throws CloneNotSupportedException;

    /**
     * Returns the number of states of this HMM.
     *
     * @return The number of states of this HMM.
     */
    @Override
    public int nbStates() {
        return this.pi.length;
    }

    /**
     * Returns the <i>pi</i> value associated with a given state.
     *
     * @param stateNb A state number such that
     * <code>0 &le; stateNb &lt; nbStates()</code>
     * @return The <i>pi</i> value associated to <code>stateNb</code>.
     */
    @Override
    public double getPi(int stateNb) {
        return this.pi[stateNb];
    }

    /**
     * Sets the <i>pi</i> value associated with a given state.
     *
     * @param stateNb A state number such that
     * <code>0 &le; stateNb &lt; nbStates()</code>.
     * @param value The <i>pi</i> value to associate to state number
     * <code>stateNb</code>
     */
    @Override
    public void setPi(int stateNb, double value) {
        this.pi[stateNb] = value;
    }

    /**
     * Takes as input distribution (pi) of states the distribution of the states
     * after one iteration regardless of any interaction.
     *
     */
    @Override
    public void fold() {
        this.fold(0x01);
    }

    /**
     * Returns an array of pi-values.
     *
     * @return An array of pi-values.
     * @note The array is a duplicate: modifications to the array won't have any
     * effect on the pi-values stored in the Hidden Markov Model.
     */
    @Override
    public double[] getPis() {
        return this.pi.clone();
    }

}
