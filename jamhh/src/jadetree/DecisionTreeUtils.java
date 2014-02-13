package jadetree;

import java.util.HashMap;
import jutils.collections.CollectionUtils;
import jutlis.algebra.Function;
import jutlis.tuples.Holder;
import jutlis.tuples.HolderBase;
import jutlis.tuples.Tuple2;
import jutlis.MathConstants;

/**
 *
 * @author kommusoft
 */
public class DecisionTreeUtils {

    public static <TSource, TTarget> double calculateRawEntropy(Iterable<? extends TSource> sources, final HashMap<TTarget, Integer> frequency, Function<? super TSource, ? extends TTarget> function, Holder<Integer> total) {
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

    public static <TSource, TTarget> double calculateEntropy(Iterable<? extends TSource> sources, final HashMap<TTarget, Integer> frequency, Function<? super TSource, ? extends TTarget> function, Holder<Integer> total) {
        Holder<Integer> subholder = total;
        if (subholder == null) {
            subholder = new HolderBase<>();
        }
        double rawentropy = calculateRawEntropy(sources, frequency, function, subholder);
        int ttl = subholder.getData();
        if (ttl > 0x00) {
            return (rawentropy / ttl + Math.log(ttl)) * MathConstants.INVLOG2;
        } else {
            return Double.NaN;
        }
    }

    public static <TSource, TTarget> double calculateEntropy(Iterable<? extends TSource> sources, Function<? super TSource, ? extends TTarget> function) {
        final HashMap<TTarget, Integer> frequency = new HashMap<>();
        return calculateEntropy(sources, frequency, function, null);
    }

    public static <TSource, TTarget> int calculateEntropyFlipIndex(Iterable<? extends TSource> sources, Function<? super TSource, ? extends TTarget> function, Tuple2<Integer, Double> total_entropy) {
        final HashMap<TTarget, Integer> rFreq = new HashMap<>();
        Holder<Integer> subholder = total_entropy;
        if (subholder == null) {
            subholder = new HolderBase<>();
        }
        double rRawS = calculateRawEntropy(sources, rFreq, function, subholder), lRawS = 0.0d;
        final int N = subholder.getData();
        final HashMap<TTarget, Integer> lFreq = new HashMap<>(rFreq.size());
        int lN = 0x00, rN = N, maxFlip = -0x01, newF;
        double maxS = Double.NEGATIVE_INFINITY, lS, rS, S;
        for (TSource s : sources) {
            lN++;
            if (lN < N) {
                TTarget target = function.evaluate(s);
                rN--;
                newF = CollectionUtils.incrementKey(lFreq, target);
                lRawS -= newF * Math.log(newF) - (newF - 0x01) * Math.log(newF - 0x01);
                newF = CollectionUtils.decrementKey(rFreq, target);
                rRawS -= newF * Math.log(newF) - (newF + 0x01) * Math.log(newF + 0x01);
                lS = lN * Math.log(lN) + lRawS;
                rS = rN * Math.log(rN) + rRawS;
                //Waring: Entropy is still multiplied with log2 * N
                S = lS + rS;
                if (S > maxS) {
                    maxS = S;
                    maxFlip = lN;
                }
            }

        }
        if (total_entropy != null) {
            total_entropy.setItem2(maxS / (MathConstants.LOG2 * N));
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
