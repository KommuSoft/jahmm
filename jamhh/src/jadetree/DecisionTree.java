package jadetree;

import jadetree.objectattributes.NominalObjectAttribute;
import jadetree.objectattributes.ObjectAttribute;

/**
 *
 * @author kommusoft
 * @param <TSource>
 */
public interface DecisionTree<TSource> {

    Iterable<ObjectAttribute<TSource, ?>> getSourceAttributes();

    void addSourceAttribute(ObjectAttribute<TSource, ?> sourceAttribute);

    void insert(TSource element);

    void expand();

    void reduce();

    boolean checkTrade();

    void trade();

    void tradeExpand();

    void reduceMemory();

    void removeSourceAttribute(ObjectAttribute<TSource, ?> sourceAttribute);

    NominalObjectAttribute<TSource, ? extends Object> getTargetAttribute();

}
