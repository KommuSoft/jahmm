package jahmm.jadetree.objectattributes;

import jahmm.jadetree.DecisionNode;
import jahmm.jadetree.DecisionNodeBase;
import jahmm.jadetree.DecisionTreeUtils;
import jahmm.jadetree.EnumerableDecisionNode;
import java.util.ArrayList;
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
    public double calculateScore(List<TSource> source, Function<TSource, Object> target, Holder<Object> state) {
        final HashMap<TTarget, ArrayList<TSource>> classified = new HashMap<>();
        FactoryMethod<ArrayList<TSource>> fm = CollectionFactoryMethods.arrayListFactory();
        CollectionUtils.classify(classified, source, this, fm);
        state.setData(classified);
        return DecisionTreeUtils.calculateEntropyPartition(classified.values(), target);
    }

    @Override
    public DecisionNodeBase<TSource> createDecisionNode(DecisionNode<TSource> parent, List<TSource> source, Function<TSource, Object> target, Holder<Object> state) {
        @SuppressWarnings("unchecked")
        HashMap<TTarget, LinkedList<TSource>> data = (HashMap<TTarget, LinkedList<TSource>>) state.getData();
        return new EnumerableDecisionNode<>(parent, this, data);
    }

}
