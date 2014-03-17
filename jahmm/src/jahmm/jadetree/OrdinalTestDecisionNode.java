package jahmm.jadetree;

import jahmm.jadetree.objectattributes.OrdinalObjectAttribute;
import java.util.logging.Logger;
import jutlis.lists.ListArray;

/**
 *
 * @author kommusoft
 * @param <TSource> The type of objects hold in and classified by the tree.
 * @param <TState> The state to compare the objects with.
 */
public class OrdinalTestDecisionNode<TSource, TState> extends TestDecisionNode<TSource> {

    private static final Logger LOG = Logger.getLogger(OrdinalTestDecisionNode.class.getName());

    private final OrdinalObjectAttribute<TSource, TState> ordinalArgument;
    private final TState state;

    public OrdinalTestDecisionNode(DecisionInode<TSource> parent, DecisionRealNodeBase<TSource> trueNode, DecisionRealNodeBase<TSource> falseNode, OrdinalObjectAttribute<TSource, TState> ordinalArgument, TState state) {
        super(parent, trueNode, falseNode);
        this.ordinalArgument = ordinalArgument;
        this.state = state;
    }

    public OrdinalTestDecisionNode(DecisionInode<TSource> tree, DecisionRealNodeBase<TSource> trueNode, DecisionRealNodeBase<TSource> falseNode, OrdinalObjectAttribute<TSource, TState> ordinalArgument, TState state, Iterable<TSource> toInsert) {
        super(tree, trueNode, falseNode);
        this.ordinalArgument = ordinalArgument;
        this.state = state;
        this.insert(toInsert);
    }

    public OrdinalTestDecisionNode(DecisionInode<TSource> tree, DecisionRealNodeBase<TSource> trueNode, DecisionRealNodeBase<TSource> falseNode, OrdinalObjectAttribute<TSource, TState> ordinalArgument, TState state, TSource... toInsert) {
        this(tree, trueNode, falseNode, ordinalArgument, state, new ListArray<>(toInsert));
    }

    public OrdinalTestDecisionNode(DecisionInode<TSource> tree, OrdinalObjectAttribute<TSource, TState> ordinalArgument, TState state) {
        super(tree);
        this.ordinalArgument = ordinalArgument;
        this.state = state;
    }

    public OrdinalTestDecisionNode(DecisionInode<TSource> tree, OrdinalObjectAttribute<TSource, TState> ordinalArgument, TState state, Iterable<TSource> toInsert) {
        super(tree);
        this.ordinalArgument = ordinalArgument;
        this.state = state;
        this.insert(toInsert);
    }

    public OrdinalTestDecisionNode(DecisionInode<TSource> tree, OrdinalObjectAttribute<TSource, TState> ordinalArgument, TState state, TSource... toInsert) {
        this(tree, ordinalArgument, state, new ListArray<>(toInsert));
    }

    @Override
    protected boolean test(TSource source) {
        return this.getOrdinalArgument().compareWith(source, this.getState()) < 0x00;
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
