package jahmm.jadetree;

import jahmm.jadetree.draw.DecisionNodeDotDrawer;
import jahmm.jadetree.objectattributes.NominalObjectAttribute;
import jahmm.jadetree.objectattributes.ObjectAttribute;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import jutils.iterators.SingleIterable;

/**
 *
 * @author kommusoft
 * @param <TSource> The source of the types to classify.
 */
public class Id3ClassificationTree<TSource> implements DecisionTree<TSource> {

    private static final Logger LOG = Logger.getLogger(Id3ClassificationTree.class.getName());

    private final ArrayList<ObjectAttribute<TSource, Object>> sourceAttributes = new ArrayList<>();
    private NominalObjectAttribute<TSource, Object> targetAttribute;
    private DecisionRealNode<TSource> root = new DecisionLeaf<>(this);

    @Override
    public void addSourceAttribute(ObjectAttribute<TSource, Object> sourceAttribute) {
        this.sourceAttributes.add(sourceAttribute);
        this.root.makeDirty();
    }

    @Override
    public void removeSourceAttribute(ObjectAttribute<TSource, Object> sourceAttribute) {
        this.sourceAttributes.remove(sourceAttribute);
        this.root.makeDirty();
    }

    @Override
    public void insert(TSource element) {
        this.root.insert(element);
    }

    @Override
    public void reduceMemory() {
        this.root.makeDirty();
    }

    @Override
    public List<ObjectAttribute<TSource, Object>> getSourceAttributes() {
        return Collections.unmodifiableList(this.sourceAttributes);
    }

    @Override
    public NominalObjectAttribute<TSource, Object> getTargetAttribute() {
        return this.targetAttribute;
    }

    @Override
    public void expand() {
        this.root.getMaximumLeaf().expand();
    }

    @Override
    public void reduce() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkTrade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void trade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void tradeExpand() {
        this.trade();
        this.expand();
    }

    @Override
    public DecisionTree<TSource> getTree() {
        return this;
    }

    @Override
    public Object classify(TSource element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object classifyInsert(TSource element) {
        Object clas = this.classify(element);
        this.insert(element);
        return clas;
        //TODO: increase performance
    }

    @Override
    public DecisionNode<TSource> getParent() {
        return this;
    }

    @Override
    public DecisionRealNode<TSource> getRoot() {
        return this.root;
    }

    @Override
    public void writeDraw(OutputStream os) throws IOException {
        new DecisionNodeDotDrawer<TSource>().write(this, os);
    }

    @Override
    public void writeDraw(Writer writer) throws IOException {
        new DecisionNodeDotDrawer<TSource>().write(this, writer);
    }

    @Override
    public String writeDraw() throws IOException {
        return new DecisionNodeDotDrawer<TSource>().write(this);
    }

    @Override
    public Iterable<DecisionNode<TSource>> getChildren() {
        return new SingleIterable<DecisionNode<TSource>>(this.root);
    }

    @Override
    public void replaceChild(DecisionRealNode<TSource> was, DecisionRealNode<TSource> now) {
        if (this.root == was) {
            this.root = now;
        }
    }

}
