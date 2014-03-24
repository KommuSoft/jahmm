/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package jahmm.observables;

/**
 * This class can build <code>OpdfInteger</code> observation probability
 * distribution functions.
 *
 * @param <TEnum> The type of the enum.
 */
public class OpdfEnumFactory<TEnum extends Enum<TEnum>> implements OpdfFactory<OpdfEnum<TEnum>> {

    /**
     *
     */
    final protected Class<TEnum> valuesClass;

    /**
     * Creates a factory for {@link OpdfEnum OpdfDiscrete} objects.
     *
     * @param valuesClass The class representing the set of values over which
     * the generated observation distributions operate.
     */
    public OpdfEnumFactory(Class<TEnum> valuesClass) {
        this.valuesClass = valuesClass;
    }

    @Override
    public OpdfEnum<TEnum> generate() {
        return new OpdfEnum<>(valuesClass);
    }
}
