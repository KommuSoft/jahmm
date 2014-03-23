package jahmm.toolbox;

/**
 *
 * @author kommusoft
 */
public abstract class KullbackLeiblerDistanceCalculatorBase {

    protected int sequencesLength = 1_000;
    protected int nbSequences = 10;

    protected KullbackLeiblerDistanceCalculatorBase() {
        this(1_000);
    }

    protected KullbackLeiblerDistanceCalculatorBase(int sequencesLength) {
        this(sequencesLength, 10);
    }

    protected KullbackLeiblerDistanceCalculatorBase(int sequencesLength, int nbSequences) {
        this.sequencesLength = sequencesLength;
        this.nbSequences = nbSequences;
    }

    /**
     * Returns the number of sequences generated to estimate a distance.
     *
     * @return The number of generated sequences.
     */
    public int getNbSequences() {
        return nbSequences;
    }

    /**
     * Sets the number of sequences generated to estimate a distance.
     *
     * @param nb The number of generated sequences.
     */
    public void setNbSequences(int nb) {
        this.nbSequences = nb;
    }

    /**
     * Returns the length of sequences generated to estimate a distance.
     *
     * @return The sequences length.
     */
    public int getSequencesLength() {
        return sequencesLength;
    }

    /**
     * Sets the length of sequences generated to estimate a distance.
     *
     * @param length The sequences length.
     */
    public void setSequencesLength(int length) {
        this.sequencesLength = length;
    }

}
