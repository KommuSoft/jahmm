/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package be.ac.ulg.montefiore.run.jahmm.apps.cli;

import static be.ac.ulg.montefiore.run.jahmm.apps.cli.CommandLineArguments.parseAction;
import static be.ac.ulg.montefiore.run.jahmm.apps.cli.CommandLineArguments.reset;
import be.ac.ulg.montefiore.run.jahmm.io.FileFormatException;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.System.exit;
import java.util.logging.Logger;

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
            exit(run(args));
        } catch (AbnormalTerminationException e) {
            System.err.println(e);
            exit(-1);
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
        reset();

        ActionHandler.Actions action = parseAction(args);
        if (action == null) {
            throw new WrongArgumentsException("Valid action required");
        }

        ActionHandler actionHandler = null;

        try {
            actionHandler = action.handler().newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
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
    private static final Logger LOG = Logger.getLogger(Cli.class.getName());
}
