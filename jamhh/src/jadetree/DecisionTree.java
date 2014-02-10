package jadetree;

import java.util.ArrayList;
import objectattributes.ObjectAttribute;

/**
 *
 * @author kommusoft
 * @param <TSource> The source of the types to classify.
 */
public class DecisionTree<TSource> {
    
    private final ArrayList<ObjectAttribute<? super TSource,?>> sourceattributes = new ArrayList<>();
    private final ArrayList<ObjectAttribute<? super TSource,?>> targetattributes = new ArrayList<>();
    
    public class DecisionTreeNode<TTarget> {
        
        ObjectAttribute<? super TSource,TTarget> decisionattribute;
        
    }
    
    public void insert (TSource element) {
    }
    
    private abstract class DecisionNode {
        
        public abstract boolean isLeaf ();
        
        public abstract DecisionNode nextHop (TSource source);
        
        public abstract double expandScore ();
        
        public abstract DecisionNode expand ();
        
    }
    
    private class DecisionLeaf extends DecisionNode {
        
        private final ArrayList<TSource> memory = new ArrayList<>();

        @Override
        public boolean isLeaf() {
            return true;
        }

        @Override
        public DecisionNode nextHop(TSource source) {
            return this;
        }

        @Override
        public double expandScore() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public DecisionNode expand() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
}