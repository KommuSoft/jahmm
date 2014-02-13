/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jadetree;

import utils.Predicate;

/**
 *
 * @author kommusoft
 */
public class PredicateDecisionNode<TSource> extends DecisionInode<TSource> {
    final Predicate<? super TSource> predicate;
    DecisionNode trueNode;
    DecisionNode falseNode;

    public PredicateDecisionNode(Predicate<? super outer.TSource> predicate, DecisionNode trueNode, DecisionNode falseNode) {
        super(tree);
        this.predicate = predicate;
        this.trueNode = trueNode;
        this.falseNode = falseNode;
    }

    public PredicateDecisionNode(Predicate<? super outer.TSource> predicate, Id3ClassificationTree<outer.TSource> tree, final Id3ClassificationTree<TSource> outer) {
        super(predicate, new DecisionLeaf(outer), new DecisionLeaf(outer), tree);
        this.outer = outer;
    }

    @Override
    public DecisionNode nextHop(outer.TSource source) {
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
    protected DecisionLeaf recalcMaximumLeaf() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the predicate
     */
    public Predicate<? super outer.TSource> getPredicate() {
        return predicate;
    }

    /**
     * @return the trueNode
     */
    public DecisionNode getTrueNode() {
        return trueNode;
    }

    /**
     * @param trueNode the trueNode to set
     */
    public void setTrueNode(DecisionNode trueNode) {
        this.trueNode = trueNode;
    }

    /**
     * @return the falseNode
     */
    public DecisionNode getFalseNode() {
        return falseNode;
    }

    /**
     * @param falseNode the falseNode to set
     */
    public void setFalseNode(DecisionNode falseNode) {
        this.falseNode = falseNode;
    }
    
}
