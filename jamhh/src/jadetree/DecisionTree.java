package jadetree;

import objectattributes.NominalObjectAttribute;
import objectattributes.ObjectAttribute;

/**
 *
 * @author kommusoft
 * @param <TSource>
 */
public interface DecisionTree<TSource> {

    Iterable<ObjectAttribute<? super TSource, ?>> getSourceAttributes();

    void addSourceAttribute(ObjectAttribute<? super TSource, ?> sourceAttribute);

    void insert(TSource element);

    void expand();

    void reduce();

    void reduceMemory();

    void removeSourceAttribute(ObjectAttribute<? super TSource, ?> sourceAttribute);

    NominalObjectAttribute<? super TSource, ?> getTargetAttribute();

}
