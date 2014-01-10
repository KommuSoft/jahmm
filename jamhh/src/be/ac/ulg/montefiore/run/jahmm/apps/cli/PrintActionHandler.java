/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package be.ac.ulg.montefiore.run.jahmm.apps.cli;

import be.ac.ulg.montefiore.run.jahmm.Hmm;
import be.ac.ulg.montefiore.run.jahmm.apps.cli.CommandLineArguments.Arguments;
import be.ac.ulg.montefiore.run.jahmm.io.FileFormatException;
import be.ac.ulg.montefiore.run.jahmm.io.HmmReader;
import be.ac.ulg.montefiore.run.jahmm.io.OpdfGenericReader;
import be.ac.ulg.montefiore.run.jahmm.io.OpdfReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.EnumSet;

/**
 * Creates a Hmm and writes it to file.
 */
class PrintActionHandler extends ActionHandler {

    @SuppressWarnings({"unchecked"}) // We use a generic reader 
    @Override
    public void act()
            throws FileFormatException, IOException, FileNotFoundException,
            AbnormalTerminationException {
        EnumSet<Arguments> args = EnumSet.of(Arguments.IN_HMM);
        CommandLineArguments.checkArgs(args);

        InputStream in = Arguments.IN_HMM.getAsInputStream();
        OpdfReader opdfReader = new OpdfGenericReader();
        Hmm<?> hmm = HmmReader.read(new InputStreamReader(in), opdfReader);

        System.out.println(hmm);
    }
}
