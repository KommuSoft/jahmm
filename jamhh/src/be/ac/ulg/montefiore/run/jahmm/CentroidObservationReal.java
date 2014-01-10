/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */

package be.ac.ulg.montefiore.run.jahmm;

import java.util.*;


/**
 * This class represents the centroid of a set of {@link ObservationReal
 * ObservationReal}.
 */
public class CentroidObservationReal
implements Centroid<ObservationReal>
{	
	private double value;
	
	
	public CentroidObservationReal(ObservationReal o)
	{
		this.value = o.value;
	}
	
	
	public void reevaluateAdd(ObservationReal e,
			List<? extends ObservationReal> v)
	{
		value =	(value * (double) v.size() + e.value) / (v.size()+1.);
	}
	
	
	public void reevaluateRemove(ObservationReal e,
			List<? extends ObservationReal> v)
	{
		value = ((value * (double) v.size()) - e.value) / (v.size()-1.);
	}
	
	
	/**
	 * Returns the distance from this centroid to a given element.
	 * This distance is the absolute value of the difference between the
	 * value of this centroid and the value of the argument.
	 * 
	 * @param e The element, which must be an {@link ObservationReal
	 *          ObservationReal}.
	 * @return The distance to the centroid.
	 */
	public double distance(ObservationReal e)
	{
		return Math.abs(e.value - value);
	}
}
