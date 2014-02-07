/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package jahmm.apps.cli;

/**
 * Implements an exception thrown when the CLI terminates abruptly.
 */
public class AbnormalTerminationException
        extends Exception {

    private static final long serialVersionUID = 1;

    /**
     * Creates an exception thrown when the CLI terminates abruptly.
     */
    public AbnormalTerminationException() {
    }

    /**
     * Creates an exception thrown when the CLI terminates abruptly.
     *
     * @param s A string describing the problem.
     */
    public AbnormalTerminationException(String s) {
        super(s);
    }
}
