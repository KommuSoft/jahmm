package jahmm.jadetree;

import jahmm.jadetree.abstracts.DecisionRealNode;
import jahmm.jadetree.abstracts.DecisionInode;
import jutils.iterators.AppendIterable;
import jutils.iterators.MapIterable;
import jutlis.algebra.Function;

/**
 *
 * @author kommusoft
 * @param <TSource>
 */
public abstract class DecisionInodeBase<TSource> extends DecisionNodeBase<TSource> implements DecisionInode<TSource> {

    private DecisionLeafImpl<TSource> maximumExpand = null;
    private DecisionInode<TSource> maximumReduce = null;

    protected DecisionInodeBase(DecisionInode<TSource> parent) {
        super(parent);
    }

    @Override
    public double expandScore() {
        return this.getMaximumExpandLeaf().expandScore();
    }

    @Override
    public DecisionLeafImpl<TSource> getMaximumExpandLeaf() {
        if (this.maximumExpand == null) {
            this.maximumExpand = this.recalcMaximumExpandLeaf();
        }
        return this.maximumExpand;
    }

    @Override
    public DecisionInode<TSource> getMaximumReduceInode() {
        if (this.maximumReduce == null) {
            this.maximumReduce = this.recalcMaximumReduceInode();
        }
        return this.maximumReduce;
    }

    @Override
    public void makeDirty() {
        this.maximumExpand = null;
    }

    protected abstract DecisionLeafImpl<TSource> recalcMaximumExpandLeaf();

    protected abstract DecisionInode<TSource> recalcMaximumReduceInode();

    @Override
    public Iterable<Iterable<TSource>> getPartitionedStoredSources() {
        return new MapIterable<>(this.getChildren(), new ConvertFunction());
    }

    @Override
    public Iterable<TSource> getStoredSources() {
        return new AppendIterable<>(this.getPartitionedStoredSources());
    }

    @Override
    public DecisionRealNode<TSource> expand() {
        return this.getMaximumExpandLeaf().expand();
    }

    private class ConvertFunction implements Function<DecisionRealNode<TSource>, Iterable<TSource>> {

        private ConvertFunction() {
        }

        @Override
        public Iterable<TSource> evaluate(DecisionRealNode<TSource> x) {
            return x.getStoredSources();
        }

    }

}
