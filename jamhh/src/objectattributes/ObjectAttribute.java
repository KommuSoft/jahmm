package objectattributes;

import java.util.List;
import jutlis.Function;
import jutlis.Holder;
import jutlis.Name;

/**
 *
 * @author kommusoft
 * @param <TSource> The type of the source.
 * @param <TTarget> The type of the attribute.
 */
public interface ObjectAttribute<TSource, TTarget> extends Name, Function<TSource, TTarget> {

    public abstract double calculateScore(List<? extends TSource> source, Holder<Object> state);

    public abstract void createDecisionNode(List<? extends TSource> source, Holder<Object> state);

    public abstract double calculateEntropy(Iterable<? extends TSource> source);

    public abstract double calculateEntropyPartition(Iterable<? extends Iterable<? extends TSource>> source);

}
