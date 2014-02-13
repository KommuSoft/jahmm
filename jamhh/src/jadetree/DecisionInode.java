package jadetree;

/**
 *
 * @author kommusoft
 */
public abstract class DecisionInode<TSource> extends DecisionNode<TSource> {

    private DecisionLeaf<TSource> maximumLeaf = null;

    protected DecisionInode(DecisionTree<TSource> tree) {
        super(tree);
    }

    @Override
    public double expandScore() {
        return this.getMaximumLeaf().expandScore();
    }

    @Override
    public DecisionLeaf<TSource> getMaximumLeaf() {
        if (this.maximumLeaf == null) {
            this.maximumLeaf = this.recalcMaximumLeaf();
        }
        return this.maximumLeaf;
    }

    @Override
    public void makeDirty() {
        this.maximumLeaf = null;
    }

    protected abstract DecisionLeaf<TSource> recalcMaximumLeaf();

}
