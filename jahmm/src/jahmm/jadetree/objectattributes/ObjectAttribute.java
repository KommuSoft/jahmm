package jahmm.jadetree.objectattributes;

import jahmm.jadetree.DecisionInode;
import jahmm.jadetree.DecisionRealNode;
import java.util.List;
import jutils.Name;
import jutlis.algebra.Function;
import jutlis.tuples.Holder;

/**
 *
 * @author kommusoft
 * @param <TSource> The type of the source.
 * @param <TTarget> The type of the attribute.
 */
public interface ObjectAttribute<TSource, TTarget> extends Name, Function<TSource, TTarget> {

    public abstract double calculateScore(Function<TSource, ? extends Object> function, Holder<Object> state, List<TSource> source);

    public abstract double calculateScore(Function<TSource, ? extends Object> function, Holder<Object> state, TSource... source);

    public abstract DecisionRealNode<TSource> createDecisionNode(DecisionInode<TSource> parent, List<TSource> source, Function<TSource, ? extends Object> function, Holder<Object> state);

}
