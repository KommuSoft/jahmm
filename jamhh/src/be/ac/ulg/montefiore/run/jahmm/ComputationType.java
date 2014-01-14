/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
