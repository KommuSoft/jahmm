/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */

package be.ac.ulg.montefiore.run.jahmm.io;

import java.io.IOException;
import java.io.StreamTokenizer;

import be.ac.ulg.montefiore.run.jahmm.OpdfMultiGaussian;

/**
 * This class implements a {@link OpdfMultiGaussian} reader.  The syntax of the
 * distribution description is the following.
 * <p>
 * The description always begins with the keyword <tt>MultiGaussianOPDF</tt>.
 * The next (resp. last) symbol is an opening (resp. closing) bracket.
 * Between the backets are two series of numbers between brackets and separated
 * by a space.
 * <p>
 * The first describes the distribution's mean vector; each number
 * is the corresponding vector element, from top to bottom.
 * <p>
 * The second describes the covariance matrix; it is given line by line, from
 * top to bottom. Each line is represented by the values of its elements, from
 * left to right, separated by a space and between brackets.  
 * <p>
 * For example, reading<br>
 * <tt>MultiGaussianOPDF [ [ 5. 5. ] [ [ 1.2 .3 ] [ .3 4. ] ] ]</tt>
 * returns a distribution equivalent to<br>
 * <code>new OpdfMultiGaussian(new double[] { 5., 5. },
 *       new double[][] { { 1.2, .3 }, { .3, 4. } })</code>.
 */
public class OpdfMultiGaussianReader
extends OpdfReader<OpdfMultiGaussian>
{
	String keyword()
	{
		return "MultiGaussianOPDF";
	}

	
	public OpdfMultiGaussian read(StreamTokenizer st)
	throws IOException, FileFormatException
	{
		HmmReader.readWords(st, keyword(), "[");
				
		double[] means = OpdfReader.read(st, -1);		
		double[][] covariance = new double[means.length][];
		
		HmmReader.readWords(st, "[");
		for (int l = 0; l < covariance.length; l++)
			covariance[l] = OpdfReader.read(st, means.length);
		HmmReader.readWords(st, "]");
	
		HmmReader.readWords(st, "]");
	
		return new OpdfMultiGaussian(means, covariance);
	}
}
