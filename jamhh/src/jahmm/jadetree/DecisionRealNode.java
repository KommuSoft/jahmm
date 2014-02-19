package jahmm.jadetree;

/**
 *
 * @author kommusoft
 * @param <TSource> The type of elements in the tree.
 */
public interface DecisionRealNode<TSource> extends DecisionNode<TSource> {

    public boolean isLeaf();

    public void makeDirty();

    public DecisionRealNode<TSource> nextHop(TSource source);

    public abstract DecisionLeaf<TSource> getMaximumLeaf();

    public abstract void insert(TSource source);

}
