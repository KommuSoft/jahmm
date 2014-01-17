package be.ac.ulg.montefiore.run.jahmm;

/**
 * Flags used to explain how the observation sequence probability should be
 * computed (either forward, using the alpha array, or backward, using the beta
 * array).
 *
 * @author kommusoft
 */
public enum ComputationType {

    /**
     * The alpha-step of the Baum-Welch algorithm.
     */
    ALPHA,
    /**
     * The beta-step of the Baum-Welch algorithm.
     */
    BETA
};
