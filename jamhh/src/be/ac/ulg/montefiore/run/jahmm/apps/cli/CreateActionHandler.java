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
import be.ac.ulg.montefiore.run.jahmm.io.HmmWriter;
import be.ac.ulg.montefiore.run.jahmm.io.OpdfWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.EnumSet;

/**
 * Creates a Hmm and write it to file.
 */
class CreateActionHandler
        extends ActionHandler {

    @Override
    public void act()
            throws FileNotFoundException, IOException, AbnormalTerminationException {
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
            throws IOException {
        OpdfFactory<? extends Opdf<O>> opdfFactory = relatedObjs.opdfFactory();
        OpdfWriter<? extends Opdf<O>> opdfWriter = relatedObjs.opdfWriter();

        Hmm<O> hmm = new Hmm<>(nbStates, opdfFactory);

        HmmWriter.write(outWriter, opdfWriter, hmm);
    }
}
