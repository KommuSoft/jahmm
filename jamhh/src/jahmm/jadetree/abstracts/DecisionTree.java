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

    public List<ObjectAttribute<TSource, Object>> getSourceAttributes();

    public void addSourceAttribute(ObjectAttribute<TSource, Object> sourceAttribute);

    public void insert(TSource element);

    public boolean checkTrade();

    public void trade();

    public void tradeExpand();

    public void reduceMemory();

    public DecisionNode<TSource> getRoot();

    public void removeSourceAttribute(ObjectAttribute<TSource, Object> sourceAttribute);

    public NominalObjectAttribute<TSource, Object> getTargetAttribute();

    public Object classify(TSource element);

    public Object classifyInsert(TSource element);

}
