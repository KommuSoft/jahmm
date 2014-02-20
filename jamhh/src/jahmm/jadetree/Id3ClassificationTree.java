package jahmm.jadetree;

import jahmm.jadetree.abstracts.DecisionTree;
import jahmm.jadetree.abstracts.DecisionNode;
import jahmm.jadetree.abstracts.DecisionRealNode;
import jahmm.jadetree.abstracts.DecisionInode;
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
import jutlis.IdableBase;

/**
 *
 * @author kommusoft
 * @param <TSource> The source of the types to classify.
 */
public class Id3ClassificationTree<TSource> extends IdableBase implements DecisionTree<TSource> {

    private static final Logger LOG = Logger.getLogger(Id3ClassificationTree.class.getName());

    private final ArrayList<ObjectAttribute<TSource, Object>> sourceAttributes = new ArrayList<>();
    private NominalObjectAttribute<TSource, Object> targetAttribute;
    private DecisionRealNode<TSource> root = new DecisionLeafImpl<>(null);

    @Override
    public void addSourceAttribute(ObjectAttribute<TSource, Object> sourceAttribute) {
        this.sourceAttributes.add(sourceAttribute);
        this.makeDirty();
    }

    @Override
    public void removeSourceAttribute(ObjectAttribute<TSource, Object> sourceAttribute) {
        this.sourceAttributes.remove(sourceAttribute);
        this.makeDirty();
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
        this.root.getMaximumExpandLeaf().expand();
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
    public void replaceChild(DecisionRealNode<TSource> was, DecisionRealNode<TSource> now) {
        if (this.root == was) {
            this.root = now;
        }
    }

    @Override
    public double expandScore() {
        return this.root.expandScore();
    }

    @Override
    public DecisionLeafImpl<TSource> getMaximumExpandLeaf() {
        return this.root.getMaximumExpandLeaf();
    }

    @Override
    public void makeDirty() {
        this.root.makeDirty();
    }

    @Override
    public Iterable<DecisionRealNode<TSource>> getChildren() {
        return new SingleIterable<>(this.root);
    }

    @Override
    public double reduceScore() {
        return this.root.reduceScore();
    }

    @Override
    public Iterable<TSource> getStoredSources() {
        return this.root.getStoredSources();
    }

    @Override
    public Iterable<Iterable<TSource>> getPartitionedStoredSources() {
        return new SingleIterable<>(this.getStoredSources());
    }

    @Override
    public boolean isLeaf() {
        return this.root.isLeaf();
    }

    @Override
    public DecisionRealNode<TSource> reduceThis() {
        return this.root.reduceThis();
    }

    @Override
    public DecisionRealNode<TSource> reduce() {
        return this.root.reduce();
    }

    @Override
    public DecisionRealNode<TSource> expand() {
        return this.root.expand();
    }

    @Override
    public DecisionRealNode<TSource> nextHop(TSource source) {
        return this.root;
    }

    @Override
    public DecisionLeafImpl<TSource> getMaximumExpandLeaf() {
        return this.root.getMaximumExpandLeaf();
    }

    @Override
    public DecisionInode<TSource> getMaximumReduceInode() {
        return this.root.getMaximumReduceInode();
    }

}
