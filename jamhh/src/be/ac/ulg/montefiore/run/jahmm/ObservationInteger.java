/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package be.ac.ulg.montefiore.run.jahmm;

import java.text.NumberFormat;
import java.util.logging.Logger;

/**
 * This class holds an integer observation.
 */
public class ObservationInteger extends Observation
        implements CentroidFactory<ObservationInteger> {

    /**
     * The observation's value.
     */
    final public int value;

    /**
     * An observation that can be described by an integer.
     *
     * @param value The value of this observation.
     */
    public ObservationInteger(int value) {
        this.value = value;
    }

    /**
     * Returns the centroid matching this observation.
     *
     * @return The corresponding observation.
     */
    @Override
    public Centroid<ObservationInteger> factor() {
        return new CentroidObservationInteger(this);
    }

    @Override
    public String toString(NumberFormat numberFormat) {
        return numberFormat.format(value);
    }
    private static final Logger LOG = Logger.getLogger(ObservationInteger.class.getName());
}
