/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package jahmm.observables;

import java.text.NumberFormat;

/**
 * This class implements observations made of a real value.
 */
public final class ObservationReal extends ObservationBase<Double> implements CentroidFactory<ObservationReal> {

    /**
     * The observation's value.
     */
    public final double value;

    /**
     * An observation that can be described by a real.
     *
     * @param value The value of this observation.
     */
    public ObservationReal(double value) {
        this.value = value;
    }

    /**
     * Returns the centroid matching this observation.
     *
     * @return The corresponding observation.
     */
    @Override
    public Centroid<ObservationReal> factor() {
        return new CentroidObservationReal(this);
    }

    @Override
    public String toString(NumberFormat numberFormat) {
        return numberFormat.format(value);
    }

    @Override
    public Double getTag() {
        return this.value;
    }
}
