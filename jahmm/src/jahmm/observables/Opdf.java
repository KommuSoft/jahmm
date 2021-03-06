/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package jahmm.observables;

import jahmm.Hmm;
import jahmm.io.INumberFormatString;
import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.Collection;
import jutils.draw.DotDrawer;

/**
 * Objects implementing this interface represent an observation probability
 * (distribution) function.
 * <p>
 * An <code>Opdf</code> can represent a probability function (if the
 * observations can take discrete values) or a probability distribution (if the
 * observations are continous).
 *
 * @param <O>
 */
public interface Opdf<O extends Observation> extends Cloneable, Serializable, INumberFormatString {

    /**
     * Returns the probability (density) of an observation given a distribution.
     *
     * @param o An observation.
     * @return The probability (density, if <code>o</code> takes continuous
     * values) of <code>o</code> for this function.
     */
    public abstract double probability(O o);

    /**
     * Generates a (pseudo) random observation according to this distribution.
     *
     * @return An observation.
     */
    public abstract O generate();

    /**
     * Fits this observation probability (distribution) function to a (non
     * empty) set of observations. The meaning to give to <i>fits</i> should be
     * <i>has the maximum likelihood</i> if possible.
     *
     * @param oa An array of observations compatible with this function.
     */
    public abstract void fit(O... oa);

    /**
     * Fits this observation probability (distribution) function to a (non
     * empty) set of observations. The meaning to give to <i>fits</i> should be
     * <i>has the maximum likelihood</i> if possible.
     *
     * @param co A set of observations compatible with this function.
     */
    public abstract void fit(Collection<? extends O> co);

    /**
     * Fits this observation probability (distribution) function to a weighted
     * (non empty) set of observations. Equations (53) and (54) of Rabiner's
     * <i>A Tutorial on Hidden Markov Models and Selected Applications in Speech
     * Recognition</i> explain how the weights can be used.
     *
     * @param o An array of observations compatible with this factory.
     * @param weights The weight associated to each observation (such that
     * <code>weight.length == o.length</code> and the sum of all the elements
     * equals 1).
     */
    abstract void fit(O[] o, double... weights);

    /**
     * Fits this observation probability (distribution) function to a weighted
     * (non empty) set of observations. Equations (53) and (54) of Rabiner's
     * <i>A Tutorial on Hidden Markov Models and Selected Applications in Speech
     * Recognition</i> explain how the weights can be used.
     *
     * @param co A set of observations compatible with this factory.
     * @param weights The weight associated to each observation (such that
     * <code>weight.length == o.length</code> and the sum of all the elements
     * equals 1).
     */
    abstract void fit(Collection<? extends O> co, double... weights);

    /**
     *
     * @return @throws java.lang.CloneNotSupportedException
     */
    public abstract Opdf<O> clone() throws CloneNotSupportedException;

    /**
     * Adds graphical elements to the dot graph.
     *
     * @param drawer The drawer to use.
     * @param writer The writer to write the information to.
     * @throws java.io.IOException If writer to the drawer fails.
     */
    public abstract void dotDrawNode(DotDrawer<? extends Hmm> drawer, Writer writer, String prefix) throws IOException;

    /**
     * Adds edges to the earlier generated graphical elements of the dot graph.
     *
     * @param drawer The drawer to use.
     * @param writer The writer to write the information to.
     * @throws java.io.IOException If writer to the drawer fails.
     */
    public abstract void dotDrawEdge(DotDrawer<? extends Hmm> drawer, Writer writer, String prefix, String source) throws IOException;
}
