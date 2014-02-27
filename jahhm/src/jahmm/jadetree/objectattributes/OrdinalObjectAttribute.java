package jahmm.jadetree.objectattributes;

import java.util.Comparator;

/**
 *
 * @author kommusoft
 * @param <TSource>
 * @param <TTarget>
 */
public interface OrdinalObjectAttribute<TSource, TTarget> extends ObjectAttribute<TSource, TTarget>, Comparator<TSource> {

    public abstract TTarget getBetween(TSource source1, TSource source2);

    public abstract int compareWith(TSource source, TTarget target);

}
