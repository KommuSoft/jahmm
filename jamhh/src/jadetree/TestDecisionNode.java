package jadetree;

import jutlis.algebra.Predicate;

/**
 *
 * @author kommusoft
 * @param <TSource>
 */
public abstract class TestDecisionNode<TSource> extends DecisionInode<TSource> {

    DecisionNodeBase<TSource> trueNode;
    DecisionNodeBase<TSource> falseNode;

    protected TestDecisionNode(final DecisionNode<TSource> parent, DecisionNodeBase<TSource> trueNode, DecisionNodeBase<TSource> falseNode) {
        super(parent);
        this.trueNode = trueNode;
        this.falseNode = falseNode;
    }

    public TestDecisionNode(final DecisionTree<TSource> tree, DecisionNodeBase<TSource> trueNode, DecisionNodeBase<TSource> falseNode, Iterable<TSource> toInsert) {
        this(tree, trueNode, falseNode);
        for (TSource source : toInsert) {
            this.insert(source);
        }
    }

    public TestDecisionNode(final DecisionTree<TSource> tree) {
        this(tree, new DecisionLeaf<TSource>(tree), new DecisionLeaf<TSource>(tree));
    }

    public TestDecisionNode(final DecisionTree<TSource> tree, Iterable<TSource> toInsert) {
        this(tree);
        for (TSource source : toInsert) {
            this.insert(source);
        }
    }

    protected abstract boolean test(TSource source);

    @Override
    public DecisionNodeBase<TSource> nextHop(TSource source) {
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
    public DecisionNodeBase<TSource> getTrueNode() {
        return trueNode;
    }

    /**
     * @param trueNode the trueNode to set
     */
    public void setTrueNode(DecisionNodeBase<TSource> trueNode) {
        this.trueNode = trueNode;
    }

    /**
     * @return the falseNode
     */
    public DecisionNodeBase<TSource> getFalseNode() {
        return falseNode;
    }

    /**
     * @param falseNode the falseNode to set
     */
    public void setFalseNode(DecisionNodeBase<TSource> falseNode) {
        this.falseNode = falseNode;
    }

}
