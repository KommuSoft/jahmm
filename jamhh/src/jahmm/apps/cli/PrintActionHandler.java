/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package jahmm.apps.cli;

import jahmm.RegularHmmBase;
import jahmm.apps.cli.CommandLineArguments.Arguments;
import jahmm.io.FileFormatException;
import jahmm.io.HmmReader;
import jahmm.io.OpdfGenericReader;
import jahmm.io.OpdfReader;
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
        RegularHmmBase<?> hmm = HmmReader.read(new InputStreamReader(in), opdfReader);

        System.out.println(hmm);
    }
}
