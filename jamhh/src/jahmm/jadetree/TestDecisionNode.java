package jahmm.jadetree;

import java.util.Collections;
import jutils.iterators.AppendIterable;
import jutlis.lists.ListArray;

/**
 *
 * @author kommusoft
 * @param <TSource>
 */
public abstract class TestDecisionNode<TSource> extends DecisionInodeBase<TSource> {

    private DecisionRealNode<TSource> trueNode;
    private DecisionRealNode<TSource> falseNode;

    protected TestDecisionNode(final DecisionNode<TSource> parent, DecisionNodeBase<TSource> trueNode, DecisionNodeBase<TSource> falseNode) {
        super(parent);
        this.trueNode = trueNode;
        this.falseNode = falseNode;
    }

    public TestDecisionNode(final DecisionNode<TSource> parent, DecisionNodeBase<TSource> trueNode, DecisionNodeBase<TSource> falseNode, Iterable<TSource> toInsert) {
        this(parent, trueNode, falseNode);
        for (TSource source : toInsert) {
            this.insert(source);
        }
    }

    public TestDecisionNode(final DecisionNode<TSource> parent) {
        this(parent, new DecisionLeaf<TSource>(parent), new DecisionLeaf<TSource>(parent));
    }

    public TestDecisionNode(final DecisionNode<TSource> parent, Iterable<TSource> toInsert) {
        this(parent);
        for (TSource source : toInsert) {
            this.insert(source);
        }
    }

    protected abstract boolean test(TSource source);

    @Override
    public DecisionRealNode<TSource> nextHop(TSource source) {
        if (this.test(source)) {
            return this.getTrueNode();
        } else {
            return this.getFalseNode();
        }
    }

    @Override
    public void makeDirty() {
        this.getTrueNode().makeDirty();
        this.getFalseNode().makeDirty();
        super.makeDirty();
    }

    @Override
    protected DecisionLeaf<TSource> recalcMaximumLeaf() {
        DecisionLeaf<TSource> maxLeaf = this.falseNode.getMaximumLeaf();
        double max = maxLeaf.expandScore();
        DecisionLeaf<TSource> leaf = this.trueNode.getMaximumLeaf();
        double val = maxLeaf.expandScore();
        if (val > max) {
            maxLeaf = leaf;
        }
        return maxLeaf;
    }

    /**
     * @return the trueNode
     */
    public DecisionRealNode<TSource> getTrueNode() {
        return trueNode;
    }

    /**
     * @param trueNode the trueNode to set
     */
    public void setTrueNode(DecisionRealNode<TSource> trueNode) {
        this.trueNode = trueNode;
    }

    /**
     * @return the falseNode
     */
    public DecisionRealNode<TSource> getFalseNode() {
        return falseNode;
    }

    /**
     * @param falseNode the falseNode to set
     */
    public void setFalseNode(DecisionRealNode<TSource> falseNode) {
        this.falseNode = falseNode;
    }

    @Override
    public void replaceChild(DecisionRealNode<TSource> was, DecisionRealNode<TSource> now) {
        if (was == this.trueNode) {
            this.trueNode = now;
        }
        if (was == this.falseNode) {
            this.falseNode = now;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Iterable<DecisionRealNode<TSource>> getChildren() {
        return Collections.unmodifiableCollection(new ListArray<>(this.trueNode, this.falseNode));
    }

}
