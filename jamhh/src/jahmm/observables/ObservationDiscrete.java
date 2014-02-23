/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package jahmm.observables;

import java.text.NumberFormat;

/**
 * This class implements observations whose values are taken out of a finite set
 * implemented as an enumeration.
 *
 * @param <E>
 */
public final class ObservationDiscrete<E extends Enum<E>> extends ObservationBase {

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
}
