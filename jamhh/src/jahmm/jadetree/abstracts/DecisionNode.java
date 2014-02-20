package jahmm.jadetree.abstracts;

import jutils.designpatterns.CompositeComponent;

/**
 *
 * @author kommusoft
 * @param <TSource> The type of objects classified and stored in the tree.
 */
public interface DecisionNode<TSource> extends CompositeComponent<DecisionNode<TSource>,DecisionInode<TSource>> {

    public abstract DecisionRealNode<TSource> reduce();

    public abstract DecisionRealNode<TSource> expand();

    public abstract double expandScore();

    public abstract double reduceScore();

    public abstract Iterable<TSource> getStoredSources();

    public abstract Iterable<Iterable<TSource>> getPartitionedStoredSources();

    @Override
    public abstract DecisionInode<TSource> getParent();

    public abstract void insert(TSource source);

}
