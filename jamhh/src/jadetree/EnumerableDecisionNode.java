package jadetree;

import java.util.HashMap;
import objectattributes.ObjectAttribute;

/**
 *
 * @author kommusoft
 * @param <TSource>
 * @param <TTarget>
 */
public class EnumerableDecisionNode<TSource, TTarget> extends AttributeDecisionNode<TSource, TTarget> {

    final HashMap<TTarget, DecisionNode<TSource>> map = new HashMap<>();

    protected EnumerableDecisionNode(ObjectAttribute<? super TSource, ? extends TTarget> objectAttribute, final DecisionTree<TSource> tree) {
        super(objectAttribute, tree);
    }

    @Override
    public DecisionNode<TSource> nextHop(TSource source) {
        TTarget key = this.getObjectAttribute(source);
        DecisionNode<TSource> value = map.get(key);
        if (value == null) {
            value = new DecisionLeaf<>(this.getTree());
            this.map.put(key, value);
        }
        return value;
    }

    @Override
    public void makeDirty() {
        for (DecisionNode<TSource> dn : this.map.values()) {
            dn.makeDirty();
        }
        super.makeDirty();
    }

    @Override
    protected DecisionLeaf<TSource> recalcMaximumLeaf() {
        double max = Double.NEGATIVE_INFINITY;
        double val;
        DecisionLeaf<TSource> leaf, maxLeaf = null;
        for (DecisionNode<TSource> dn : this.map.values()) {
            leaf = dn.getMaximumLeaf();
            val = leaf.expandScore();
            if (val > max) {
                max = val;
                maxLeaf = leaf;
            }
        }
        return maxLeaf;
    }

}
