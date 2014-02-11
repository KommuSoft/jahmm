package objectattributes;

import java.util.HashMap;
import java.util.List;
import utils.Holder;
import utils.HolderBase;
import utils.Utils;

/**
 *
 * @author kommusoft
 * @param <TSource>
 * @param <TTarget>
 */
public abstract class NominalObjectAttributeBase<TSource, TTarget> implements NominalObjectAttribute<TSource, TTarget> {

    @Override
    public double calculateEntropy(Iterable<? extends TSource> sources) {
        final HashMap<TTarget, Integer> frequency = new HashMap<>();
        return calculateEntropy(sources, frequency, null);
    }

    protected double calculateEntropy(Iterable<? extends TSource> sources, final HashMap<TTarget, Integer> frequency, Holder<Integer> total) {
        final double log2 = 1.0 / Math.log(2.0);
        int ttl = 0;
        for (TSource s : sources) {
            TTarget target = this.evaluate(s);
            Utils.incrementKey(frequency, target);
            ttl++;
        }
        double entropy = 0.0;
        for (Integer fi : frequency.values()) {
            double p = (double) fi / ttl;
            entropy -= p * log2 * Math.log(p);
        }
        if (total != null) {
            total.setData(ttl);
        }
        return entropy;
    }

    @Override
    public double calculateEntropyPartition(Iterable<? extends Iterable<? extends TSource>> sources) {
        final HashMap<TTarget, Integer> frequency = new HashMap<>();
        Holder<Integer> ttl = new HolderBase<>();
        int total = 0;
        int subtotal;
        double entropy = 0.0;
        double subentropy = 0.0;
        for (Iterable<? extends TSource> subsource : sources) {
            subentropy = calculateEntropy(subsource, frequency, ttl);
            frequency.clear();
            subtotal = ttl.getData();
            entropy += subentropy * subtotal;
            total += subtotal;
        }
        return entropy / total;
    }

    @Override
    public double calculateScore(List<? extends TSource> source) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createDecisionNode(List<? extends TSource> source) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
