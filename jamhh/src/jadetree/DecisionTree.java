package jadetree;

import jadetree.objectattributes.NominalObjectAttribute;
import jadetree.objectattributes.ObjectAttribute;

/**
 *
 * @author kommusoft
 * @param <TSource>
 */
public interface DecisionTree<TSource> extends DecisionNode<TSource> {

    Iterable<ObjectAttribute<TSource, Object>> getSourceAttributes();

    void addSourceAttribute(ObjectAttribute<TSource, Object> sourceAttribute);

    void insert(TSource element);

    void expand();

    void reduce();

    boolean checkTrade();

    void trade();

    void tradeExpand();

    void reduceMemory();

    void removeSourceAttribute(ObjectAttribute<TSource, Object> sourceAttribute);

    NominalObjectAttribute<TSource, Object> getTargetAttribute();

    Object classify(TSource element);

    Object classifyInsert(TSource element);

}
