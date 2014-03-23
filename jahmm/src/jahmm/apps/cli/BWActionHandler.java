/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package jahmm.apps.cli;

import jahmm.RegularHmmBase;
import jahmm.apps.cli.CommandLineArguments.Arguments;
import jahmm.io.FileFormatException;
import jahmm.io.HmmReader;
import jahmm.io.HmmWriter;
import jahmm.io.OpdfReader;
import jahmm.io.OpdfWriter;
import jahmm.learn.RegularBaumWelchLearnerBase;
import jahmm.learn.RegularBaumWelchScaledLearnerBase;
import jahmm.observables.CentroidFactory;
import jahmm.observables.Observation;
import jahmm.observables.Opdf;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.EnumSet;
import java.util.List;
import java.util.logging.Logger;

/**
 * Applies the Baum-Welch learning algorithm.
 */
class BWActionHandler extends ActionHandler {

    private static final Logger LOG = Logger.getLogger(BWActionHandler.class.getName());

    @Override
    public void act() throws IOException, FileFormatException, AbnormalTerminationException {
        EnumSet<Arguments> args = EnumSet.of(Arguments.OPDF, Arguments.OUT_HMM, Arguments.IN_HMM, Arguments.IN_SEQ, Arguments.NB_ITERATIONS);
        CommandLineArguments.checkArgs(args);

        int nbIterations = Arguments.NB_ITERATIONS.getAsInt();
        OutputStream outStream = Arguments.OUT_HMM.getAsOutputStream();
        Writer hmmWriter = new OutputStreamWriter(outStream);
        InputStream hmmStream = Arguments.IN_HMM.getAsInputStream();
        InputStream seqStream = Arguments.IN_SEQ.getAsInputStream();
        Reader hmmReader = new InputStreamReader(hmmStream, Cli.CHARSET);
        Reader seqReader = new InputStreamReader(seqStream, Cli.CHARSET);

        learn(Types.relatedObjs(), hmmReader, seqReader, hmmWriter, nbIterations);

        hmmWriter.flush();
    }

    private <O extends Observation & CentroidFactory<O>> void learn(RelatedObjs<O> relatedObjs, Reader hmmFileReader, Reader seqFileReader, Writer hmmFileWriter, int nbIterations) throws IOException, FileFormatException {
        List<List<O>> seqs = relatedObjs.readSequences(seqFileReader);
        OpdfReader<? extends Opdf<O>> opdfReader = relatedObjs.opdfReader();
        OpdfWriter<? extends Opdf<O>> opdfWriter = relatedObjs.opdfWriter();

        RegularHmmBase<O> initHmm = HmmReader.read(hmmFileReader, opdfReader);
        RegularBaumWelchLearnerBase<O> bw = new RegularBaumWelchScaledLearnerBase<>();
        bw.setNbIterations(nbIterations);
        RegularHmmBase<O> hmm = bw.learn(initHmm, seqs);
        HmmWriter.write(hmmFileWriter, opdfWriter, hmm);
    }

}
