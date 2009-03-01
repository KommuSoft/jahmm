package be.ac.ulg.montefiore.run.jahmm.io;

import java.io.IOException;
import java.io.Writer;

import be.ac.ulg.montefiore.run.jahmm.ObservationInteger;


/**
 * Writes an {@link ObservationInteger ObservationInteger} up to (and including)
 * the semi-colon.
 */
public class ObservationIntegerWriter
extends ObservationWriter<ObservationInteger>
{	
	public void write(ObservationInteger observation, Writer writer) 
	throws IOException
	{
		writer.write(observation.value + "; ");
	}
}
