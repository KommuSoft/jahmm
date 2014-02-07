package objectattributes;

/**
 *
 * @author kommusoft
 * @param <TSource> The type of the source.
 */
public interface ObjectAttribute<TSource> {
    
    Object getAttribute (TSource source);
    
}
