/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package jahmm.observables;

import java.text.NumberFormat;
import java.util.logging.Logger;

/**
 * This class implements observations whose values are taken out of a finite set
 * implemented as an enumeration.
 *
 * @param <TEnum> The type of the enumeration.
 */
public final class ObservationEnum<TEnum extends Enum<TEnum>> extends ObservationBase<TEnum> {

    private static final Logger LOG = Logger.getLogger(ObservationEnum.class.getName());

    /**
     * This observation value.
     */
    public final TEnum value;

    /**
     *
     * @param value
     */
    public ObservationEnum(TEnum value) {
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

    @Override
    public TEnum getTag() {
        return this.value;
    }
}
