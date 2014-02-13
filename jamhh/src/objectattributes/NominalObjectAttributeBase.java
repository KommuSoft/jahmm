package objectattributes;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import jutlis.CollectionFactoryMethods;
import jutlis.FactoryMethod;
import jutlis.Holder;
import jutlis.HolderBase;
import utils.Utils;

/**
 *
 * @author kommusoft
 * @param <TSource>
 * @param <TTarget>
 */
public abstract class NominalObjectAttributeBase<TSource, TTarget> implements NominalObjectAttribute<TSource, TTarget> {

    public double calculateEntropy(Iterable<? extends TSource> sources) {
        final HashMap<TTarget, Integer> frequency = new HashMap<>();
        return calculateEntropy(sources, frequency, null);
    }

    @Override
    public double calculateScore(List<? extends TSource> source, Holder<Object> state) {
        final HashMap<TTarget, LinkedList<TSource>> classified = new HashMap<>();
        FactoryMethod<LinkedList<TSource>> fm = CollectionFactoryMethods.linkedListFactory();
        Utils.classify(classified, source, this, fm);
        state.setData(classified);
        return calculateEntropyPartition(classified.values());
    }

    @Override
    public void createDecisionNode(List<? extends TSource> source, Holder<Object> state) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
