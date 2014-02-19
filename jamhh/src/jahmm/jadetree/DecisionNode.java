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

    /**
     * @return the tree
     */
    public DecisionTree<TSource> getTree();

    public void writeDraw(OutputStream os) throws IOException;

    public void writeDraw(Writer writer) throws IOException;

    public String writeDraw() throws IOException;

}
