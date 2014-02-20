package jahmm.jadetree;

import jahmm.jadetree.abstracts.DecisionInode;
import jahmm.jadetree.abstracts.DecisionRealNode;
import jahmm.jadetree.abstracts.DecisionTree;
import jahmm.jadetree.objectattributes.NominalObjectAttribute;
import jahmm.jadetree.objectattributes.ObjectAttribute;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import jutils.iterators.SingleIterable;

/**
 *
 * @author kommusoft
 * @param <TSource> The source of the types to classify.
 */
public class Id3ClassificationTree<TSource> extends DecisionInodeBase<TSource> implements DecisionTree<TSource> {

    private final ArrayList<ObjectAttribute<TSource, Object>> sourceAttributes = new ArrayList<>();
    private final NominalObjectAttribute<TSource, Object> targetAttribute;
    private DecisionRealNode<TSource> root;

    public Id3ClassificationTree(final NominalObjectAttribute<TSource, Object> targetAttribute) {
        super(null);
        this.targetAttribute = targetAttribute;
    }

    @Override
    public DecisionInode<TSource> getParent() {
        return this;
    }

    @Override
    public DecisionTree<TSource> getTree() {
        return this;
    }

    @Override
    public void reduce() {
        this.root.reduce();
    }

    @Override
    public void expand() {
        this.root.expand();
    }

    @Override
    public double expandScore() {
        return this.root.expandScore();
    }

    @Override
    public double reduceScore() {
        return this.root.reduceScore();
    }

    @Override
    public void insert(TSource source) {
        this.root.insert(source);
    }

    @Override
    public Iterable<DecisionRealNode<TSource>> getChildren() {
        return new SingleIterable<>(this.root);
    }

    @Override
    public DecisionRealNode<TSource> getRoot() {
        return this.root;
    }

    @Override
    public void replaceChild(DecisionRealNode<TSource> was, DecisionRealNode<TSource> now) {
        if (Objects.equals(this.root, was)) {
            this.root = now;
        }
    }

    @Override
    public List<ObjectAttribute<TSource, Object>> getSourceAttributes() {
        return Collections.unmodifiableList(this.getSourceAttributes());
    }

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
    public NominalObjectAttribute<TSource, Object> getTargetAttribute() {
        return this.targetAttribute;
    }

    @Override
    public boolean checkTrade() {
        return this.reduceScore() >= this.expandScore();
    }

    @Override
    public void trade() {
        if (this.checkTrade()) {
            this.reduce();
            this.expandScore();
        }
    }

    @Override
    public void tradeExpand() {
        this.trade();
        this.expand();
    }

    @Override
    public void reduceMemory() {
        this.root.makeDirty();
    }

    @Override
    public Object classify(TSource element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object classifyInsert(TSource element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
