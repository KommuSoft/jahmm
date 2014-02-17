package jahmm.jadetree.objectattributes;

/**
 *
 * @author kommusoft
 * @param <TSource>
 * @param <TTarget>
 */
public interface ContinuObjectAttribute<TSource, TTarget> extends ObjectAttribute<TSource, TTarget>, OrdinalObjectAttribute<TSource, TTarget> {

    public abstract TTarget getMiddle(TSource source1, TSource source2);

}