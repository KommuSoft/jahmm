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

    protected DecisionRealInodeBase(final DecisionInode<TSource> parent) {
        super(parent);
    }

    @Override
    protected DecisionLeaf<TSource> recalcMaximumExpandLeaf() {
        return CollectionUtils.argMax(CollectionUtils.map(this.getChildren(), new MaximumExpandFunction()), new ExpandScoreFunction());
    }

    @Override
    protected DecisionRealInode<TSource> recalcMaximumReduceInode() {
        return CollectionUtils.argMax(CollectionUtils.map(this.getChildren(), new MaximumReduceFunction()), new ReduceScoreFunction());
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
