/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package be.ac.ulg.montefiore.run.jahmm.apps.cli;

import be.ac.ulg.montefiore.run.jahmm.CentroidFactory;
import be.ac.ulg.montefiore.run.jahmm.Hmm;
import be.ac.ulg.montefiore.run.jahmm.Observation;
import be.ac.ulg.montefiore.run.jahmm.Opdf;
import be.ac.ulg.montefiore.run.jahmm.OpdfFactory;
import be.ac.ulg.montefiore.run.jahmm.apps.cli.CommandLineArguments.Arguments;
import static be.ac.ulg.montefiore.run.jahmm.apps.cli.CommandLineArguments.checkArgs;
import static be.ac.ulg.montefiore.run.jahmm.apps.cli.Types.relatedObjs;
import be.ac.ulg.montefiore.run.jahmm.io.FileFormatException;
import be.ac.ulg.montefiore.run.jahmm.io.HmmWriter;
import static be.ac.ulg.montefiore.run.jahmm.io.HmmWriter.write;
import be.ac.ulg.montefiore.run.jahmm.io.OpdfWriter;
import be.ac.ulg.montefiore.run.jahmm.learn.KMeansLearner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.EnumSet;
import static java.util.EnumSet.of;
import java.util.List;
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
        EnumSet<Arguments> args = of(
                Arguments.OPDF,
                Arguments.NB_STATES,
                Arguments.OUT_HMM,
                Arguments.IN_SEQ);
        checkArgs(args);

        int nbStates = Arguments.NB_STATES.getAsInt();
        OutputStream outStream = Arguments.OUT_HMM.getAsOutputStream();
        Writer writer = new OutputStreamWriter(outStream);
        InputStream st = Arguments.IN_SEQ.getAsInputStream();
        Reader reader = new InputStreamReader(st);

        learn(nbStates, relatedObjs(), reader, writer);

        writer.flush();
    }

    private <O extends Observation & CentroidFactory<O>> void
            learn(int nbStates, RelatedObjs<O> relatedObjs, Reader reader,
                    Writer writer)
            throws IOException, FileFormatException {
        OpdfFactory<? extends Opdf<O>> opdfFactory = relatedObjs.opdfFactory();
        List<List<O>> seqs = relatedObjs.readSequences(reader);
        OpdfWriter<? extends Opdf<O>> opdfWriter = relatedObjs.opdfWriter();

        KMeansLearner<O> kl = new KMeansLearner<>(nbStates, opdfFactory,
                seqs);
        Hmm<O> hmm = kl.learn();

        write(writer, opdfWriter, hmm);
    }
    private static final Logger LOG = Logger.getLogger(KMeansActionHandler.class.getName());
}
