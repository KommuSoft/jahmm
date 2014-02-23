/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package jahmm.apps.cli;

import jahmm.observables.CentroidFactory;
import jahmm.Hmm;
import jahmm.observables.Observation;
import jahmm.observables.Opdf;
import jahmm.observables.OpdfFactory;
import jahmm.apps.cli.CommandLineArguments.Arguments;
import jahmm.io.FileFormatException;
import jahmm.io.HmmWriter;
import jahmm.io.OpdfWriter;
import jahmm.learn.KMeansLearner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.EnumSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Applies the k-means learning algorithm.
 */
class KMeansActionHandler
        extends ActionHandler {

    @Override
    public void act()
            throws FileNotFoundException, IOException, FileFormatException,
            AbnormalTerminationException {
        EnumSet<Arguments> args = EnumSet.of(
                Arguments.OPDF,
                Arguments.NB_STATES,
                Arguments.OUT_HMM,
                Arguments.IN_SEQ);
        CommandLineArguments.checkArgs(args);

        int nbStates = Arguments.NB_STATES.getAsInt();
        OutputStream outStream = Arguments.OUT_HMM.getAsOutputStream();
        Writer writer = new OutputStreamWriter(outStream);
        InputStream st = Arguments.IN_SEQ.getAsInputStream();
        Reader reader = new InputStreamReader(st);
        try {
            learn(nbStates, Types.relatedObjs(), reader, writer);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(KMeansActionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        writer.flush();
    }

    private <O extends Observation & CentroidFactory<O>> void
            learn(int nbStates, RelatedObjs<O> relatedObjs, Reader reader,
                    Writer writer)
            throws IOException, FileFormatException, CloneNotSupportedException {
        OpdfFactory<? extends Opdf<O>> opdfFactory = relatedObjs.opdfFactory();
        List<List<O>> seqs = relatedObjs.readSequences(reader);
        OpdfWriter<? extends Opdf<O>> opdfWriter = relatedObjs.opdfWriter();

        KMeansLearner<O> kl = new KMeansLearner<>(nbStates, opdfFactory,
                seqs);
        Hmm<O> hmm = kl.learn();

        HmmWriter.write(writer, opdfWriter, hmm);
    }
}
