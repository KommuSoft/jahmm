package jadetree.objectattributes;

import java.util.Set;

/**
 *
 * @author kommusoft
 * @param <TSource> The type of object to derive the type from
 * @param <TTarget> The type of object to derive to.
 */
public interface NominalObjectAttribute<TSource, TTarget> extends ObjectAttribute<TSource, TTarget> {

    public abstract Set<TTarget> getPossibleValues();

}
