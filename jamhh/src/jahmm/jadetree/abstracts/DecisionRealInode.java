package jahmm.jadetree.abstracts;

/**
 *
 * @author kommusoft
 */
public interface DecisionRealInode<TSource> extends DecisionRealNode<TSource>, DecisionInode<TSource> {
    
    public abstract void reduceThis ();
    
}
