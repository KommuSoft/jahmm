/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */

package be.ac.ulg.montefiore.run.jahmm.apps.cli;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import be.ac.ulg.montefiore.run.jahmm.*;
import be.ac.ulg.montefiore.run.jahmm.io.*;
import be.ac.ulg.montefiore.run.jahmm.toolbox.MarkovGenerator;


/**
 * This class collects all the objects related to a specific observation
 * type.
 */
public interface RelatedObjs<O extends Observation & CentroidFactory<O>>
{
	public ObservationReader<O> observationReader();
	public ObservationWriter<O> observationWriter();
	public OpdfFactory<? extends Opdf<O>> opdfFactory();
	public OpdfReader<? extends Opdf<O>> opdfReader();
	public OpdfWriter<? extends Opdf<O>> opdfWriter();
	public List<List<O>> readSequences(Reader reader)
	throws FileFormatException, IOException;
	public MarkovGenerator<O> generator(Hmm<O> hmm);
}