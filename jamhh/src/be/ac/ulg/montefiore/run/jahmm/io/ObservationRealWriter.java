/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package be.ac.ulg.montefiore.run.jahmm.io;

import be.ac.ulg.montefiore.run.jahmm.ObservationReal;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * Writes an {@link ObservationReal ObservationReal} up to (and including) the
 * semi-colon.
 */
public class ObservationRealWriter
        extends ObservationWriter<ObservationReal> {

    @Override
    public void write(ObservationReal observation, Writer writer)
            throws IOException {
        writer.write(observation.value + "; ");
    }
    private static final Logger LOG = Logger.getLogger(ObservationRealWriter.class.getName());
}
