package objectattributes;

import java.util.Comparator;


/**
 *
 * @author kommusoft
 * @param <TSource>
 * @param <TTarget>
 */
public abstract class ContinuObjectAttribute<TSource,TTarget> implements ObjectAttribute<TSource,TTarget>, OrdinalObjectAttribute<TSource,TTarget> {
    
    @Override
    public ObjectAttributeType getType() {
        return ObjectAttributeType.Continu;
    }
    
    public abstract TTarget getBetween(TSource source1, TSource source2);
    
    public abstract TTarget compareWith (TSource source, TTarget target);
    
}