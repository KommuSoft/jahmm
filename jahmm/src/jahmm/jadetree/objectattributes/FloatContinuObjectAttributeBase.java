package jahmm.jadetree.objectattributes;

/**
 *
 * @author kommusoft
 * @param <TSource>
 */
public abstract class FloatContinuObjectAttributeBase<TSource> extends ContinuObjectAttributeBase<TSource, Float> {

    @Override
    public Float getMiddle(TSource source1, TSource source2) {
        return 0.5f * (this.evaluate(source1) + this.evaluate(source2));
    }

    @Override
    public int compareWith(TSource source, Float target) {
        return this.evaluate(source).compareTo(target);
    }

    @Override
    public int compare(TSource source1, TSource source2) {
        return this.evaluate(source1).compareTo(this.evaluate(source2));
    }

}
