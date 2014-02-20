package jahmm.jadetree;

/**
 *
 * @author kommusoft
 * @param <TSource> The type of elements stored in the decision tree.
 */
public class DecisionRealInodeBase<TSource> extends DecisionRealNodeBase<TSource> implements DecisionRealInode<TSource> {

    @Override
    protected DecisionLeaf<TSource> recalcMaximumExpandLeaf() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected DecisionRealInode<TSource> recalcMaximumReduceInode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterable<TSource> getStoredSources() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterable<Iterable<TSource>> getPartitionedStoredSources() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void reduceThis() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterable<DecisionRealNode<TSource>> getChildren() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void replaceChild(DecisionRealNode<TSource> was, DecisionRealNode<TSource> now) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
