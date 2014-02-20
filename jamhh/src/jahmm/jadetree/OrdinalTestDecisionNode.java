package jahmm.jadetree;

import jahmm.jadetree.abstracts.DecisionInode;
import jahmm.jadetree.objectattributes.OrdinalObjectAttribute;

/**
 *
 * @author kommusoft
 * @param <TSource> The type of objects hold in and classified by the tree.
 * @param <TState> The state to compare the objects with.
 */
public class OrdinalTestDecisionNode<TSource, TState> extends TestDecisionNode<TSource> {

    private final OrdinalObjectAttribute<TSource, TState> ordinalArgument;
    private final TState state;

    public OrdinalTestDecisionNode(DecisionInode<TSource> parent, DecisionNodeBase<TSource> trueNode, DecisionNodeBase<TSource> falseNode, OrdinalObjectAttribute<TSource, TState> ordinalArgument, TState state) {
        super(parent, trueNode, falseNode);
        this.ordinalArgument = ordinalArgument;
        this.state = state;
    }

    public OrdinalTestDecisionNode(DecisionInode<TSource> tree, DecisionNodeBase<TSource> trueNode, DecisionNodeBase<TSource> falseNode, Iterable<TSource> toInsert, OrdinalObjectAttribute<TSource, TState> ordinalArgument, TState state) {
        super(tree, trueNode, falseNode, toInsert);
        this.ordinalArgument = ordinalArgument;
        this.state = state;
    }

    public OrdinalTestDecisionNode(DecisionInode<TSource> tree, OrdinalObjectAttribute<TSource, TState> ordinalArgument, TState state) {
        super(tree);
        this.ordinalArgument = ordinalArgument;
        this.state = state;
    }

    public OrdinalTestDecisionNode(DecisionInode<TSource> tree, Iterable<TSource> toInsert, OrdinalObjectAttribute<TSource, TState> ordinalArgument, TState state) {
        super(tree, toInsert);
        this.ordinalArgument = ordinalArgument;
        this.state = state;
    }

    @Override
    protected boolean test(TSource source) {
        return this.getOrdinalArgument().compareWith(source, this.getState()) <= 0x00;
    }

    @Override
    public String toString() {
        return String.format("%s < %s", this.getOrdinalArgument().getName(), this.getState().toString());
    }

    /**
     * @return the ordinalArgument
     */
    public OrdinalObjectAttribute<TSource, TState> getOrdinalArgument() {
        return ordinalArgument;
    }

    /**
     * @return the state
     */
    public TState getState() {
        return state;
    }

}
