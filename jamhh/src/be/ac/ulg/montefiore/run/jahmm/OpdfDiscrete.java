/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package be.ac.ulg.montefiore.run.jahmm;

import java.text.NumberFormat;
import static java.text.NumberFormat.getInstance;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Arrays.asList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import static java.util.EnumSet.allOf;
import java.util.List;
import java.util.logging.Logger;


public class OpdfDiscrete<E extends Enum<E>>
        implements Opdf<ObservationDiscrete<E>> {

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
        values = new ArrayList<>(allOf(valuesClass));

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
        values = new ArrayList<>(allOf(valuesClass));

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
        fit(asList(oa));
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
        fit(asList(o), weights);
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

    /**
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public OpdfDiscrete<E> clone() {
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
        return toString(getInstance());
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

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(OpdfDiscrete.class.getName());
}
