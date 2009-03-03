/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */

package be.ac.ulg.montefiore.run.jahmm;


/**
 * Classes implementing this interface are able to generate observation
 * probability distribution functions.
 * The classes implementing <code>OpdfFactory</code> are supposed to generate
 * only a certain kind of distribution (e.g. Gaussian).
 */
public interface OpdfFactory<D extends Opdf<?>>
{    
    /**
     * Generates a new observation probability distribution function.
     * 
     * @return The new opdf.
     */
    public D factor();
}
