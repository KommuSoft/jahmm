package jahmm.jadetree;

import jahmm.jadetree.abstracts.DecisionInode;
import jahmm.jadetree.abstracts.DecisionRealNode;
import jutils.iterators.AppendIterable;
import jutils.iterators.MapIterable;
import jutlis.algebra.Function;

/**
 *
 * @author kommusoft
 * @param <TSource>
 */
public abstract class DecisionInodeBase<TSource> extends DecisionNodeBase<TSource> implements DecisionInode<TSource> {

    protected DecisionInodeBase(DecisionInode<TSource> parent) {
        super(parent);
    }

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
