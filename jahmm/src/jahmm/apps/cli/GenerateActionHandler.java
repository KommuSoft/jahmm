/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package jahmm.apps.cli;

import jahmm.RegularHmmBase;
import jahmm.apps.cli.CommandLineArguments.Arguments;
import jahmm.io.FileFormatException;
import jahmm.io.HmmReader;
import jahmm.io.ObservationSequencesWriter;
import jahmm.io.ObservationWriter;
import jahmm.io.OpdfReader;
import jahmm.observables.CentroidFactory;
import jahmm.observables.Observation;
import jahmm.observables.Opdf;
import jahmm.toolbox.RegularMarkovGenerator;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * Generates observation sequences from a HMM and write it to file.
 */
class GenerateActionHandler
        extends ActionHandler {

    @Override
    public void act()
            throws FileNotFoundException, IOException, FileFormatException,
            AbnormalTerminationException {
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
            throws IOException, FileFormatException {
        ObservationWriter<O> obsWriter = relatedObjs.observationWriter();
        OpdfReader<? extends Opdf<O>> opdfReader = relatedObjs.opdfReader();
        RegularHmmBase<O> hmm = HmmReader.read(hmmFileReader, opdfReader);

        RegularMarkovGenerator<O> generator = relatedObjs.generator(hmm);

        List<List<O>> seqs = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            seqs.add(generator.observationSequence(1_000));
        }

        ObservationSequencesWriter.write(seqsFileWriter, obsWriter, seqs);
    }
}
