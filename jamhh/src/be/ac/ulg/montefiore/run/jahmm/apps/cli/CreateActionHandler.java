/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */

package be.ac.ulg.montefiore.run.jahmm.apps.cli;

import java.io.*;
import java.util.EnumSet;

import be.ac.ulg.montefiore.run.jahmm.*;
import be.ac.ulg.montefiore.run.jahmm.apps.cli.CommandLineArguments.Arguments;
import be.ac.ulg.montefiore.run.jahmm.io.HmmWriter;
import be.ac.ulg.montefiore.run.jahmm.io.OpdfWriter;


/**
 * Creates a Hmm and write it to file.
 */
class CreateActionHandler
extends ActionHandler
{	
	public void act()
	throws FileNotFoundException, IOException, AbnormalTerminationException
	{
		EnumSet<Arguments> args = EnumSet.of(
				Arguments.OPDF,
				Arguments.NB_STATES,
				Arguments.OUT_HMM);
		CommandLineArguments.checkArgs(args);
		
		int nbStates = Arguments.NB_STATES.getAsInt();
		OutputStream outStream = Arguments.OUT_HMM.getAsOutputStream();
		Writer outWriter = new OutputStreamWriter(outStream);
		
		write(outWriter, nbStates, Types.relatedObjs());
		
		outWriter.flush();
	}
	
	
	private <O extends Observation & CentroidFactory<O>> void
	write(Writer outWriter, int nbStates, RelatedObjs<O> relatedObjs)
	throws IOException
	{
		OpdfFactory<? extends Opdf<O>> opdfFactory = relatedObjs.opdfFactory();
		OpdfWriter<? extends Opdf<O>> opdfWriter = relatedObjs.opdfWriter();
		
		Hmm<O> hmm = new Hmm<O>(nbStates, opdfFactory);
		
		HmmWriter.write(outWriter, opdfWriter, hmm);
	}
}
