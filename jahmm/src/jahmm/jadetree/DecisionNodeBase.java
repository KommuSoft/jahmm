package jahmm.jadetree;

import jutils.IdableBase;

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

}
