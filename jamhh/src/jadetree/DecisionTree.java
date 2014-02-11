package jadetree;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import objectattributes.ObjectAttribute;

/**
 *
 * @author kommusoft
 * @param <TSource> The source of the types to classify.
 */
public class DecisionTree<TSource> {

    private final ArrayList<ObjectAttribute<? super TSource, ?>> sourceAttributes = new ArrayList<>();
    private final ArrayList<ObjectAttribute<? super TSource, ?>> targetAttributes = new ArrayList<>();
    private DecisionNode root = new DecisionLeaf();

    public void addSourceAttribute(ObjectAttribute<? super TSource, ?> sourceAttribute) {
        this.sourceAttributes.add(sourceAttribute);
        this.root.makeDirty();
    }

    public void addTargetAttribute(ObjectAttribute<? super TSource, ?> targetAttribute) {
        this.targetAttributes.add(targetAttribute);
        this.root.makeDirty();
    }

    public void removeSourceAttribute(ObjectAttribute<? super TSource, ?> sourceAttribute) {
        this.sourceAttributes.remove(sourceAttribute);
        this.root.makeDirty();
    }

    public void removeTargetAttribute(ObjectAttribute<? super TSource, ?> targetAttribute) {
        this.targetAttributes.remove(targetAttribute);
        this.root.makeDirty();
    }

    public void insert(TSource element) throws IllegalAccessException, InvocationTargetException {
        this.root.insert(element);
    }

    public class DecisionTreeNode<TTarget> {

        ObjectAttribute<? super TSource, TTarget> decisionattribute;

    }

    private abstract class DecisionNode {

        public boolean isLeaf() {
            return false;
        }

        public DecisionNode nextHop(TSource source) throws IllegalAccessException, InvocationTargetException {
            return this;
        }

        public abstract double expandScore();

        public abstract DecisionNode expand();

        public void insert(TSource source) throws IllegalAccessException, InvocationTargetException {
            this.nextHop(source).insert(source);
        }

        public abstract void makeDirty();

        public abstract DecisionLeaf getMaximumLeaf();

    }

    private abstract class AttributeDecisionNode extends DecisionNode {

        private final ObjectAttribute<? super TSource, ?> objectAttribute;
        private DecisionLeaf maximumLeaf = null;

        protected AttributeDecisionNode(ObjectAttribute<? super TSource, ?> objectAttribute) {
            this.objectAttribute = objectAttribute;
        }

        @Override
        public double expandScore() {
            return this.getMaximumLeaf().expandScore();
        }

        @Override
        public DecisionLeaf getMaximumLeaf() {
            if (this.maximumLeaf == null) {
                this.maximumLeaf = this.recalcMaximumLeaf();
            }
            return this.maximumLeaf;
        }

        /**
         * @return the objectAttribute
         */
        protected ObjectAttribute<? super TSource, ?> getObjectAttribute() {
            return objectAttribute;
        }

        protected Object getObjectAttribute(TSource source) throws IllegalAccessException, InvocationTargetException {
            return this.objectAttribute.getAttribute(source);
        }

        @Override
        public void makeDirty() {
            this.maximumLeaf = null;
        }

        protected abstract DecisionLeaf recalcMaximumLeaf();

    }

    private class EnumerableDecisionNode extends AttributeDecisionNode {

        private final HashMap<Object, DecisionNode> map = new HashMap<>();

        protected EnumerableDecisionNode(ObjectAttribute<? super TSource, ?> objectAttribute) {
            super(objectAttribute);
        }

        @Override
        public DecisionNode nextHop(TSource source) throws IllegalAccessException, InvocationTargetException {
            Object key = this.getObjectAttribute(source);
            DecisionNode value = map.get(key);
            if (value == null) {
                value = new DecisionLeaf();
                this.map.put(key, value);
            }
            return value;
        }

        @Override
        public DecisionNode expand() {
            throw new UnsupportedOperationException("Node already expanded.");
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

    private class DecisionLeaf extends DecisionNode {

        private final ArrayList<TSource> memory = new ArrayList<>();
        private double score = Double.NaN;
        private int splitIndex = 0x00;

        public boolean isDirty() {
            return Double.isNaN(this.score);
        }

        @Override
        public void makeDirty() {
            this.score = Double.NaN;
        }

        @Override
        public boolean isLeaf() {
            return true;
        }

        @Override
        public double expandScore() {
            if (this.isDirty()) {
                this.calculateScore();
            }
            return this.score;
        }

        @Override
        public DecisionNode expand() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void insert(TSource source) {
            this.makeDirty();
            memory.add(source);
        }

        private void calculateScore() {

        }

        @Override
        public DecisionLeaf getMaximumLeaf() {
            return this;
        }

    }

}
