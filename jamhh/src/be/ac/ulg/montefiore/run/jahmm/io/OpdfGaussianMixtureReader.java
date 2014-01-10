/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */

package be.ac.ulg.montefiore.run.jahmm.io;

import java.io.IOException;
import java.io.StreamTokenizer;

import be.ac.ulg.montefiore.run.jahmm.OpdfGaussian;
import be.ac.ulg.montefiore.run.jahmm.OpdfGaussianMixture;


/**
 * This class implements a {@link OpdfGaussian} reader.  The syntax of the
 * distribution description is the following.
 * <p>
 * The description always begins with the keyword <tt>GaussianMxitureOPDF</tt>.
 * Three series of numbers between brackets and separated by a space follow;
 * numbers are separated by a space. The first the gaussians mean values.  The
 * second is the gaussians variance.  The last sequence of number gives each
 * gaussian proportion.
 * <p>
 * For example, reading <br>
 * <tt>GaussianMixtureOPDF [ [ 1.2 2. ] [ .1 .9 ] [ .4 .6 ] ]</tt> returns a
 * distribution equivalent to <br>
 * <code>new OpdfGaussianMixture(new double[] { 1.2, 2. },
 * new double[] { .1, .9 }, new double[] { .4, .6 })</code>.
 */
public class OpdfGaussianMixtureReader
extends OpdfReader<OpdfGaussianMixture>
{
	String keyword()
	{
		return "GaussianMixtureOPDF";
	}

	
	public OpdfGaussianMixture read(StreamTokenizer st)
	throws IOException,	FileFormatException {
		HmmReader.readWords(st, keyword(), "[");
		
		double[] means = OpdfReader.read(st, -1);
		double[] variances = OpdfReader.read(st, means.length);
		double[] proportions = OpdfReader.read(st, means.length);
		
		HmmReader.readWords(st, "]");
		
		return new OpdfGaussianMixture(means, variances,
				proportions);
	}
}
