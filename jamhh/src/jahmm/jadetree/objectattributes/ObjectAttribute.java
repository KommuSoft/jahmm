package jahmm.jadetree.objectattributes;

import jahmm.jadetree.DecisionNode;
import jahmm.jadetree.DecisionNodeBase;
import java.util.List;
import jutlis.Name;
import jutlis.algebra.Function;
import jutlis.tuples.Holder;

/**
 *
 * @author kommusoft
 * @param <TSource> The type of the source.
 * @param <TTarget> The type of the attribute.
 */
public interface ObjectAttribute<TSource, TTarget> extends Name, Function<TSource, TTarget> {

    public abstract double calculateScore(List<TSource> source, Function<TSource, Object> function, Holder<Object> state);

    public abstract DecisionNodeBase<TSource> createDecisionNode(DecisionNode<TSource> parent, List<TSource> source, Function<TSource, Object> function, Holder<Object> state);

}
