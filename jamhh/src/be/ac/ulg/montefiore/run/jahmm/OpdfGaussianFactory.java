/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package be.ac.ulg.montefiore.run.jahmm;

import java.util.logging.Logger;

/**
 * This class can build <code>OpdfMultiGaussian</code> observation probability
 * functions.
 */
public class OpdfGaussianFactory
        implements OpdfFactory<OpdfGaussian> {

    @Override
    public OpdfGaussian factor() {
        return new OpdfGaussian();
    }
    private static final Logger LOG = Logger.getLogger(OpdfGaussianFactory.class.getName());
}
