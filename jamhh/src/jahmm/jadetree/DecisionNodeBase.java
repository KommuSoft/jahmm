package jahmm.jadetree;

import jahmm.jadetree.abstracts.DecisionInode;
import jahmm.jadetree.abstracts.DecisionNode;
import jahmm.jadetree.abstracts.DecisionTree;
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
        return this.parent.getTree();
    }

}
