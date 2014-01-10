/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package be.ac.ulg.montefiore.run.jahmm.apps.cli;

import be.ac.ulg.montefiore.run.jahmm.io.FileFormatException;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class implements a command line interface for the Jahmm library.
 */
public class Cli {

    /**
     *
     */
    public final static String CHARSET = "ISO-8859-1";

    /**
     * The entry point of the CLI.
     *
     * @param args Command line arguments.
     * @throws java.io.IOException
     */
    public static void main(String... args)
            throws IOException {
        try {
            System.exit(run(args));
        } catch (AbnormalTerminationException e) {
            System.err.println(e);
            System.exit(-1);
        }
    }

    /**
     *
     * @param args
     * @return
     * @throws IOException
     * @throws AbnormalTerminationException
     */
    static public int run(String... args)
            throws IOException, AbnormalTerminationException {
        // Allows this method to be called more than once
        CommandLineArguments.reset();

        ActionHandler.Actions action = CommandLineArguments.parseAction(args);
        if (action == null) {
            throw new WrongArgumentsException("Valid action required");
        }

        ActionHandler actionHandler = null;

        try {
            actionHandler = action.handler().newInstance();
        } catch (Exception e) {
            throw new InternalError(e.toString());
        }

        actionHandler.parseArguments(args);

        try {
            actionHandler.act();
        } catch (FileNotFoundException | FileFormatException e) {
            System.err.println(e);
            return -1;
        }

        return 0;
    }
}
