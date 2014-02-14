package jadetree;

import jadetree.objectattributes.OrdinalObjectAttribute;

/**
 *
 * @author kommusoft
 */
public class OrdinalTestDecisionNode<TSource, TState> extends TestDecisionNode<TSource> {

    private final OrdinalObjectAttribute<TSource, TState> ordinalArgument;
    private final TState state;

    public OrdinalTestDecisionNode(DecisionNode<TSource> parent, DecisionNodeBase<TSource> trueNode, DecisionNodeBase<TSource> falseNode, OrdinalObjectAttribute<TSource, TState> ordinalArgument, TState state) {
        super(parent, trueNode, falseNode);
        this.ordinalArgument = ordinalArgument;
        this.state = state;
    }

    public OrdinalTestDecisionNode(DecisionTree<TSource> tree, DecisionNodeBase<TSource> trueNode, DecisionNodeBase<TSource> falseNode, Iterable<TSource> toInsert, OrdinalObjectAttribute<TSource, TState> ordinalArgument, TState state) {
        super(tree, trueNode, falseNode, toInsert);
        this.ordinalArgument = ordinalArgument;
        this.state = state;
    }

    public OrdinalTestDecisionNode(DecisionTree<TSource> tree, OrdinalObjectAttribute<TSource, TState> ordinalArgument, TState state) {
        super(tree);
        this.ordinalArgument = ordinalArgument;
        this.state = state;
    }

    public OrdinalTestDecisionNode(DecisionTree<TSource> tree, Iterable<TSource> toInsert, OrdinalObjectAttribute<TSource, TState> ordinalArgument, TState state) {
        super(tree, toInsert);
        this.ordinalArgument = ordinalArgument;
        this.state = state;
    }

    @Override
    protected boolean test(TSource source) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
