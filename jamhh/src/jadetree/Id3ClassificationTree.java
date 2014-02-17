package jadetree;

import jadetree.objectattributes.NominalObjectAttribute;
import jadetree.objectattributes.ObjectAttribute;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;

/**
 *
 * @author kommusoft
 * @param <TSource> The source of the types to classify.
 */
public class Id3ClassificationTree<TSource> implements DecisionTree<TSource> {

    private static final Logger LOG = Logger.getLogger(Id3ClassificationTree.class.getName());

    private final ArrayList<ObjectAttribute<TSource, Object>> sourceAttributes = new ArrayList<>();
    private NominalObjectAttribute<TSource, Object> targetAttribute;
    private DecisionNodeBase<TSource> root = new DecisionLeaf<>(this);

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
    public Iterable<ObjectAttribute<TSource, Object>> getSourceAttributes() {
        return Collections.unmodifiableList(this.sourceAttributes);
    }

    @Override
    public NominalObjectAttribute<TSource, Object> getTargetAttribute() {
        return this.targetAttribute;
    }

    @Override
    public void expand() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

}
