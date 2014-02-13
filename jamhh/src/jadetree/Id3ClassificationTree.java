package jadetree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;
import objectattributes.NominalObjectAttribute;
import objectattributes.ObjectAttribute;
import jutlis.HolderBase;
import jutlis.Predicate;

/**
 *
 * @author kommusoft
 * @param <TSource> The source of the types to classify.
 */
public class Id3ClassificationTree<TSource> implements DecisionTree<TSource> {

    private static final Logger LOG = Logger.getLogger(Id3ClassificationTree.class.getName());

    private final ArrayList<ObjectAttribute<? super TSource, ?>> sourceAttributes = new ArrayList<>();
    private NominalObjectAttribute<? super TSource, ?> targetAttribute;
    private DecisionNode<TSource> root = new DecisionLeaf<>(this);

    @Override
    public void addSourceAttribute(ObjectAttribute<? super TSource, ?> sourceAttribute) {
        this.sourceAttributes.add(sourceAttribute);
        this.root.makeDirty();
    }

    @Override
    public void removeSourceAttribute(ObjectAttribute<? super TSource, ?> sourceAttribute) {
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
    public Iterable<ObjectAttribute<? super TSource, ?>> getSourceAttributes() {
        return this.sourceAttributes;
    }

}
