package objectattributes;

/**
 *
 * @author kommusoft
 */
public abstract class EnumerableObjectAttribute<TSource,TTarget> implements ObjectAttribute<TSource,TTarget> {
    
    @Override
    public ObjectAttributeType getType() {
        return ObjectAttributeType.Enumerable;
    }
    
}
