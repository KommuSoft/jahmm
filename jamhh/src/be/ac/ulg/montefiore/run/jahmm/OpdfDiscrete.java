/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package be.ac.ulg.montefiore.run.jahmm;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;

/**
 * This class implements a distribution over a finite set of elements. This set
 * is implemented as an <code>enum</code>.
 *
 * @param <E>
 */
public class OpdfDiscrete<E extends Enum<E>>
        implements Opdf<ObservationDiscrete<E>> {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    protected OpdfInteger distribution;

    /**
     *
     */
    protected final List<E> values;

    /**
     *
     */
    protected final EnumMap<E, ObservationInteger> toIntegerMap;

    /**
     * Builds a new probability distribution which operates on a finite set of
     * values. The probabilities are initialized so that the distribution is
     * uniformaly distributed.
     *
     * @param valuesClass An {@link Enum Enum} class representing the set of
     * values.
     */
    public OpdfDiscrete(Class<E> valuesClass) {
        values = new ArrayList<>(EnumSet.allOf(valuesClass));

        if (values.isEmpty()) {
            throw new IllegalArgumentException();
        }

        distribution = new OpdfInteger(values.size());
        toIntegerMap = createMap(valuesClass);
    }

    /**
     * Builds a new probability distribution which operates on integer values.
     *
     * @param valuesClass An {@link Enum Enum} class representing the set of
     * values.
     * @param probabilities Array holding one probability for each possible
     * value (<i>i.e.</i> such that <code>probabilities[i]</code> is the
     * probability of the observation <code>i</code>th element of
     * <code>values</code>.
     */
    public OpdfDiscrete(Class<E> valuesClass, double[] probabilities) {
        values = new ArrayList<>(EnumSet.allOf(valuesClass));

        if (probabilities.length == 0 || values.size() != probabilities.length) {
            throw new IllegalArgumentException();
        }

        distribution = new OpdfInteger(probabilities);
        toIntegerMap = createMap(valuesClass);
    }

    private EnumMap<E, ObservationInteger> createMap(Class<E> valuesClass) {
        EnumMap<E, ObservationInteger> result
                = new EnumMap<>(valuesClass);

        for (E value : values) {
            result.put(value, new ObservationInteger(value.ordinal()));
        }

        return result;
    }

    @Override
    public double probability(ObservationDiscrete o) {
        return distribution.probability(toIntegerMap.get(o.value));
    }

    @Override
    public ObservationDiscrete<E> generate() {
        return new ObservationDiscrete<>(values.get(distribution.generate().value));
    }

    @Override
    public void fit(ObservationDiscrete<E>... oa) {
        fit(Arrays.asList(oa));
    }

    @Override
    public void fit(Collection<? extends ObservationDiscrete<E>> co) {
        List<ObservationInteger> dco = new ArrayList<>();

        for (ObservationDiscrete<E> o : co) {
            dco.add(toIntegerMap.get(o.value));
        }

        distribution.fit(dco);
    }

    @Override
    public void fit(ObservationDiscrete<E>[] o, double[] weights) {
        fit(Arrays.asList(o), weights);
    }

    @Override
    public void fit(Collection<? extends ObservationDiscrete<E>> co,
            double[] weights) {
        List<ObservationInteger> dco = new ArrayList<>();

        for (ObservationDiscrete<E> o : co) {
            dco.add(toIntegerMap.get(o.value));
        }

        distribution.fit(dco, weights);
    }

    @SuppressWarnings("unchecked")
    @Override
    public OpdfDiscrete<E> clone() throws CloneNotSupportedException {
        try {
            OpdfDiscrete<E> opdfDiscrete = (OpdfDiscrete<E>) super.clone();
            opdfDiscrete.distribution = distribution.clone();
            return opdfDiscrete;
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return toString(NumberFormat.getInstance());
    }

    @Override
    public String toString(NumberFormat numberFormat) {
        String s = "Discrete distribution --- ";

        for (int i = 0; i < values.size();) {
            ObservationDiscrete o = new ObservationDiscrete<>(values.get(i));

            s += o + " " + numberFormat.format(probability(o))
                    + ((++i < values.size()) ? ", " : "");
        }

        return s;
    }
}
