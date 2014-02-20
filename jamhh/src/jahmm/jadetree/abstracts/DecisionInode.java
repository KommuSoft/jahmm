package jahmm.jadetree.abstracts;

import jutils.designpatterns.CompositeNode;

/**
 *
 * @author kommusoft
 * @param <TSource> The type of objects stored in the decision tree.
 */
public interface DecisionInode<TSource> extends DecisionNode<TSource>, CompositeNode<DecisionNode<TSource>,DecisionInode<TSource>> {

    @Override
    public abstract Iterable<DecisionRealNode<TSource>> getChildren();

    public abstract void replaceChild(DecisionRealNode<TSource> was, DecisionRealNode<TSource> now);

}
