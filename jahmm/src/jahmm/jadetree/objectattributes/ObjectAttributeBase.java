package jahmm.jadetree.objectattributes;

import jutlis.algebra.Function;
import jutlis.lists.ListArray;
import jutlis.tuples.Holder;

public abstract class ObjectAttributeBase<TSource, TTarget> implements ObjectAttribute<TSource, TTarget> {

    @Override
    public double calculateScore(Function<TSource, ? extends Object> function, Holder<Object> state, TSource... source) {
        return calculateScore(function, state, new ListArray<>(source));
    }

}
