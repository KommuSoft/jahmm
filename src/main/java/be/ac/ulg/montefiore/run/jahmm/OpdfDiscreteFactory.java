/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */

package be.ac.ulg.montefiore.run.jahmm;


/**
 * This class can build <code>OpdfInteger</code> observation probability
 * distribution functions.
 */
public class OpdfDiscreteFactory<E extends Enum<E>>
implements OpdfFactory<OpdfDiscrete<E>>
{	
	final protected Class<E> valuesClass;
	
	
	/**
	 * Creates a factory for {@link OpdfDiscrete OpdfDiscrete} objects.
	 * 
	 * @param valuesClass The class representing the set of values over which
	 *      the generated observation distributions operate.
	 */
	public OpdfDiscreteFactory(Class<E> valuesClass)
	{
		this.valuesClass = valuesClass;
	}
	
	
	public OpdfDiscrete<E> factor()
	{
		return new OpdfDiscrete<E>(valuesClass);
	}
}
