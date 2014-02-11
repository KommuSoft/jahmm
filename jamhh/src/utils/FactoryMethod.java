package utils;

/**
 *
 * @author kommusoft
 * @param <TResult> The type of the object to generate.
 */
public interface FactoryMethod<TResult> {
    
    public TResult generate ();
    
}
