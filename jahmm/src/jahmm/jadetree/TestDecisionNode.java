package jahmm.jadetree;

import java.util.Collections;
import jutlis.lists.ListArray;

/**
 *
 * @author kommusoft
 * @param <TSource>
 */
public abstract class TestDecisionNode<TSource> extends DecisionRealInodeBase<TSource> {

    private DecisionRealNode<TSource> trueNode;
    private DecisionRealNode<TSource> falseNode;

    protected TestDecisionNode(final DecisionInode<TSource> parent, DecisionRealNodeBase<TSource> trueNode, DecisionRealNodeBase<TSource> falseNode) {
        super(parent);
        this.trueNode = trueNode;
        this.falseNode = falseNode;
    }

    protected TestDecisionNode(final DecisionInode<TSource> parent) {
        this(parent, new DecisionLeafBase<>(parent), new DecisionLeafBase<>(parent));
    }

    protected abstract boolean test(TSource source);

    @Override
    public DecisionRealNode<TSource> nextHop(TSource source) {
        if (this.test(source)) {
            return this.getTrueNode();
        } else {
            return this.getFalseNode();
        }
    }

    /**
     * @return the trueNode
     */
    public DecisionRealNode<TSource> getTrueNode() {
        return trueNode;
    }

    /**
     * @param trueNode the trueNode to set
     */
    public void setTrueNode(DecisionRealNode<TSource> trueNode) {
        this.trueNode = trueNode;
    }

    /**
     * @return the falseNode
     */
    public DecisionRealNode<TSource> getFalseNode() {
        return falseNode;
    }

    /**
     * @param falseNode the falseNode to set
     */
    public void setFalseNode(DecisionRealNode<TSource> falseNode) {
        this.falseNode = falseNode;
    }

    @Override
    public void replaceChild(DecisionRealNode<TSource> was, DecisionRealNode<TSource> now) {
        if (was == this.trueNode) {
            this.trueNode = now;
        }
        if (was == this.falseNode) {
            this.falseNode = now;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Iterable<DecisionRealNode<TSource>> getChildren() {
        return Collections.unmodifiableCollection(new ListArray<>(this.trueNode, this.falseNode));
    }

}
