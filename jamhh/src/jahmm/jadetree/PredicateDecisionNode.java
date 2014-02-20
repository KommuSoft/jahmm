package jahmm.jadetree;

import jutlis.algebra.Predicate;

/**
 *
 * @author kommusoft
 */
public class PredicateDecisionNode<TSource> extends TestDecisionNode<TSource> {

    private final Predicate<? super TSource> predicate;

    public PredicateDecisionNode(DecisionInode<TSource> parent, DecisionRealNodeBase<TSource> trueNode, DecisionRealNodeBase<TSource> falseNode, Predicate<? super TSource> predicate) {
        super(parent, trueNode, falseNode);
        this.predicate = predicate;
    }

    public PredicateDecisionNode(DecisionInode<TSource> parent, DecisionRealNodeBase<TSource> trueNode, DecisionRealNodeBase<TSource> falseNode, Iterable<TSource> toInsert, Predicate<? super TSource> predicate) {
        super(parent, trueNode, falseNode, toInsert);
        this.predicate = predicate;
    }

    public PredicateDecisionNode(DecisionInode<TSource> parent, Predicate<? super TSource> predicate) {
        super(parent);
        this.predicate = predicate;
    }

    public PredicateDecisionNode(DecisionInode<TSource> parent, Iterable<TSource> toInsert, Predicate<? super TSource> predicate) {
        super(parent, toInsert);
        this.predicate = predicate;
    }

    @Override
    public String toString() {
        return this.getPredicate().toString();
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
