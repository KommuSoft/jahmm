package objectattributes;

import jadetree.DecisionNode;
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

    public abstract <TOtherTarget> double calculateScore(List<? extends TSource> source, ObjectAttribute<? super TSource, TOtherTarget> function, Holder<Object> state);

    public abstract <TOtherTarget> DecisionNode<TSource> createDecisionNode(List<? extends TSource> source, ObjectAttribute<? super TSource, TOtherTarget> function, Holder<Object> state);

}
