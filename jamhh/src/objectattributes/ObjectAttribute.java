package objectattributes;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import utils.Name;

/**
 *
 * @author kommusoft
 * @param <TSource> The type of the source.
 * @param <TTarget> The type of the attribute.
 */
public interface ObjectAttribute<TSource, TTarget> extends Name {

    public abstract TTarget getAttribute(TSource source) throws IllegalAccessException, InvocationTargetException;

    public abstract double calculateScore(List<? extends TSource> source) throws IllegalAccessException, InvocationTargetException;

    public abstract void createDecisionNode(List<? extends TSource> source) throws IllegalAccessException, InvocationTargetException;
    
    public abstract double calculateEntropy (Iterable<? extends TSource> source) throws IllegalAccessException, InvocationTargetException;
    
    public abstract double calculateEntropyPartition (Iterable<? extends Iterable<? extends TSource>> source) throws IllegalAccessException, InvocationTargetException;

}
