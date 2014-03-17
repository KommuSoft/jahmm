package jahmm.jadetree;

import jutlis.algebra.Predicate;
import jutlis.lists.ListArray;

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

    public PredicateDecisionNode(DecisionInode<TSource> parent, DecisionRealNodeBase<TSource> trueNode, DecisionRealNodeBase<TSource> falseNode, Predicate<? super TSource> predicate, Iterable<TSource> toInsert) {
        super(parent, trueNode, falseNode);
        this.predicate = predicate;
        this.insert(toInsert);
    }

    public PredicateDecisionNode(DecisionInode<TSource> parent, DecisionRealNodeBase<TSource> trueNode, DecisionRealNodeBase<TSource> falseNode, Predicate<? super TSource> predicate, TSource... toInsert) {
        this(parent, trueNode, falseNode, predicate, new ListArray<>(toInsert));
    }

    public PredicateDecisionNode(DecisionInode<TSource> parent, Predicate<? super TSource> predicate) {
        super(parent);
        this.predicate = predicate;
    }

    public PredicateDecisionNode(DecisionInode<TSource> parent, Predicate<? super TSource> predicate, Iterable<TSource> toInsert) {
        super(parent);
        this.predicate = predicate;
        this.insert(toInsert);
    }

    public PredicateDecisionNode(DecisionInode<TSource> parent, Predicate<? super TSource> predicate, TSource... toInsert) {
        this(parent, predicate, new ListArray<>(toInsert));
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
