package jahmm.jadetree;

import jutlis.algebra.Predicate;

/**
 *
 * @author kommusoft
 */
public class PredicateDecisionNode<TSource> extends TestDecisionNode<TSource> {

    private final Predicate<? super TSource> predicate;

    public PredicateDecisionNode(DecisionNode<TSource> parent, DecisionNodeBase<TSource> trueNode, DecisionNodeBase<TSource> falseNode, Predicate<? super TSource> predicate) {
        super(parent, trueNode, falseNode);
        this.predicate = predicate;
    }

    public PredicateDecisionNode(DecisionTree<TSource> tree, DecisionNodeBase<TSource> trueNode, DecisionNodeBase<TSource> falseNode, Iterable<TSource> toInsert, Predicate<? super TSource> predicate) {
        super(tree, trueNode, falseNode, toInsert);
        this.predicate = predicate;
    }

    public PredicateDecisionNode(DecisionTree<TSource> tree, Predicate<? super TSource> predicate) {
        super(tree);
        this.predicate = predicate;
    }

    public PredicateDecisionNode(DecisionTree<TSource> tree, Iterable<TSource> toInsert, Predicate<? super TSource> predicate) {
        super(tree, toInsert);
        this.predicate = predicate;
    }

    @Override
    protected boolean test(TSource source) {
        return this.getPredicate().evaluate(source);
    }

    /**
     * @return the predicate
     */
    public Predicate<? super TSource> getPredicate() {
        return predicate;
    }

}
