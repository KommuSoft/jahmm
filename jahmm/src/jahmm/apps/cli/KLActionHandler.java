/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package jahmm.apps.cli;

import jahmm.RegularHmmBase;
import jahmm.apps.cli.CommandLineArguments.Arguments;
import jahmm.io.FileFormatException;
import jahmm.io.HmmReader;
import jahmm.observables.CentroidFactory;
import jahmm.observables.Observation;
import jahmm.toolbox.RegularKullbackLeiblerDistanceCalculatorBase;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.EnumSet;

/**
 * This class implements an action that computes the Kullback-Leibler distance
 * between two HMMs.
 */
public class KLActionHandler extends ActionHandler {

    /**
     *
     * @throws FileNotFoundException
     * @throws IOException
     * @throws FileFormatException
     * @throws AbnormalTerminationException
     */
    @Override
    public void act() throws FileNotFoundException, IOException,
            FileFormatException, AbnormalTerminationException {
        EnumSet<Arguments> args = EnumSet.of(
                Arguments.OPDF,
                Arguments.IN_HMM,
                Arguments.IN_KL_HMM);
        CommandLineArguments.checkArgs(args);

        InputStream st = Arguments.IN_KL_HMM.getAsInputStream();
        Reader reader1 = new InputStreamReader(st);
        st = Arguments.IN_HMM.getAsInputStream();
        Reader reader2 = new InputStreamReader(st);

        distance(Types.relatedObjs(), reader1, reader2);
    }

    private <O extends Observation & CentroidFactory<O>> void
            distance(RelatedObjs<O> relatedObjs, Reader reader1, Reader reader2)
            throws IOException, FileFormatException {
        RegularHmmBase<O> hmm1 = HmmReader.read(reader1, relatedObjs.opdfReader());
        RegularHmmBase<O> hmm2 = HmmReader.read(reader2, relatedObjs.opdfReader());

        RegularKullbackLeiblerDistanceCalculatorBase kl
                = new RegularKullbackLeiblerDistanceCalculatorBase();
        System.out.println(kl.distance(hmm1, hmm2));
    }
}
