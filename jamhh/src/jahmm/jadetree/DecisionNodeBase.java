package jahmm.jadetree;

import jahmm.jadetree.draw.DecisionNodeDotDrawer;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 *
 * @author kommusoft
 * @param <TSource>
 */
public abstract class DecisionNodeBase<TSource> implements DecisionRealNode<TSource> {

    private final DecisionNode<TSource> parent;

    public DecisionNodeBase(final DecisionNode<TSource> parent) {
        this.parent = parent;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public DecisionRealNode<TSource> nextHop(TSource source) {
        return this;
    }

    public abstract double expandScore();

    @Override
    public void insert(TSource source) {
        this.nextHop(source).insert(source);
    }

    /**
     * @return the tree
     */
    @Override
    public DecisionTree<TSource> getTree() {
        return this.getParent().getTree();
    }

    /**
     * @return the parent
     */
    @Override
    public DecisionNode<TSource> getParent() {
        return parent;
    }

    @Override
    public void writeDraw(OutputStream os) throws IOException {
        new DecisionNodeDotDrawer<TSource>().write(parent, os);
    }

    @Override
    public void writeDraw(Writer writer) throws IOException {
        new DecisionNodeDotDrawer<TSource>().write(parent, writer);
    }

    @Override
    public String writeDraw() throws IOException {
        return new DecisionNodeDotDrawer<TSource>().write(parent);
    }

}
