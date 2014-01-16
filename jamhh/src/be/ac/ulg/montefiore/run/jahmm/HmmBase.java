package be.ac.ulg.montefiore.run.jahmm;

/**
 *
 * @author kommusoft
 */
public abstract class HmmBase<TObs extends Observation, TAMx, TBMx, TInt extends Observation> implements AbstractHmm<TObs, TInt> {

    protected double pi[];
    protected TAMx a;
    protected TBMx b;

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
        return pi.length;
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
        return pi[stateNb];
    }

    /**
     * Sets the <i>pi</i> value associated with a given state.
     *     
* @param stateNb A state number such that
     * <code>0 &le; stateNb &lt; nbStates()</code>.
     * @param value The <i>pi</i> value to associate to state number
     * <code>stateNb</code>
     */
    public void setPi(int stateNb, double value) {
        pi[stateNb] = value;
    }

    @Override
    public void fold() {
        this.fold(0x01);
    }

}
