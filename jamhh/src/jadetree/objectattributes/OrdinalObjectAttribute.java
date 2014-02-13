package jadetree.objectattributes;

import java.util.Comparator;

/**
 *
 * @author kommusoft
 * @param <TSource>
 * @param <TTarget>
 */
public interface OrdinalObjectAttribute<TSource, TTarget> extends ObjectAttribute<TSource, TTarget>, Comparator<TSource> {

}
