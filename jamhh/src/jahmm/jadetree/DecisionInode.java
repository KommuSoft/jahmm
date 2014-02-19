package jahmm.jadetree;

import jutils.designpatterns.CompositeNode;

/**
 *
 * @author kommusoft
 * @param <TSource> The type of objects stored in the decision tree.
 */
public interface DecisionInode<TSource> extends DecisionRealNode<TSource>, CompositeNode<DecisionNode<TSource>> {

    public abstract DecisionLeaf<TSource> getMaximumLeaf();

    @Override
    public abstract Iterable<DecisionRealNode<TSource>> getChildren();

}
