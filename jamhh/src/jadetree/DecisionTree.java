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
    }

    public void addTargetAttribute(ObjectAttribute<? super TSource, ?> targetAttribute) {
        this.targetAttributes.add(targetAttribute);
    }

    public void removeSourceAttribute(ObjectAttribute<? super TSource, ?> sourceAttribute) {
        this.sourceAttributes.remove(sourceAttribute);
    }

    public void removeTargetAttribute(ObjectAttribute<? super TSource, ?> targetAttribute) {
        this.targetAttributes.remove(targetAttribute);
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

    }

    private abstract class AttributeDecisionNode extends DecisionNode {

        private final ObjectAttribute<? super TSource, ?> objectAttribute;

        protected AttributeDecisionNode(ObjectAttribute<? super TSource, ?> objectAttribute) {
            this.objectAttribute = objectAttribute;
        }

        @Override
        public double expandScore() {
            return Double.NaN;
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

    }

    private class DecisionLeaf extends DecisionNode {

        private final ArrayList<TSource> memory = new ArrayList<>();

        @Override
        public boolean isLeaf() {
            return true;
        }

        @Override
        public double expandScore() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public DecisionNode expand() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void insert(TSource source) {
            memory.add(source);
        }

    }

}
