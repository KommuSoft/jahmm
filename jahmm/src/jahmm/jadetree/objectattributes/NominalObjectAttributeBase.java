package jahmm.jadetree.objectattributes;

import jahmm.jadetree.DecisionInode;
import jahmm.jadetree.DecisionRealNode;
import jahmm.jadetree.DecisionTreeUtils;
import jahmm.jadetree.EnumerableDecisionNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import jutils.CollectionFactoryMethods;
import jutils.FactoryMethod;
import jutils.collections.CollectionUtils;
import jutlis.algebra.Function;
import jutlis.tuples.Holder;

/**
 *
 * @author kommusoft
 * @param <TSource>
 * @param <TTarget>
 */
public abstract class NominalObjectAttributeBase<TSource, TTarget> extends ObjectAttributeBase<TSource, TTarget> implements NominalObjectAttribute<TSource, TTarget> {

    @Override
    public double calculateScore(Function<TSource, ? extends Object> function, Holder<Object> state, List<TSource> source) {
        final HashMap<TTarget, ArrayList<TSource>> classified = new HashMap<>();
        FactoryMethod<ArrayList<TSource>> fm = CollectionFactoryMethods.arrayListFactory();
        CollectionUtils.classify(classified, source, this, fm);
        if (state != null) {
            state.setData(classified);
        }
        return DecisionTreeUtils.calculateEntropyPartition(classified.values(), function);
    }

    @Override
    public DecisionRealNode<TSource> createDecisionNode(DecisionInode<TSource> parent, List<TSource> source, Function<TSource, ? extends Object> target, Holder<Object> state) {
        @SuppressWarnings("unchecked")
        HashMap<TTarget, LinkedList<TSource>> data = (HashMap<TTarget, LinkedList<TSource>>) state.getData();
        return new EnumerableDecisionNode<>(parent, this, data);
    }

}
