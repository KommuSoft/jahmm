package jahmm.jadetree.abstracts;

import jahmm.jadetree.objectattributes.NominalObjectAttribute;
import jahmm.jadetree.objectattributes.ObjectAttribute;
import java.util.List;

/**
 *
 * @author kommusoft
 * @param <TSource>
 */
public interface DecisionTree<TSource> extends DecisionInode<TSource> {

    public abstract List<ObjectAttribute<TSource, Object>> getSourceAttributes();

    public abstract void addSourceAttribute(ObjectAttribute<TSource, Object> sourceAttribute);

    public abstract boolean checkTrade();

    public abstract void trade();

    public abstract void tradeExpand();

    public abstract void reduceMemory();

    public abstract DecisionRealNode<TSource> getRoot();

    public abstract void removeSourceAttribute(ObjectAttribute<TSource, Object> sourceAttribute);

    public abstract NominalObjectAttribute<TSource, Object> getTargetAttribute();

    public abstract Object classify(TSource element);

    public abstract Object classifyInsert(TSource element);

}
