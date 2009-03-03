/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */

package be.ac.ulg.montefiore.run.jahmm;

/**
 * Creates a centroid for type <O>.  Used by the k-means algorithm.
 */
public interface CentroidFactory<O>
{
	public Centroid<O> factor();
}
