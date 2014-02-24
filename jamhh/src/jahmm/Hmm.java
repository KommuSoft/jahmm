package jahmm;

import jahmm.observables.Observation;
import jahmm.observables.Opdf;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.List;

/**
 * Hmm interface; it specifies a Hidden Markov Model. An HMM is composed of:
 * <ul>
 * <li><i>states</i>: each state has a given probability of being initial
 * (<i>pi</i>) and an associated observation probability function (<i>opdf</i>).
 * Each state is associated to an index; the first state is numbered 0, the last
 * n-1 (where n is the number of states in the HMM); this number is given as an
 * argument to the various functions to refer to the matching state. </li>
 * <li><i>transition probabilities</i>: that is, the probability of going from
 * state <i>i</i> to state <i>j</i> (<i>a<sub>i,j</sub></i>).</li>
 * </ul>
 * <p>
 * Important objects extensively used with HMMs are {@link Observation
 * Observation}s, observation sequences and set of observation sequences. An
 * observation sequence is simply a {@link List List} of
 * {@link Observation Observation}s (in the right order, the i-th element of the
 * vector being the i-th element of the sequence). A set of observation
 * sequences is a {@link java.util.List List} of such sequences.
 * 
* @param <TObs> the type of the observations.
 * @param <TInt> the type for the interactions of the hidden Markov model.
 */
public interface Hmm<TObs extends Observation, TInt extends Observation> extends Cloneable, Serializable {

    /**
     * Creates a duplicate object of the HMM.
     *
     * @return An IHHM that contains the same date as this object.
     * @throws CloneNotSupportedException An exception such that classes lower
     * in the hierarchy can fail to clone.
     */
    Hmm<TObs, TInt> clone() throws CloneNotSupportedException;

    /**
     * Returns the probability associated with the transition going from state
     * <i>i</i> to state <i>j</i> (<i>a<sub>i,j</sub></i>).
     *
     * @param i The first state number such that
     * <code>0 &le; i &lt; nbStates()</code>.
     * @param j The second state number such that
     * <code>0 &le; j &lt; nbStates()</code>.
     * @return The probability associated to the transition going from
     * <code>i</code> to state <code>j</code>.
     */
    double getAij(int i, int j);

    /**
     * Returns the opdf associated with a given state.
     *
     * @param stateNb A state number such that
     * <code>0 &le; stateNb &lt; nbStates()</code>.
     * @return The opdf associated to state <code>stateNb</code>.
     */
    Opdf<TObs> getOpdf(int stateNb);

    /**
     * Returns the <i>pi</i> value associated with a given state.
     *
     * @param stateNb A state number such that
     * <code>0 &le; stateNb &lt; nbStates()</code>
     * @return The <i>pi</i> value associated to <code>stateNb</code>.
     */
    double getPi(int stateNb);

    /**
     * Returns the natural logarithm of observation sequences probability given
     * this HMM. A <i>scaling</i> procedure is used in order to avoid underflows
     * when computing the probability of long sequences.
     *
     * @param oseq A non-empty observation sequence.
     * @return The probability of this sequence.
     */
    double lnProbability(List<? extends TInt> oseq);

    /**
     * Returns an array containing the most likely state sequence matching an
     * observation sequence given this HMM. This sequence <code>I</code>
     * maximizes the probability of <code>P[I|O,Model]</code> where
     * <code>O</code> is the observation sequence and <code>Model</code> this
     * HMM model.
     *
     * @param oseq A non-empty observation sequence.
     * @return An array containing the most likely sequence of state numbers.
     * This array can be modified.
     */
    int[] mostLikelyStateSequence(List<? extends TInt> oseq);

    /**
     * Returns the number of states of this HMM.
     *
     * @return The number of states of this HMM.
     */
    int nbStates();

    /**
     * Returns the probability of an observation sequence given this HMM.
     *
     * @param oseq A non-empty observation sequence.
     * @return The probability of this sequence.
     */
    double probability(List<? extends TInt> oseq);

    /**
     * Returns the probability of an observation sequence along a state sequence
     * given this HMM.
     *
     * @param oseq A non-empty observation sequence.
     * @param sseq An array containing a sequence of state numbers. The length
     * of this array must be equal to the length of <code>oseq</code>
     * @return The probability P[oseq,sseq|H], where H is this HMM.
     */
    double probability(List<? extends TInt> oseq, int[] sseq);

    /**
     * Gives a description of this HMM.
     *
     * @return A textual description of this HMM.
     */
    @Override
    String toString();

    /**
     * Gives a description of this HMM.
     *
     * @param nf A number formatter used to print numbers (e.g. Aij values).
     * @return A textual description of this HMM.
     */
    String toString(NumberFormat nf);

    void fold();

    void fold(int n);

    void fold(Iterable<? extends TInt> interaction);

}
