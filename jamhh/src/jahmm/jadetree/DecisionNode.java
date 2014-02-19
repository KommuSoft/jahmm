package jahmm.jadetree;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import jutils.designpatterns.CompositeComponent;

/**
 *
 * @author kommusoft
 * @param <TSource> The type of objects classified and stored in the tree.
 */
public interface DecisionNode<TSource> extends CompositeComponent<DecisionNode<TSource>> {

    public abstract double expandScore();

    public abstract double reduceScore();

    public abstract Iterable<TSource> getStoredSources();

    public abstract Iterable<Iterable<TSource>> getPartitionedStoredSources();

    /**
     * @return the tree
     */
    public abstract DecisionTree<TSource> getTree();

    public abstract void writeDraw(OutputStream os) throws IOException;

    public abstract void writeDraw(Writer writer) throws IOException;

    public abstract String writeDraw() throws IOException;

    public abstract void replaceChild(DecisionRealNode<TSource> was, DecisionRealNode<TSource> now);

}
