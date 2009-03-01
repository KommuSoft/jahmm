package be.ac.ulg.montefiore.run.jahmm.apps.cli;

import java.io.*;
import java.util.*;

import be.ac.ulg.montefiore.run.jahmm.*;
import be.ac.ulg.montefiore.run.jahmm.apps.cli.CommandLineArguments.Arguments;
import be.ac.ulg.montefiore.run.jahmm.io.*;
import be.ac.ulg.montefiore.run.jahmm.toolbox.MarkovGenerator;


/**
 * Generates observation sequences from a HMM and write it to file.
 */
class GenerateActionHandler
extends ActionHandler
{
	public void act()
	throws FileNotFoundException, IOException, FileFormatException,
	AbnormalTerminationException
	{
		EnumSet<Arguments> args = EnumSet.of(
				Arguments.OPDF,
				Arguments.OUT_SEQS,
				Arguments.IN_HMM);
		CommandLineArguments.checkArgs(args);
		
		InputStream hmmStream = Arguments.IN_HMM.getAsInputStream();
		Reader hmmFileReader = new InputStreamReader(hmmStream);
		OutputStream seqsStream = Arguments.OUT_SEQS.getAsOutputStream();
		Writer seqsFileWriter = new OutputStreamWriter(seqsStream);
		
		write(hmmFileReader, seqsFileWriter, Types.relatedObjs());
		
		seqsFileWriter.flush();
	}
	
	
	private <O extends Observation & CentroidFactory<O>> void
	write(Reader hmmFileReader, Writer seqsFileWriter,
			RelatedObjs<O> relatedObjs)
	throws IOException, FileFormatException
	{
		ObservationWriter<O> obsWriter = relatedObjs.observationWriter();
		OpdfReader<? extends Opdf<O>> opdfReader = relatedObjs.opdfReader();
		Hmm<O> hmm = HmmReader.read(hmmFileReader, opdfReader);
		
		MarkovGenerator<O> generator = relatedObjs.generator(hmm);
		
		List<List<O>> seqs = new ArrayList<List<O>>();
		for (int i = 0; i < 100; i++)
			seqs.add(generator.observationSequence(1000));
		
		ObservationSequencesWriter.write(seqsFileWriter, obsWriter, seqs);
	}
}
