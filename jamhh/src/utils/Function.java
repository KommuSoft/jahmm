package utils;

/**
 *
 * @author kommusoft
 * @param <TSource> The domain of the function.
 * @param <TResult> The range of the function.
 */
public interface Function<TSource, TResult> {

    public abstract TResult evaluate(TSource x);

}
