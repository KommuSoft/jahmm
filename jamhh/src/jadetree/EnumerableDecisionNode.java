package jadetree;

import jadetree.objectattributes.ObjectAttribute;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 *
 * @author kommusoft
 * @param <TSource>
 * @param <TTarget>
 */
public class EnumerableDecisionNode<TSource, TTarget> extends AttributeDecisionNode<TSource, TTarget> {

    final HashMap<TTarget, DecisionNodeBase<TSource>> map = new HashMap<>();

    protected EnumerableDecisionNode(final DecisionTree<TSource> tree, ObjectAttribute<? super TSource, ? extends TTarget> objectAttribute) {
        super(tree, objectAttribute);
    }

    protected EnumerableDecisionNode(final DecisionTree<TSource> tree, ObjectAttribute<? super TSource, ? extends TTarget> objectAttribute, HashMap<TTarget, ? extends List<TSource>> toInsert) {
        this(tree, objectAttribute);
        for (Entry<TTarget, ? extends List<TSource>> entry : toInsert.entrySet()) {
            map.put(entry.getKey(), new DecisionLeaf(this.getTree(), entry.getValue()));
        }
    }

    @Override
    public DecisionNodeBase<TSource> nextHop(TSource source) {
        TTarget key = this.getObjectAttribute(source);
        DecisionNodeBase<TSource> value = map.get(key);
        if (value == null) {
            value = new DecisionLeaf<>(this.getTree());
            this.map.put(key, value);
        }
        return value;
    }

    @Override
    public void makeDirty() {
        for (DecisionNodeBase<TSource> dn : this.map.values()) {
            dn.makeDirty();
        }
        super.makeDirty();
    }

    @Override
    protected DecisionLeaf<TSource> recalcMaximumLeaf() {
        double max = Double.NEGATIVE_INFINITY;
        double val;
        DecisionLeaf<TSource> leaf, maxLeaf = null;
        for (DecisionNodeBase<TSource> dn : this.map.values()) {
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
