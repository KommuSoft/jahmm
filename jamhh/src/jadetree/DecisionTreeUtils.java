package jadetree;

import java.util.HashMap;
import jutlis.Function;
import jutlis.Holder;
import jutlis.HolderBase;
import utils.Utils;

/**
 *
 * @author kommusoft
 */
public class DecisionTreeUtils {

    public static <TSource, TTarget> double calculateEntropy(Iterable<? extends TSource> sources, final HashMap<TTarget, Integer> frequency, Function<TSource, TTarget> function, Holder<Integer> total) {
        final double log2 = 1.0 / Math.log(2.0);
        int ttl = 0;
        for (TSource s : sources) {
            TTarget target = function.evaluate(s);
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

    public static <TSource, TTarget> double calculateEntropyPartition(Iterable<? extends Iterable<? extends TSource>> sources, Function<TSource, TTarget> function) {
        final HashMap<TTarget, Integer> frequency = new HashMap<>();
        Holder<Integer> ttl = new HolderBase<>();
        int total = 0;
        int subtotal;
        double entropy = 0.0d;
        double subentropy;
        for (Iterable<? extends TSource> subsource : sources) {
            subentropy = calculateEntropy(subsource, frequency, function, ttl);
            frequency.clear();
            subtotal = ttl.getData();
            entropy += subentropy * subtotal;
            total += subtotal;
        }
        return entropy / total;
    }

}
