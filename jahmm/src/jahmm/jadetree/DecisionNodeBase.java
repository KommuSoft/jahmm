package jahmm.jadetree;

import java.util.Iterator;
import jutils.IdableBase;
import jutlis.lists.ListArray;

/**
 *
 * @author kommusoft
 * @param <TSource>
 */
public abstract class DecisionNodeBase<TSource> extends IdableBase implements DecisionNode<TSource> {

    private final DecisionInode<TSource> parent;

    protected DecisionNodeBase(final DecisionInode<TSource> parent) {
        this.parent = parent;
    }

    @Override
    public DecisionInode<TSource> getParent() {
        return this.parent;
    }

    @Override
    public DecisionTree<TSource> getTree() {
        if (this.parent != null) {
            return this.parent.getTree();
        } else {
            return null;
        }
    }

    @Override
    public void insert(Iterable<TSource> sources) {
        for (TSource source : sources) {
            this.insert(source);
        }
    }

    @Override
    public void insert(TSource... sources) {
        this.insert(new ListArray<>(sources));
    }

    @Override
    public Iterator<TSource> iterator() {
        return this.getStoredSources().iterator();
    }

}
