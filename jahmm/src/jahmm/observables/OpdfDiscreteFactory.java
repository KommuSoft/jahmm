package jahmm.observables;

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
     * Creates a factory for {@link OpdfEnum OpdfDiscrete} objects.
     *
     * @param values The iterable of values to construct a new OpdfDiscrete.
     */
    public OpdfDiscreteFactory(Iterable<TDiscrete> values) {
        this.values = values;
    }

    @Override
    public OpdfDiscrete<TDiscrete> generate() {
        return new OpdfDiscrete<>(this.values);
    }
}
