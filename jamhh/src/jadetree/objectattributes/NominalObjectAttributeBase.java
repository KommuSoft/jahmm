package jadetree.objectattributes;

import jadetree.DecisionNode;
import jadetree.DecisionTreeUtils;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import jutils.collections.CollectionUtils;
import jutlis.CollectionFactoryMethods;
import jutlis.FactoryMethod;
import jutlis.algebra.Function;
import jutlis.tuples.Holder;

/**
 *
 * @author kommusoft
 * @param <TSource>
 * @param <TTarget>
 */
public abstract class NominalObjectAttributeBase<TSource, TTarget> implements NominalObjectAttribute<TSource, TTarget> {

    @Override
    public double calculateScore(List<? extends TSource> source, Function<? super TSource, ? extends Object> target, Holder<Object> state) {
        final HashMap<TTarget, LinkedList<TSource>> classified = new HashMap<>();
        FactoryMethod<LinkedList<TSource>> fm = CollectionFactoryMethods.linkedListFactory();
        CollectionUtils.classify(classified, source, this, fm);
        state.setData(classified);
        return DecisionTreeUtils.calculateEntropyPartition(classified.values(), target);
    }

    @Override
    public DecisionNode<TSource> createDecisionNode(List<? extends TSource> source, Function<? super TSource, ? extends Object> target, Holder<Object> state) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
