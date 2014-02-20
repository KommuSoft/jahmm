package jahmm.jadetree.abstracts;

import jutils.Dirtyable;
import jutils.Idable;

/**
 *
 * @author kommusoft
 * @param <TSource> The type of elements in the tree.
 */
public interface DecisionRealNode<TSource> extends DecisionNode<TSource>, Idable, Dirtyable {

    public abstract boolean isLeaf();

    public abstract DecisionRealNode<TSource> nextHop(TSource source);

    public abstract DecisionLeaf<TSource> getMaximumExpandLeaf();

    public abstract DecisionRealInode<TSource> getMaximumReduceInode();

}
