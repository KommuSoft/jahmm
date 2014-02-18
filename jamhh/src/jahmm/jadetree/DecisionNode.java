package jahmm.jadetree;

import jutils.designpatterns.CompositeComponent;

/**
 *
 * @author kommusoft
 * @param <TSource> The type of objects classified and stored in the tree.
 */
public interface DecisionNode<TSource> extends CompositeComponent<DecisionNode<TSource>> {

    /**
     * @return the tree
     */
    public DecisionTree<TSource> getTree();

}
