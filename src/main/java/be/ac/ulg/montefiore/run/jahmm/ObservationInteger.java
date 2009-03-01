package be.ac.ulg.montefiore.run.jahmm;

import java.text.*;


/**
 * This class holds an integer observation.
 */
public class ObservationInteger extends Observation
implements CentroidFactory<ObservationInteger>
{	
	/**
	 * The observation's value.
	 */
	final public int value;
	
	
	/**
	 * An observation that can be described by an integer.
	 *
	 * @param value The value of this observation.
	 */
	public ObservationInteger(int value)
	{
		this.value = value;
	}
	
	
	/**
	 * Returns the centroid matching this observation.
	 *
	 * @return The corresponding observation.
	 */
	public Centroid<ObservationInteger> factor()
	{
		return new CentroidObservationInteger(this);
	}	

	
	public String toString(NumberFormat numberFormat)
	{
		return numberFormat.format(value);
	}
}
