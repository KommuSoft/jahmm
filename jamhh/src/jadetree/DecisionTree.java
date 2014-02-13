package jadetree;

import objectattributes.ObjectAttribute;

/**
 *
 * @author kommusoft
 * @param <TSource>
 */
public interface DecisionTree<TSource> {

    Iterable<ObjectAttribute<? super TSource, ?>> getSourceAttributes();

}
