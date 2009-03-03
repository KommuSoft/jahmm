/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */

package be.ac.ulg.montefiore.run.jahmm.io;

import java.io.*;
import java.text.DecimalFormat;

import be.ac.ulg.montefiore.run.jahmm.Opdf;


/**
 * Writes an observation distribution textual description.
 */
public abstract class OpdfWriter<O extends Opdf<?>>
{
	/**
	 * Writes a textual description of a given
	 * {@link be.ac.ulg.montefiore.run.jahmm.Opdf Opdf} compatible
	 * with the corresponding {@link OpdfReader}.
	 *
	 * @param writer The writer where the description is output.
	 * @param opdf An observation distribution.
	 */
	public abstract void write(Writer writer, O opdf)
	throws IOException;
	
	
	/**
	 * Writes a sequence of numbers.  This method is compatible with 
	 * {@link OpdfReader#read(StreamTokenizer, int)}.
	 * 
	 * @param writer Where to read the sequence to.
	 * @param array The array to write.
	 */
	protected void write(Writer writer, double[] array)
	throws IOException
	{
		DecimalFormat formatter = new DecimalFormat();
		
		writer.write("[");
		
		for (int i = 0; i < array.length; i++)
			writer.write(" " + formatter.format(array[i]));
		
		writer.write(" ]");
	}
}
