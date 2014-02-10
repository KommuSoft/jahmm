package objectattributes;


/**
 *
 * @author kommusoft
 */
public abstract class ContinuObjectAttribute<TSource,TTarget> implements ObjectAttribute<TSource,TTarget> {
    
    @Override
    public ObjectAttributeType getType() {
        return ObjectAttributeType.Continu;
    }
    
}