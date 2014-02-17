package jadetree;

import jadetree.objectattributes.OrdinalObjectAttribute;

/**
 *
 * @author kommusoft
 * @param <TSource> The type of objects hold in and classified by the tree.
 * @param <TState> The state to compare the objects with.
 */
public class OrdinalTestDecisionNode<TSource, TState> extends TestDecisionNode<TSource> {

    private final OrdinalObjectAttribute<TSource, TState> ordinalArgument;
    private final TState state;

    public OrdinalTestDecisionNode(DecisionNode<TSource> parent, DecisionNodeBase<TSource> trueNode, DecisionNodeBase<TSource> falseNode, OrdinalObjectAttribute<TSource, TState> ordinalArgument, TState state) {
        super(parent, trueNode, falseNode);
        this.ordinalArgument = ordinalArgument;
        this.state = state;
    }

    public OrdinalTestDecisionNode(DecisionNode<TSource> tree, DecisionNodeBase<TSource> trueNode, DecisionNodeBase<TSource> falseNode, Iterable<TSource> toInsert, OrdinalObjectAttribute<TSource, TState> ordinalArgument, TState state) {
        super(tree, trueNode, falseNode, toInsert);
        this.ordinalArgument = ordinalArgument;
        this.state = state;
    }

    public OrdinalTestDecisionNode(DecisionNode<TSource> tree, OrdinalObjectAttribute<TSource, TState> ordinalArgument, TState state) {
        super(tree);
        this.ordinalArgument = ordinalArgument;
        this.state = state;
    }

    public OrdinalTestDecisionNode(DecisionNode<TSource> tree, Iterable<TSource> toInsert, OrdinalObjectAttribute<TSource, TState> ordinalArgument, TState state) {
        super(tree, toInsert);
        this.ordinalArgument = ordinalArgument;
        this.state = state;
    }

    @Override
    protected boolean test(TSource source) {
        return this.ordinalArgument.compareWith(source, this.state) <= 0x00;
    }

}
