/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */

package be.ac.ulg.montefiore.run.jahmm;

import java.text.*;


/**
 * This class implements observations made of a real value.
 */
public class ObservationReal extends Observation
implements CentroidFactory<ObservationReal>
{
	
	/**
	 * The observation's value.
	 */
	final public double value;
	
	
	/**
	 * An observation that can be described by a real.
	 *
	 * @param value The value of this observation.
	 */
	public ObservationReal(double value)
	{
		this.value = value;
	}
	
	
	/**
	 * Returns the centroid matching this observation.
	 *
	 * @return The corresponding observation.
	 */
	public Centroid<ObservationReal> factor()
	{
		return new CentroidObservationReal(this);
	}
	
	
	public String toString(NumberFormat numberFormat)
	{
		return numberFormat.format(value);
	}
}
