package jahmm.jadetree;

import java.util.logging.Logger;
import jutils.collections.CollectionUtils;
import jutlis.algebra.Function;

/**
 *
 * @author kommusoft
 * @param <TSource> The type of elements stored in the decision tree.
 */
public abstract class DecisionRealInodeBase<TSource> extends DecisionRealNodeBase<TSource> implements DecisionRealInode<TSource> {

    private static final Logger LOG = Logger.getLogger(DecisionRealInodeBase.class.getName());
    private double reduceScore = Double.NaN;

    protected DecisionRealInodeBase(final DecisionInode<TSource> parent) {
        super(parent);
    }

    @Override
    public void makeDirty() {
        super.makeDirty();
        for (DecisionRealNode<TSource> drn : this.getChildren()) {
            drn.makeDirty();
        }
    }

    @Override
    protected DecisionLeaf<TSource> recalcMaximumExpandLeaf() {
        return CollectionUtils.argMax(CollectionUtils.map(this.getChildren(), new MaximumExpandFunction()), new ExpandScoreFunction());
    }

    @Override
    public void makeInnerDirty() {
        this.reduceScore = Double.NaN;
        super.makeInnerDirty();
    }

    @Override
    public double reduceScore() {
        this.getMaximumReduceInode();
        return this.reduceScore;
    }

    @Override
    protected DecisionRealInode<TSource> recalcMaximumReduceInode() {
        DecisionRealInode<TSource> reducer = CollectionUtils.argMax(CollectionUtils.map(this.getChildren(), new MaximumReduceFunction()), new ReduceScoreFunction());
        if (reducer == null) {
            double reduceScore = this.recalcSelfReduce();
            if (reducer.reduceScore() < reduceScore) {
                reducer = this;
                this.reduceScore = reduceScore;
            }
        } else {
            this.reduceScore = reducer.reduceScore();
        }
        return reducer;
    }

    private double recalcSelfReduce() {
        return DecisionTreeUtils.calculateInformationGainReduce(this.getPartitionedStoredSources(), this.getTree().getTargetAttribute());
    }

    @Override
    public Iterable<TSource> getStoredSources() {
        return CollectionUtils.append(this.getPartitionedStoredSources());
    }

    @Override
    public Iterable<Iterable<TSource>> getPartitionedStoredSources() {
        return CollectionUtils.map(this.getChildren(), new StoredSourcesFunction());
    }

    @Override
    public void reduceThis() {
        DecisionLeafBase<TSource> lfb = new DecisionLeafBase<>(this.getParent(), this.getStoredSources());
        this.getParent().replaceChild(this, lfb);
    }

    private class MaximumExpandFunction implements Function<DecisionRealNode<TSource>, DecisionLeaf<TSource>> {

        @Override
        public DecisionLeaf<TSource> evaluate(DecisionRealNode<TSource> x) {
            return x.getMaximumExpandLeaf();
        }

    }

    private class ExpandScoreFunction implements Function<DecisionLeaf<TSource>, Double> {

        @Override
        public Double evaluate(DecisionLeaf<TSource> x) {
            return x.expandScore();
        }

    }

    private class MaximumReduceFunction implements Function<DecisionRealNode<TSource>, DecisionRealInode<TSource>> {

        @Override
        public DecisionRealInode<TSource> evaluate(DecisionRealNode<TSource> x) {
            return x.getMaximumReduceInode();
        }

    }

    private class ReduceScoreFunction implements Function<DecisionRealInode<TSource>, Double> {

        @Override
        public Double evaluate(DecisionRealInode<TSource> x) {
            return x.reduceScore();
        }

    }

    private class StoredSourcesFunction implements Function<DecisionRealNode<TSource>, Iterable<TSource>> {

        @Override
        public Iterable<TSource> evaluate(DecisionRealNode<TSource> x) {
            return x.getStoredSources();
        }

    }

}
