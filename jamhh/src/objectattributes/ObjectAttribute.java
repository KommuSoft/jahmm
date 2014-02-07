package objectattributes;

import utils.Name;

/**
 *
 * @author kommusoft
 * @param <TSource> The type of the source.
 */
public interface ObjectAttribute<TSource,TTarget> extends Name {
    
    TTarget getAttribute (TSource source);
    
}
