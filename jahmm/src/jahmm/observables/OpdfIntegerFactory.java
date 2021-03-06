/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package jahmm.observables;

/**
 * This class can build <code>OpdfInteger</code> observation probability
 * functions.
 */
public final class OpdfIntegerFactory implements OpdfFactory<OpdfInteger> {

    private final int nbEntries;

    /**
     * Creates a factory for {@link OpdfInteger OpdfInteger} objects.
     *
     * @param nbEntries The number of entries of the returned distribution.
     */
    public OpdfIntegerFactory(int nbEntries) {
        this.nbEntries = nbEntries;
    }

    @Override
    public OpdfInteger generate() {
        return new OpdfInteger(nbEntries);
    }
}
