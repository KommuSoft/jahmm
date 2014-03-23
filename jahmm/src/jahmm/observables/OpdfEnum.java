/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package jahmm.observables;

import jahmm.Hmm;
import java.io.IOException;
import java.io.Writer;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.logging.Logger;
import jutils.draw.DotDrawer;
import jutlis.tuples.Tuple2;
import jutlis.tuples.Tuple2Base;

/**
 * This class implements a distribution over a finite set of elements. This set
 * is implemented as an <code>enum</code>.
 *
 * @param <TEnum> The type of the enumeration.
 */
public final class OpdfEnum<TEnum extends Enum<TEnum>> extends OpdfBase<ObservationEnum<TEnum>> implements Opdf<ObservationEnum<TEnum>> {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(OpdfEnum.class.getName());

    /**
     *
     */
    protected final OpdfInteger distribution;

    /**
     *
     */
    protected final List<TEnum> values;

    /**
     *
     */
    protected final EnumMap<TEnum, ObservationInteger> toIntegerMap;

    /**
     * Builds a new probability distribution which operates on a finite set of
     * values. The probabilities are initialized so that the distribution is
     * uniformally distributed.
     *
     * @param valuesClass An {@link Enum Enum} class representing the set of
     * values.
     */
    public OpdfEnum(Class<TEnum> valuesClass) {
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
    public OpdfEnum(Class<TEnum> valuesClass, double... probabilities) {
        values = new ArrayList<>(EnumSet.allOf(valuesClass));

        if (probabilities.length == 0 || values.size() != probabilities.length) {
            throw new IllegalArgumentException();
        }

        distribution = new OpdfInteger(probabilities);
        toIntegerMap = createMap(valuesClass);
    }

    private OpdfEnum(List<TEnum> values, OpdfInteger distribution, EnumMap<TEnum, ObservationInteger> toIntegerMap) {
        this.values = values;
        this.distribution = distribution;
        this.toIntegerMap = toIntegerMap;
    }

    private EnumMap<TEnum, ObservationInteger> createMap(Class<TEnum> valuesClass) {
        EnumMap<TEnum, ObservationInteger> result = new EnumMap<>(valuesClass);
        for (TEnum value : values) {
            result.put(value, new ObservationInteger(value.ordinal()));
        }

        return result;
    }

    @Override
    public double probability(ObservationEnum<TEnum> o) {
        return distribution.probability(toIntegerMap.get(o.value));
    }

    @Override
    public ObservationEnum<TEnum> generate() {
        return new ObservationEnum<>(values.get(distribution.generate().value));
    }

    @Override
    public void fit(ObservationEnum<TEnum>... oa) {
        fit(Arrays.asList(oa));
    }

    @Override
    public void fit(Collection<? extends ObservationEnum<TEnum>> co) {
        List<ObservationInteger> dco = new ArrayList<>(co.size());
        for (ObservationEnum<TEnum> o : co) {
            dco.add(toIntegerMap.get(o.value));
        }

        distribution.fit(dco);
    }

    @Override
    public void fit(ObservationEnum<TEnum>[] o, double... weights) {
        fit(Arrays.asList(o), weights);
    }

    @Override
    public void fit(Collection<? extends ObservationEnum<TEnum>> co, double... weights) {
        List<ObservationInteger> dco = new ArrayList<>(co.size());

        for (ObservationEnum<TEnum> o : co) {
            dco.add(toIntegerMap.get(o.value));
        }

        distribution.fit(dco, weights);
    }

    @Override
    public OpdfEnum<TEnum> clone() throws CloneNotSupportedException {
        return new OpdfEnum<>(this.values, this.distribution.clone(), this.toIntegerMap);
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
            ObservationEnum o = new ObservationEnum<>(values.get(i));

            s += o + " " + numberFormat.format(probability(o))
                    + ((++i < values.size()) ? ", " : "");
        }

        return s;
    }

    @Override
    public void dotDrawNode(DotDrawer<? extends Hmm> drawer, Writer writer, String prefix) throws IOException {
        Tuple2<String, String> shapeTuple = new Tuple2Base<>("shape", "triangle");
        Tuple2<String, String> labelTuple = new Tuple2Base<>("label", "");
        for (TEnum val : this.values) {
            String vals = val.toString();
            labelTuple.setItem2(String.format("\"%s\"", vals));
            drawer.nodeStatement(writer, prefix + vals, shapeTuple, labelTuple);
        }
    }

    @Override
    public void dotDrawEdge(DotDrawer<? extends Hmm> drawer, Writer writer, String prefix, String source) throws IOException {
        Tuple2<String, String> labelTuple = new Tuple2Base<>("label", "");
        for (TEnum val : this.values) {
            String vals = val.toString();
            labelTuple.setItem2(String.format("\"%s\"", this.probability(new ObservationEnum<>(val))));
            drawer.nodeStatement(writer, prefix + vals, labelTuple);
        }
    }
}
