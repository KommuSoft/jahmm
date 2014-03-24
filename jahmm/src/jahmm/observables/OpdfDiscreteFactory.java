package jahmm.observables;

import jutlis.lists.ListArray;

/**
 *
 * @author kommusoft
 * @param <TDiscrete> The type of the discrete factory.
 */
public class OpdfDiscreteFactory<TDiscrete> implements OpdfFactory<OpdfDiscrete<TDiscrete>> {

    /**
     * This class can build <code>OpdfDiscrete</code> observation probability
     * distribution functions.
     *
     */
    final protected Iterable<TDiscrete> values;

    /**
     * Creates a factory for {@link OpdfDiscrete OpdfDiscrete} objects.
     *
     * @param values The iterable of values to construct a new OpdfDiscrete.
     */
    public OpdfDiscreteFactory(Iterable<TDiscrete> values) {
        this.values = values;
    }

    /**
     * Creates a factory for {@link OpdfDiscrete OpdfDiscrete} objects.
     *
     * @param values The array of values to construct a new OpdfDiscrete.
     */
    public OpdfDiscreteFactory(TDiscrete... values) {
        this(new ListArray<>(values));
    }

    /**
     * Generate a new {@link OpdfDiscrete OpdfDiscrete} object.
     *
     * @return A new {@link OpdfDiscrete OpdfDiscrete} object.
     */
    @Override
    public OpdfDiscrete<TDiscrete> generate() {
        return new OpdfDiscrete<>(this.values);
    }
}
