package jahmm.jadetree;

import jahmm.jadetree.objectattributes.ObjectAttribute;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Logger;

/**
 *
 * @author kommusoft
 * @param <TSource>
 * @param <TTarget>
 */
public class EnumerableDecisionNode<TSource, TTarget> extends AttributeDecisionNode<TSource, TTarget> {

    private static final Logger LOG = Logger.getLogger(EnumerableDecisionNode.class.getName());

    final HashMap<TTarget, DecisionRealNode<TSource>> map = new HashMap<>();

    public EnumerableDecisionNode(final DecisionInode<TSource> parent, ObjectAttribute<TSource, TTarget> objectAttribute) {
        super(parent, objectAttribute);
    }

    public EnumerableDecisionNode(final DecisionInode<TSource> tree, ObjectAttribute<TSource, TTarget> objectAttribute, HashMap<TTarget, ? extends List<TSource>> toInsert) {
        this(tree, objectAttribute);
        for (Entry<TTarget, ? extends List<TSource>> entry : toInsert.entrySet()) {
            map.put(entry.getKey(), new DecisionLeaf<>(this.getTree(), entry.getValue()));
        }
    }

    @Override
    public DecisionRealNode<TSource> nextHop(TSource source) {
        TTarget key = this.getObjectAttribute(source);
        DecisionRealNode<TSource> value = map.get(key);
        if (value == null) {
            value = new DecisionLeaf<>(this.getTree());
            this.map.put(key, value);
        }
        return value;
    }

    @Override
    public void makeDirty() {
        for (DecisionRealNode<TSource> dn : this.map.values()) {
            dn.makeDirty();
        }
        super.makeDirty();
    }

    @Override
    protected DecisionLeaf<TSource> recalcMaximumLeaf() {
        double max = Double.NEGATIVE_INFINITY;
        double val;
        DecisionLeaf<TSource> leaf, maxLeaf = null;
        for (DecisionRealNode<TSource> dn : this.map.values()) {
            leaf = dn.getMaximumExpandLeaf();
            val = leaf.expandScore();
            if (val > max) {
                max = val;
                maxLeaf = leaf;
            }
        }
        return maxLeaf;
    }

    @Override
    public void replaceChild(DecisionRealNode<TSource> was, DecisionRealNode<TSource> now) {
        for (Entry<TTarget, DecisionRealNode<TSource>> entry : map.entrySet()) {
            if (entry.getValue() == was) {
                entry.setValue(now);
            }
        }
    }

    @Override
    public Iterable<DecisionRealNode<TSource>> getChildren() {
        return Collections.unmodifiableCollection(this.map.values());
    }

    @Override
    public String toString() {
        return this.getObjectAttribute().getName();
    }

}
