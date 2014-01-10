/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package be.ac.ulg.montefiore.run.jahmm;

import java.text.NumberFormat;
import java.util.logging.Logger;


public class ObservationDiscrete<E extends Enum<E>>
        extends Observation {

    /**
     * This observation value.
     */
    public final E value;

    /**
     *
     * @param value
     */
    public ObservationDiscrete(E value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public String toString(NumberFormat nf) {
        return toString();
    }
    private static final Logger LOG = Logger.getLogger(ObservationDiscrete.class.getName());
}
