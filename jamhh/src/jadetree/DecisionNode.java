package jadetree;

/**
 *
 * @author kommusoft
 * @param <TSource> The type of objects classified and stored in the tree.
 */
public interface DecisionNode<TSource> {

    /**
     * @return the tree
     */
    public DecisionTree<TSource> getTree();

}
