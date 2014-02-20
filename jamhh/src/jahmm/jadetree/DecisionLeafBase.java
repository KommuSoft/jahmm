package jahmm.jadetree;

import jahmm.jadetree.abstracts.DecisionInode;
import jahmm.jadetree.abstracts.DecisionLeaf;
import jahmm.jadetree.abstracts.DecisionRealInode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import jutils.iterators.SingleIterable;
import jutlis.tuples.HolderBase;

/**
 *
 * @author kommusoft
 * @param <TSource> The type of objects the decision tree stores.
 */
public final class DecisionLeafBase<TSource> extends DecisionRealNodeBase<TSource> implements DecisionLeaf<TSource> {

    private final List<TSource> memory;
    private double expandScore = Double.NaN;
    private int splitIndex = 0x00;
    private final HolderBase<Object> splitData = new HolderBase<>();

    public DecisionLeafBase(final DecisionInode<TSource> parent) {
        this(parent, new ArrayList<TSource>());
    }

    public DecisionLeafBase(final DecisionInode<TSource> parent, final List<TSource> memory) {
        super(parent);
        this.memory = memory;
    }

    public DecisionLeafBase(final DecisionInode<TSource> parent, final Iterable<? extends TSource> memory) {
        this(parent);
        for (TSource item : memory) {
            this.memory.add(item);
        }
    }

    @Override
    protected DecisionLeaf<TSource> recalcMaximumExpandLeaf() {
        return this;
    }

    @Override
    protected DecisionRealInode<TSource> recalcMaximumReduceInode() {
        return null;
    }

    @Override
    public Iterable<TSource> getStoredSources() {
        return Collections.unmodifiableList(this.memory);
    }

    @Override
    public Iterable<Iterable<TSource>> getPartitionedStoredSources() {
        return new SingleIterable<>(this.getStoredSources());
    }

    @Override
    public void expandThis() {
        this.expandScore();
        DecisionTree<TSource> tree = this.getTree();
        this.expandScore();
        DecisionRealNode<TSource> foo = tree.getSourceAttributes().get(splitIndex).createDecisionNode(this.getParent(), this.memory, tree.getTargetAttribute(), this.splitData);
        this.getParent().replaceChild(this, foo);
    }

}
