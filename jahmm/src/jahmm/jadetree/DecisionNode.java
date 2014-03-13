package jahmm.jadetree;

import jutils.designpatterns.CompositeComponent;

/**
 *
 * @author kommusoft
 * @param <TSource> The type of objects classified and stored in the tree.
 */
public interface DecisionNode<TSource> extends CompositeComponent<DecisionNode<TSource>, DecisionInode<TSource>> {

    public abstract void reduce();

    public abstract void expand();

    public abstract double expandScore();

    public abstract double reduceScore();

    public abstract Iterable<TSource> getStoredSources();

    public abstract Iterable<Iterable<TSource>> getPartitionedStoredSources();

    public abstract DecisionTree<TSource> getTree();

    public abstract void insert(TSource source);
    
    public abstract void insert(Iterable<TSource> sources);
    
    public abstract void insert(TSource... sources);

}
