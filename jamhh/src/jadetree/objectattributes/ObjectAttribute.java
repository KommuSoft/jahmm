package jadetree.objectattributes;

import jadetree.DecisionNode;
import java.util.List;
import jutlis.algebra.Function;
import jutlis.tuples.Holder;
import jutlis.Name;

/**
 *
 * @author kommusoft
 * @param <TSource> The type of the source.
 * @param <TTarget> The type of the attribute.
 */
public interface ObjectAttribute<TSource, TTarget> extends Name, Function<TSource, TTarget> {

    public abstract double calculateScore(List<? extends TSource> source, ObjectAttribute<? super TSource, ? extends Object> function, Holder<Object> state);

    public abstract DecisionNode<TSource> createDecisionNode(List<? extends TSource> source, ObjectAttribute<? super TSource, ? extends Object> function, Holder<Object> state);

}
