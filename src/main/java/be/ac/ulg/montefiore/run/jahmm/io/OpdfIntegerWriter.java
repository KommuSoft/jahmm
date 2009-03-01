package be.ac.ulg.montefiore.run.jahmm.io;

import java.io.IOException;
import java.io.Writer;

import be.ac.ulg.montefiore.run.jahmm.ObservationInteger;
import be.ac.ulg.montefiore.run.jahmm.OpdfInteger;

/**
 * This class can write a textual description of an {@link OpdfInteger}.
 * It is compatible with {@link OpdfIntegerReader}.
 */
public class OpdfIntegerWriter
extends OpdfWriter<OpdfInteger>
{
	public void write(Writer writer, OpdfInteger opdf)
	throws IOException
	{
		String s = "IntegerOPDF [";
		
		for (int i = 0; i < opdf.nbEntries(); i++)
			s += opdf.probability(new ObservationInteger(i)) + " ";
			
		writer.write(s + "]\n");
	}
}
