package jahmm.jadetree;

import jutils.iterators.AppendIterable;
import jutils.iterators.MapIterable;
import jutlis.algebra.Function;

/**
 *
 * @author kommusoft
 * @param <TSource>
 */
public abstract class DecisionInodeBase<TSource> extends DecisionNodeBase<TSource> implements DecisionInode<TSource> {

    private DecisionLeaf<TSource> maximumLeaf = null;

    protected DecisionInodeBase(DecisionNode<TSource> parent) {
        super(parent);
    }

    @Override
    public double expandScore() {
        return this.getMaximumExpandLeaf().expandScore();
    }

    @Override
    public DecisionLeaf<TSource> getMaximumExpandLeaf() {
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

    @Override
    public Iterable<Iterable<TSource>> getPartitionedStoredSources() {
        return new MapIterable<>(this.getChildren(), new ConvertFunction());
    }

    @Override
    public Iterable<TSource> getStoredSources() {
        return new AppendIterable<>(this.getPartitionedStoredSources());
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
