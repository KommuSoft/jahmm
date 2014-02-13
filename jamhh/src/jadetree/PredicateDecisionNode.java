package jadetree;

import jutlis.algebra.Predicate;

/**
 *
 * @author kommusoft
 * @param <TSource>
 */
public class PredicateDecisionNode<TSource> extends DecisionInode<TSource> {

    final Predicate<? super TSource> predicate;
    DecisionNode<TSource> trueNode;
    DecisionNode<TSource> falseNode;

    public PredicateDecisionNode(Predicate<? super TSource> predicate, DecisionNode<TSource> trueNode, DecisionNode<TSource> falseNode, final DecisionTree<TSource> tree) {
        super(tree);
        this.predicate = predicate;
        this.trueNode = trueNode;
        this.falseNode = falseNode;
    }

    public PredicateDecisionNode(Predicate<? super TSource> predicate, final DecisionTree<TSource> tree) {
        this(predicate, new DecisionLeaf<TSource>(tree), new DecisionLeaf<TSource>(tree), tree);
    }

    @Override
    public DecisionNode<TSource> nextHop(TSource source) {
        if (this.getPredicate().evaluate(source)) {
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the predicate
     */
    public Predicate<? super TSource> getPredicate() {
        return predicate;
    }

    /**
     * @return the trueNode
     */
    public DecisionNode<TSource> getTrueNode() {
        return trueNode;
    }

    /**
     * @param trueNode the trueNode to set
     */
    public void setTrueNode(DecisionNode<TSource> trueNode) {
        this.trueNode = trueNode;
    }

    /**
     * @return the falseNode
     */
    public DecisionNode<TSource> getFalseNode() {
        return falseNode;
    }

    /**
     * @param falseNode the falseNode to set
     */
    public void setFalseNode(DecisionNode<TSource> falseNode) {
        this.falseNode = falseNode;
    }

}
