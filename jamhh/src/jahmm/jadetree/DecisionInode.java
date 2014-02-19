package jahmm.jadetree;

import jutils.designpatterns.CompositeNode;

/**
 *
 * @author kommusoft
 * @param <TSource> The type of objects stored in the decision tree.
 */
public interface DecisionInode<TSource> extends DecisionRealNode<TSource>, CompositeNode<DecisionNode<TSource>> {

    @Override
    public abstract Iterable<DecisionRealNode<TSource>> getChildren();

}
