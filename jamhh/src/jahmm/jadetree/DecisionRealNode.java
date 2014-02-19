package jahmm.jadetree;

import jutlis.Idable;

/**
 *
 * @author kommusoft
 * @param <TSource> The type of elements in the tree.
 */
public interface DecisionRealNode<TSource> extends DecisionNode<TSource>, Idable {

    public abstract boolean isLeaf();

    public abstract void makeDirty();

    public abstract DecisionRealNode<TSource> reduceThis();

    public abstract DecisionRealNode<TSource> reduce();

    public abstract DecisionRealNode<TSource> expand();

    //public abstract DecisionRealNode<TSource> expandThis();
    public abstract DecisionRealNode<TSource> nextHop(TSource source);

    public abstract DecisionLeaf<TSource> getMaximumExpandLeaf();

    public abstract DecisionInode<TSource> getMaximumReduceLeaf();

    public abstract void insert(TSource source);

}
