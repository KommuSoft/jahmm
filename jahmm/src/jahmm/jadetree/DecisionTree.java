package jahmm.jadetree;

import jahmm.jadetree.objectattributes.NominalObjectAttribute;
import jahmm.jadetree.objectattributes.ObjectAttribute;
import java.util.List;

/**
 *
 * @author kommusoft
 * @param <TSource>
 */
public interface DecisionTree<TSource> extends DecisionInode<TSource> {

    public abstract List<ObjectAttribute<TSource, ? extends Object>> getSourceAttributes();

    public abstract void addSourceAttribute(ObjectAttribute<TSource, ? extends Object> sourceAttribute);
    
    public abstract void addSourceAttribute(Iterable<ObjectAttribute<TSource, ? extends Object>> sourceAttributes);

    public abstract boolean checkTrade();

    public abstract void trade();

    public abstract void tradeExpand();

    public abstract void reduceMemory();

    public abstract DecisionRealNode<TSource> getRoot();

    public abstract void removeSourceAttribute(ObjectAttribute<TSource, ? extends Object> sourceAttribute);
    
    public abstract void removeSourceAttribute(Iterable<ObjectAttribute<TSource, ? extends Object>> sourceAttributes);

    public abstract NominalObjectAttribute<TSource, ? extends Object> getTargetAttribute();

    public abstract Object classify(TSource element);

    public abstract Object classifyInsert(TSource element);

}
