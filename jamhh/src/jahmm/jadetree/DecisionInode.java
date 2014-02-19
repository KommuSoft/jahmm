package jahmm.jadetree;

import jutils.designpatterns.CompositeNode;

/**
 *
 * @author kommusoft
 * @param <TSource> The type of objects stored in the decision tree.
 */
public interface DecisionInode<TSource> extends DecisionNode<TSource>, CompositeNode<DecisionNode<TSource>> {

    double expandScore();

    DecisionLeaf<TSource> getMaximumLeaf();

    void makeDirty();

}
