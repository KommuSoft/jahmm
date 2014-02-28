package jahmm.jadetree.objectattributes;

/**
 *
 * @author kommusoft
 */
public abstract class ComparableOrdinalObjectAttribute<TSource, TTarget extends Comparable<TTarget>> implements OrdinalObjectAttribute<TSource, TTarget> {

    @Override
    public TTarget getBetween(TSource source1, TSource source2) {
        return this.evaluate(source1);
    }

    @Override
    public int compareWith(TSource source, TTarget target) {
        return this.evaluate(source).compareTo(target);
    }

    @Override
    public int compare(TSource source1, TSource source2) {
        return this.evaluate(source1).compareTo(this.evaluate(source2));
    }

}
