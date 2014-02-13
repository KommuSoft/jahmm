package jadetree;

/**
 *
 * @author kommusoft
 * @param <TSource>
 */
public abstract class DecisionNode<TSource> {

    private final DecisionTree<TSource> tree;

    public DecisionNode(final DecisionTree<TSource> tree) {
        this.tree = tree;
    }

    public boolean isLeaf() {
        return false;
    }

    public DecisionNode<TSource> nextHop(TSource source) {
        return this;
    }

    public abstract double expandScore();

    public void insert(TSource source) {
        this.nextHop(source).insert(source);
    }

    public abstract void makeDirty();

    public abstract DecisionLeaf<TSource> getMaximumLeaf();

    /**
     * @return the tree
     */
    protected DecisionTree<TSource> getTree() {
        return tree;
    }

}
