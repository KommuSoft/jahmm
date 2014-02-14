package jadetree.objectattributes;

public abstract class ContinuObjectAttributeBase<TSource, TTarget> extends OrdinalObjectAttributeBase<TSource,TTarget> implements ContinuObjectAttribute<TSource, TTarget> {

    @Override
    public TTarget getBetween(TSource source1, TSource source2) {
        return this.getMiddle(source1, source2);
    }

}
