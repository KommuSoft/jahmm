package jadetree;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;
import objectattributes.NominalObjectAttribute;
import objectattributes.ObjectAttribute;

/**
 *
 * @author kommusoft
 * @param <TSource> The source of the types to classify.
 */
public class ClassificationTree<TSource> {

    private static final Logger LOG = Logger.getLogger(ClassificationTree.class.getName());

    private final ArrayList<ObjectAttribute<? super TSource, ?>> sourceAttributes = new ArrayList<>();
    NominalObjectAttribute<? super TSource, ?> targetAttribute;
    private DecisionNode root = new DecisionLeaf();

    public void addSourceAttribute(ObjectAttribute<? super TSource, ?> sourceAttribute) {
        this.sourceAttributes.add(sourceAttribute);
        this.root.makeDirty();
    }

    public void removeSourceAttribute(ObjectAttribute<? super TSource, ?> sourceAttribute) {
        this.sourceAttributes.remove(sourceAttribute);
        this.root.makeDirty();
    }

    public void insert(TSource element) throws IllegalAccessException, InvocationTargetException {
        this.root.insert(element);
    }

    public class DecisionTreeNode<TTarget> {

        ObjectAttribute<? super TSource, TTarget> decisionattribute;

    }

    public abstract class DecisionNode {

        public boolean isLeaf() {
            return false;
        }

        public DecisionNode nextHop(TSource source) throws IllegalAccessException, InvocationTargetException {
            return this;
        }

        public abstract double expandScore() throws IllegalAccessException, InvocationTargetException;

        public void insert(TSource source) throws IllegalAccessException, InvocationTargetException {
            this.nextHop(source).insert(source);
        }

        public abstract void makeDirty();

        public abstract DecisionLeaf getMaximumLeaf() throws IllegalAccessException, InvocationTargetException;

    }

    public abstract class AttributeDecisionNode extends DecisionNode {

        private final ObjectAttribute<? super TSource, ?> objectAttribute;
        private DecisionLeaf maximumLeaf = null;

        protected AttributeDecisionNode(ObjectAttribute<? super TSource, ?> objectAttribute) {
            this.objectAttribute = objectAttribute;
        }

        @Override
        public double expandScore() throws IllegalAccessException, InvocationTargetException {
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

        protected abstract DecisionLeaf recalcMaximumLeaf() throws IllegalAccessException, InvocationTargetException;

    }

    public class EnumerableDecisionNode extends AttributeDecisionNode {

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
        public void makeDirty() {
            for (DecisionNode dn : this.map.values()) {
                dn.makeDirty();
            }
            super.makeDirty();
        }

        @Override
        protected DecisionLeaf recalcMaximumLeaf() throws IllegalAccessException, InvocationTargetException {
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

    public class DecisionLeaf extends DecisionNode {

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
        public double expandScore() throws IllegalAccessException, InvocationTargetException {
            if (this.isDirty()) {
                this.score = this.calculateScore();
            }
            return this.score;
        }

        public DecisionNode expand() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void insert(TSource source) {
            this.makeDirty();
            memory.add(source);
        }

        private double calculateScore() throws IllegalAccessException, InvocationTargetException {
            double maxScore = Double.NEGATIVE_INFINITY;
            int maxIndex = -0x01, i = 0x00;
            for (ObjectAttribute<? super TSource, ?> oa : ClassificationTree.this.sourceAttributes) {
                double sc = oa.calculateScore(this.memory);
                if (sc > maxScore) {
                    maxScore = sc;
                    maxIndex = i;
                }
                i++;
            }
            this.splitIndex = maxIndex;
            return maxScore;
        }

        @Override
        public DecisionLeaf getMaximumLeaf() {
            return this;
        }

    }

}
