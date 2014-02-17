package jahmm.jadetree;

/**
 *
 * @author kommusoft
 * @param <TSource>
 */
public abstract class DecisionNodeBase<TSource> implements DecisionNode<TSource> {

    private final DecisionNode<TSource> parent;

    public DecisionNodeBase(final DecisionNode<TSource> parent) {
        this.parent = parent;
    }

    public boolean isLeaf() {
        return false;
    }

    public DecisionNodeBase<TSource> nextHop(TSource source) {
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
    @Override
    public DecisionTree<TSource> getTree() {
        return this.parent.getTree();
    }

}
