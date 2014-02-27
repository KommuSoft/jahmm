package jahmm.jadetree.draw;

import jahmm.jadetree.DecisionInode;
import jahmm.jadetree.DecisionNode;
import jahmm.jadetree.DecisionTree;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.logging.Logger;
import jutils.designpatterns.draw.CompositePatternDotDrawer;

/**
 *
 * @author kommusoft
 * @param <TSource> The type of elements in the decision node.
 */
public class DecisionNodeDotDrawer<TSource> extends CompositePatternDotDrawer<DecisionNode<TSource>, DecisionInode<TSource>> {

    private static final Logger LOG = Logger.getLogger(DecisionNodeDotDrawer.class.getName());

    public void write(DecisionTree<TSource> tree, OutputStream os) throws IOException {
        this.write(tree.getRoot(), os);
    }

    public void write(DecisionTree<TSource> tree, Writer writer) throws IOException {
        this.write(tree.getRoot(), writer);
    }

    public String write(DecisionTree<TSource> tree) throws IOException {
        return this.write(tree.getRoot());
    }

}
