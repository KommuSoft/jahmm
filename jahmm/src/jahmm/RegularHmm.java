package jahmm;

import jahmm.observables.Observation;
import jahmm.observables.Opdf;

/**
 *
 * @author kommusoft
 * @param <TObs>
 */
public interface RegularHmm<TObs extends Observation> extends Hmm<TObs, TObs> {

    /**
     * Creates a duplicate object of the HMM.
     *
     * @return An IHHM that contains the same date as this object.
     * @throws CloneNotSupportedException An exception such that classes lower
     * in the hierarchy can fail to clone.
     */
    @Override
    public abstract RegularHmm<TObs> clone() throws CloneNotSupportedException;

    /**
     * Sets the probability associated to the transition going from state
     * <i>i</i> to state <i>j</i> (<i>A<sub>i,j</sub></i>).
     *
     * @param i The first state number such that
     * <code>0 &le; i &lt; nbStates()</code>.
     * @param j The second state number such that
     * <code>0 &le; j &lt; nbStates()</code>.
     * @param value The value of <i>A<sub>i,j</sub></i>.
     */
    public abstract void setAij(int i, int j, double value);

    /**
     * Sets the opdf associated with a given state.
     *
     * @param stateNb A state number such that
     * <code>0 &le; stateNb &lt; nbStates()</code>.
     * @param opdf An observation probability function.
     */
    public abstract void setOpdf(int stateNb, Opdf<TObs> opdf);

    /**
     * Returns the opdf associated with a given state.
     *
     * @param stateNb A state number such that
     * <code>0 &le; stateNb &lt; nbStates()</code>.
     * @return The opdf associated to state <code>stateNb</code>.
     */
    public abstract Opdf<TObs> getOpdf(int stateNb);

}
