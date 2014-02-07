/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package jahmm;

import java.text.NumberFormat;

/**
 * This class holds an integer observation.
 */
public final class ObservationInteger extends ObservationBase implements CentroidFactory<ObservationInteger> {

    /**
     * The observation's value.
     */
    public final int value;

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
}
