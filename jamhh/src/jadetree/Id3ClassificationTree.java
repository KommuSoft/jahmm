package jadetree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;
import objectattributes.NominalObjectAttribute;
import objectattributes.ObjectAttribute;
import utils.HolderBase;
import utils.Predicate;

/**
 *
 * @author kommusoft
 * @param <TSource> The source of the types to classify.
 */
public class Id3ClassificationTree<TSource> implements DecisionTree<TSource> {

    private static final Logger LOG = Logger.getLogger(Id3ClassificationTree.class.getName());

    private final ArrayList<ObjectAttribute<? super TSource, ?>> sourceAttributes = new ArrayList<>();
    NominalObjectAttribute<? super TSource, ?> targetAttribute;
    private DecisionNode root = new DecisionLeaf(this);

    public void addSourceAttribute(ObjectAttribute<? super TSource, ?> sourceAttribute) {
        this.sourceAttributes.add(sourceAttribute);
        this.root.makeDirty();
    }

    public void removeSourceAttribute(ObjectAttribute<? super TSource, ?> sourceAttribute) {
        this.sourceAttributes.remove(sourceAttribute);
        this.root.makeDirty();
    }

    public void insert(TSource element) {
        this.root.insert(element);
    }

    public void reduceMemory() {
        this.root.makeDirty();
    }

    public class DecisionTreeNode<TTarget> {

    }

    public abstract class AttributeDecisionNode extends DecisionInode {

        final ObjectAttribute<? super TSource, ?> objectAttribute;

        protected AttributeDecisionNode(ObjectAttribute<? super TSource, ?> objectAttribute, Id3ClassificationTree<TSource> tree) {
            super(tree);
            this.objectAttribute = objectAttribute;
        }

        @Override
        public double expandScore() {
            return this.getMaximumLeaf().expandScore();
        }

        /**
         * @return the objectAttribute
         */
        protected ObjectAttribute<? super TSource, ?> getObjectAttribute() {
            return objectAttribute;
        }

        protected Object getObjectAttribute(TSource source) {
            return this.objectAttribute.evaluate(source);
        }

    }

    public class EnumerableDecisionNode extends AttributeDecisionNode {

        final HashMap<Object, DecisionNode> map = new HashMap<>();

        protected EnumerableDecisionNode(ObjectAttribute<? super TSource, ?> objectAttribute, Id3ClassificationTree<TSource> tree) {
            super(objectAttribute, tree);
        }

        @Override
        public DecisionNode nextHop(TSource source) {
            Object key = this.getObjectAttribute(source);
            DecisionNode value = map.get(key);
            if (value == null) {
                value = new DecisionLeaf(jadetree.Id3ClassificationTree.this);
                this.map.put(key, value);
            }
            return value;
        }

        @Override
        public void makeDirty() {
            for (DecisionNode dn : this.map.values()) {
                dn.makeDirty();
            }
            super.makeDirty();
        }

        @Override
        protected DecisionLeaf recalcMaximumLeaf() {
            double max = Double.NEGATIVE_INFINITY, val;
            DecisionLeaf leaf;
            DecisionLeaf leaf, maxLeaf = null;
            for (DecisionNode dn : this.map.values()) {
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

}
