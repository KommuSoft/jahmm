package be.ac.ulg.montefiore.run.jahmm;

import java.util.*;
import java.io.*;
import java.text.NumberFormat;


/**
 * Objects implementing this interface represent an observation probability
 * (distribution) function.
 * <p>
 * An <code>Opdf</code> can represent a probability function (if the 
 * observations can take discrete values) or a probability distribution (if
 * the observations are continous).
 */
public interface Opdf<O extends Observation> 
extends Cloneable, Serializable
{
    
    /**
     * Returns the probability (density) of an observation given a distribution.
     *
     * @param o An observation.
     * @return The probability (density, if <code>o</code> takes continuous
     *         values) of <code>o</code> for this function.
     */
    public double probability(O o);

    
     /**
     * Generates a (pseudo) random observation according to this
     * distribution.
     *
     * @return An observation.
     */
    public O generate();
    
    
    /**
     * Fits this observation probability (distribution) function to a (non
     * empty) set of observations.  The meaning to give to <i>fits</i> should be
     * <i>has the maximum likelihood</i> if possible.
     *
     * @param oa An array of observations compatible with this function.
     */
    public void fit(O... oa);

    
    /**
     * Fits this observation probability (distribution) function to a (non
     * empty) set of observations.  The meaning to give to <i>fits</i> should be
     * <i>has the maximum likelihood</i> if possible.
     *
     * @param co A set of observations compatible with this function.
     */
    public void fit(Collection<? extends O> co);
    

    /**
     * Fits this observation probability (distribution) function to a
     * weighted (non empty) set of observations.  Equations (53) and (54)
     * of Rabiner's <i>A Tutorial on Hidden Markov Models and Selected 
     * Applications in Speech Recognition</i> explain how the weights can be
     * used.
     *
     * @param o An array of observations compatible with this factory.
     * @param weights The weight associated to each observation (such that
     *                <code>weight.length == o.length</code> and the sum of
     *                all the elements equals 1).
     */
    void fit(O[] o, double[] weights);
    

    /**
     * Fits this observation probability (distribution) function to a
     * weighted (non empty) set of observations.  Equations (53) and (54)
     * of Rabiner's <i>A Tutorial on Hidden Markov Models and Selected 
     * Applications in Speech Recognition</i> explain how the weights can be
     * used.
     *
     * @param co A set of observations compatible with this factory.
     * @param weights The weight associated to each observation (such that
     *                <code>weight.length == o.length</code> and the sum of
     *                all the elements equals 1).
     */
    void fit(Collection<? extends O> co, double[] weights);
    
    
    /**
     * Returns a {@link java.lang.String String} describing this distribution.
     * 
     * @param numberFormat A formatter used to convert the numbers (<i>e.g.</i>
     *      probabilities) to strings.
     * @return A {@link java.lang.String String} describing this distribution.
     */
    public String toString(NumberFormat numberFormat);
    
    
    public Opdf<O> clone();
}
