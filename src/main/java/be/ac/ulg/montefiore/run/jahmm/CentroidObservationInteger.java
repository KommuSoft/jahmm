package be.ac.ulg.montefiore.run.jahmm;

import java.util.*;


/**
 * This class represents the centroid of a set of {@link ObservationInteger
 * ObservationInteger}.
 */
public class CentroidObservationInteger
implements Centroid<ObservationInteger>
{	
	private double value;
	
	
	public CentroidObservationInteger(ObservationInteger o)
	{
		this.value = o.value;
	}
	
	
	public void reevaluateAdd(ObservationInteger e,
			List<? extends ObservationInteger> v)
	{
		value = ((value * (double) v.size()) +
				((double) (e.value))) / (v.size()+1.);
	}
	
	
	public void reevaluateRemove(ObservationInteger e,
			List<? extends ObservationInteger> v)
	{
		value = ((value * (double) v.size()) -
				((double) e.value)) / (v.size()-1.);
	}
	
	
	/**
	 * Returns the distance from this centroid to a given element.
	 * This distance is the absolute value of the difference between the
	 * value of this centroid and the value of the argument.
	 * 
	 * @param e The element, which must be an {@link ObservationInteger
	 *          ObservationInteger}.
	 * @return The distance to the centroid.
	 */
	public double distance(ObservationInteger e)
	{
		return Math.abs(e.value-value);
	}
}
