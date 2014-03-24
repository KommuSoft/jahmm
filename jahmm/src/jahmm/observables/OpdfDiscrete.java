package jahmm.observables;

import jahmm.Hmm;
import java.io.IOException;
import java.io.Writer;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import jutils.collections.CollectionUtils;
import jutils.draw.DotDrawer;
import jutlis.lists.ListArray;
import jutlis.tuples.Tuple2;
import jutlis.tuples.Tuple2Base;

/**
 *
 * @author kommusoft
 */
public final class OpdfDiscrete<TDiscrete> extends OpdfBase<ObservationDiscrete<TDiscrete>> implements Opdf<ObservationDiscrete<TDiscrete>> {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(OpdfDiscrete.class.getName());

    /**
     *
     */
    protected final OpdfInteger distribution;

    /**
     *
     */
    protected final ArrayList<TDiscrete> values;

    /**
     *
     */
    protected final HashMap<TDiscrete, ObservationInteger> toIntegerMap;

    /**
     * Builds a new probability distribution which operates on a finite set of
     * values. The probabilities are initialized so that the distribution is
     * uniformally distributed.
     *
     * @param values An array of potential values.
     */
    public OpdfDiscrete(TDiscrete... values) {
        this(new ListArray<>(values));
    }

    /**
     * Builds a new probability distribution which operates on a finite set of
     * values. The probabilities are initialized so that the distribution is
     * uniformally distributed.
     *
     * @param values An iterable of potential values.
     */
    public OpdfDiscrete(Iterable<TDiscrete> values) {
        this.values = new ArrayList<>();
        CollectionUtils.addAll(this.values, values);
        int sz = this.values.size();
        if (sz <= 0x00) {
            throw new IllegalArgumentException();
        }
        toIntegerMap = createMap(this.values);
        distribution = new OpdfInteger(sz);
    }

    /**
     * Builds a new probability distribution which operates on integer values.
     *
     * @param values An iterable of potential values.
     * @param probabilities Array holding one probability for each possible
     * value (<i>i.e.</i> such that <code>probabilities[i]</code> is the
     * probability of the observation <code>i</code>th element of
     * <code>values</code>.
     */
    public OpdfDiscrete(Iterable<TDiscrete> values, double... probabilities) {
        this.values = new ArrayList<>();
        CollectionUtils.addAll(this.values, values);
        int sz = this.values.size();

        if (probabilities.length == 0 || sz != probabilities.length) {
            throw new IllegalArgumentException();
        }
        toIntegerMap = createMap(this.values);
        distribution = new OpdfInteger(probabilities);
    }

    /**
     * Builds a new probability distribution which operates on integer values.
     *
     * @param values An array of potential values.
     * @param probabilities Array holding one probability for each possible
     * value (<i>i.e.</i> such that <code>probabilities[i]</code> is the
     * probability of the observation <code>i</code>th element of
     * <code>values</code>.
     */
    public OpdfDiscrete(TDiscrete[] values, double... probabilities) {
        this(new ListArray<>(values), probabilities);
    }

    private OpdfDiscrete(ArrayList<TDiscrete> values, OpdfInteger distribution, HashMap<TDiscrete, ObservationInteger> toIntegerMap) {
        this.values = values;
        this.distribution = distribution;
        this.toIntegerMap = toIntegerMap;
    }

    private HashMap<TDiscrete, ObservationInteger> createMap(ArrayList<TDiscrete> values) {
        HashMap<TDiscrete, ObservationInteger> result = new HashMap<>(values.size());
        int index = 0x00;
        for (TDiscrete value : values) {
            result.put(value, new ObservationInteger(index));
            index++;
        }
        return result;
    }

    @Override
    public double probability(ObservationDiscrete<TDiscrete> o) {
        return distribution.probability(toIntegerMap.get(o.value));
    }

    @Override
    public ObservationDiscrete<TDiscrete> generate() {
        return new ObservationDiscrete<TDiscrete>(values.get(distribution.generate().value));
    }

    @Override
    public void fit(ObservationDiscrete<TDiscrete>... oa) {
        fit(Arrays.asList(oa));
    }

    @Override
    public void fit(Collection<? extends ObservationDiscrete<TDiscrete>> co) {
        List<ObservationInteger> dco = new ArrayList<>(co.size());
        for (ObservationDiscrete<TDiscrete> o : co) {
            dco.add(toIntegerMap.get(o.value));
        }

        distribution.fit(dco);
    }

    @Override
    public void fit(ObservationDiscrete<TDiscrete>[] o, double... weights) {
        fit(Arrays.asList(o), weights);
    }

    @Override
    public void fit(Collection<? extends ObservationDiscrete<TDiscrete>> co, double... weights) {
        List<ObservationInteger> dco = new ArrayList<>(co.size());

        for (ObservationDiscrete<TDiscrete> o : co) {
            dco.add(toIntegerMap.get(o.value));
        }

        distribution.fit(dco, weights);
    }

    @Override
    public OpdfDiscrete<TDiscrete> clone() throws CloneNotSupportedException {
        @SuppressWarnings("unchecked")
        ArrayList<TDiscrete> vls = (ArrayList<TDiscrete>) this.values.clone();
        @SuppressWarnings("unchecked")
        HashMap<TDiscrete, ObservationInteger> map = (HashMap<TDiscrete, ObservationInteger>) this.values.clone();
        return new OpdfDiscrete<>(vls, this.distribution.clone(), map);
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
            ObservationDiscrete<TDiscrete> o = new ObservationDiscrete<>(values.get(i));

            s += o + " " + numberFormat.format(probability(o))
                    + ((++i < values.size()) ? ", " : "");
        }

        return s;
    }

    @Override
    public void dotDrawNode(DotDrawer<? extends Hmm> drawer, Writer writer, String prefix) throws IOException {
        Tuple2<String, String> shapeTuple = new Tuple2Base<>("shape", "triangle");
        Tuple2<String, String> labelTuple = new Tuple2Base<>("label", "");
        for (TDiscrete val : this.values) {
            String vals = val.toString();
            labelTuple.setItem2(String.format("\"%s\"", vals));
            drawer.nodeStatement(writer, prefix + vals, shapeTuple, labelTuple);
        }
    }

    @Override
    public void dotDrawEdge(DotDrawer<? extends Hmm> drawer, Writer writer, String prefix, String source) throws IOException {
        Tuple2<String, String> labelTuple = new Tuple2Base<>("label", "");
        for (TDiscrete val : this.values) {
            String vals = val.toString();
            labelTuple.setItem2(String.format("\"%s\"", this.probability(new ObservationDiscrete<>(val))));
            drawer.nodeStatement(writer, prefix + vals, labelTuple);
        }
    }

}
