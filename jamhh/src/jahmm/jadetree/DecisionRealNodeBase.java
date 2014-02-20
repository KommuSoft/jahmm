package jahmm.jadetree;

import jahmm.jadetree.abstracts.DecisionInode;
import jahmm.jadetree.abstracts.DecisionLeaf;
import jahmm.jadetree.abstracts.DecisionRealInode;
import jahmm.jadetree.abstracts.DecisionRealNode;

/**
 *
 * @author kommusoft
 * @param <TSource>
 */
public abstract class DecisionRealNodeBase<TSource> extends DecisionNodeBase<TSource> implements DecisionRealNode<TSource> {

    private DecisionLeaf<TSource> maximumExpandLeaf = null;
    private DecisionRealInode<TSource> maximumReduceInode = null;

    protected DecisionRealNodeBase(DecisionInode<TSource> parent) {
        super(parent);
    }

    @Override
    public void reduce() {
        this.maximumReduceInode.reduceThis();
    }

    @Override
    public void expand() {
        this.maximumExpandLeaf.expandThis();
    }

    @Override
    public double expandScore() {
        return this.getMaximumExpandLeaf().expandScore();
    }

    @Override
    public double reduceScore() {
        return this.getMaximumReduceInode().reduceScore();
    }

    @Override
    public void insert(TSource source) {
        this.nextHop(source).insert(source);
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public DecisionRealNode<TSource> nextHop(TSource source) {
        return null;
    }

    @Override
    public DecisionLeaf<TSource> getMaximumExpandLeaf() {
        if (this.maximumExpandLeaf == null) {
            this.maximumExpandLeaf = recalcMaximumExpandLeaf();
        }
        return this.maximumExpandLeaf;
    }

    @Override
    public DecisionRealInode<TSource> getMaximumReduceInode() {
        if (this.maximumReduceInode == null) {
            this.maximumReduceInode = recalcMaximumReduceInode();
        }
        return this.maximumReduceInode;
    }

    @Override
    public boolean isDirty() {
        return (this.maximumExpandLeaf == null);
    }

    @Override
    public void makeDirty() {
        this.maximumExpandLeaf = null;
        this.maximumReduceInode = null;
    }

    protected abstract DecisionLeaf<TSource> recalcMaximumExpandLeaf();

    protected abstract DecisionRealInode<TSource> recalcMaximumReduceInode();

}
