package jahmm.jadetree;

import jahmm.jadetree.objectattributes.ObjectAttribute;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import jutils.iterators.SingleIterable;
import jutlis.tuples.HolderBase;

/**
 *
 * @author kommusoft
 * @param <TSource> The type of objects the decision tree stores.
 */
public final class DecisionLeafBase<TSource> extends DecisionRealNodeBase<TSource> implements DecisionLeaf<TSource> {

    private static final Logger LOG = Logger.getLogger(DecisionLeafBase.class.getName());

    private final List<TSource> memory;
    private double expandScore = Double.NaN;
    private int splitIndex = -0x01;
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
    public double expandScore() {
        if (Double.isNaN(this.expandScore)) {
            this.expandScore = this.recalculateExpandScore();
        }
        return this.expandScore;
    }

    @Override
    public void insert(TSource source) {
        this.memory.add(source);
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public boolean isDirty() {
        return Double.isNaN(this.expandScore) || super.isDirty();
    }

    @Override
    public void makeDirty() {
        this.expandScore = Double.NaN;
        this.splitData.setData(null);
        this.splitIndex = -0x01;
        super.makeDirty();
    }

    private double recalculateExpandScore() {
        double maxScore = Double.NEGATIVE_INFINITY;
        int maxIndex = 0x00, i = 0x00;
        HolderBase<Object> curData = new HolderBase<>();
        ObjectAttribute<TSource, Object> target = this.getTree().getTargetAttribute();
        for (ObjectAttribute<TSource, ?> oa : this.getTree().getSourceAttributes()) {
            double sc = oa.calculateScore(this.memory, target, curData);
            if (sc > maxScore) {
                maxScore = sc;
                maxIndex = i;
                this.splitData.copyFrom(curData);
            }
            i++;
        }
        this.splitIndex = maxIndex;
        return maxScore;
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
        DecisionTree<TSource> tree = this.getTree();
        this.expandScore();
        DecisionRealNode<TSource> foo = tree.getSourceAttributes().get(splitIndex).createDecisionNode(this.getParent(), this.memory, tree.getTargetAttribute(), this.splitData);
        this.getParent().replaceChild(this, foo);
    }

}
