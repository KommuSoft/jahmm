package jadetree;

import java.util.HashMap;
import jutils.collections.CollectionUtils;
import jutlis.algebra.Function;
import jutlis.tuples.Holder;
import jutlis.tuples.HolderBase;
import jutlis.tuples.Tuple2;

/**
 *
 * @author kommusoft
 */
public class DecisionTreeUtils {

    public static <TSource, TTarget> double calculateRawEntropy(Iterable<? extends TSource> sources, final HashMap<TTarget, Integer> frequency, Function<TSource, TTarget> function, Holder<Integer> total) {
        int ttl = 0;
        for (TSource s : sources) {
            TTarget target = function.evaluate(s);
            CollectionUtils.incrementKey(frequency, target);
            ttl++;
        }
        double entropy = 0.0;
        for (Integer fi : frequency.values()) {
            entropy -= fi * Math.log(fi);
        }
        if (total != null) {
            total.setData(ttl);
        }
        return entropy;
    }

    public static <TSource, TTarget> double calculateEntropy(Iterable<? extends TSource> sources, final HashMap<TTarget, Integer> frequency, Function<TSource, TTarget> function, Holder<Integer> total) {
        Holder<Integer> subholder = total;
        if (subholder == null) {
            subholder = new HolderBase<>();
        }
        double entropy = calculateRawEntropy(sources, frequency, function, total);
        int ttl = subholder.getData();
        return (entropy / ttl + Math.log(ttl)) / Math.log(2.0d);
    }

    public static <TSource, TTarget> int calculateEntropyFlipIndex(Iterable<? extends TSource> sources, Function<TSource, TTarget> function, Tuple2<Integer, Double> total_entropy) {
        final HashMap<TTarget, Integer> frequency = new HashMap<>();
        int ttl = 0x00, maxFlip = -0x01;
        double maxEntropy = 0.0d, rawEntropy = 0.0d;
        for (TSource s : sources) {
            TTarget target = function.evaluate(s);
            CollectionUtils.incrementKey(frequency, target);
            ttl++;
            //TODO: recalculate entropy
            if (rawEntropy > maxEntropy) {
                maxEntropy = rawEntropy;
                maxFlip = ttl - 0x01;
            }

        }
        return maxFlip;
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
