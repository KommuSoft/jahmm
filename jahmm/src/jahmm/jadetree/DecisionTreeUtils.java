package jahmm.jadetree;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;
import jutils.MathUtils;
import jutils.collections.CollectionUtils;
import jutlis.algebra.Function;
import jutlis.algebra.functions.FunctionIdentity;
import jutlis.tuples.Holder;
import jutlis.tuples.HolderBase;
import jutlis.tuples.Tuple2;

/**
 *
 * @author kommusoft
 */
public class DecisionTreeUtils {

    private static final Logger LOG = Logger.getLogger(DecisionTreeUtils.class.getName());

    public static <TEntry> double calculateRawEntropy(Map<TEntry, Integer> counters, Holder<Integer> total) {
        double rawEntropy = 0.0d;
        int ttl = 0x00;
        for (Integer fi : counters.values()) {
            if (fi != null && fi > 0x00) {
                rawEntropy -= fi * Math.log(fi);
                ttl += fi;
            }
        }
        if (total != null) {
            total.setData(ttl);
        }
        return rawEntropy;
    }

    public static <TSource, TTarget> double calculateEntropy(final Map<TTarget, Integer> frequency, Holder<Integer> total) {
        Holder<Integer> subholder = total;
        if (subholder == null) {
            subholder = new HolderBase<>();
        }
        double rawentropy = calculateRawEntropy(frequency, subholder);
        int ttl = subholder.getData();
        if (ttl > 0x00) {
            return (rawentropy / ttl + Math.log(ttl)) * MathUtils.INVLOG2;
        } else {
            return 0.0d;
        }
    }

    public static <TSource, TTarget> double calculateRawEntropy(Iterable<? extends TSource> sources, final Map<TTarget, Integer> frequency, Function<TSource, TTarget> function, Holder<Integer> total) {
        for (TSource s : sources) {
            TTarget target = function.evaluate(s);
            CollectionUtils.incrementKey(frequency, target);
        }
        return calculateRawEntropy(frequency, total);
    }

    public static <TSource, TTarget> double calculateRawEntropy(Iterable<? extends TSource> sources, Function<TSource, TTarget> function) {
        return calculateRawEntropy(sources, function, null);
    }

    public static <TSource> double calculateRawEntropy(Iterable<? extends TSource> sources) {
        return calculateRawEntropy(sources, new FunctionIdentity<>());
    }

    public static <TSource> double calculateRawEntropy(Iterable<? extends TSource> sources, Holder<Integer> total) {
        return calculateRawEntropy(sources, new FunctionIdentity<>(), total);
    }

    public static <TSource, TTarget> double calculateRawEntropy(Iterable<? extends TSource> sources, Function<TSource, TTarget> function, Holder<Integer> total) {
        final HashMap<TTarget, Integer> frequency = new HashMap<>();
        return calculateRawEntropy(sources, frequency, function, total);
    }

    public static <TSource, TTarget> double calculateEntropy(Iterable<? extends TSource> sources, final Map<TTarget, Integer> frequency, Function<TSource, TTarget> function, Holder<Integer> total) {
        Holder<Integer> subholder = total;
        if (subholder == null) {
            subholder = new HolderBase<>();
        }
        double rawentropy = calculateRawEntropy(sources, frequency, function, subholder);
        int ttl = subholder.getData();
        if (ttl > 0x00) {
            return (rawentropy / ttl + Math.log(ttl)) * MathUtils.INVLOG2;
        } else {
            return 0.0d;
        }
    }

    public static <TSource, TTarget> double calculateEntropy(Iterable<TSource> sources, Function<TSource, TTarget> function) {
        final HashMap<TTarget, Integer> frequency = new HashMap<>();
        return calculateEntropy(sources, frequency, function, null);
    }

    public static double calculateEntropy2p(double p) {
        if (p <= 0.0d || p >= 1.0d || Double.isNaN(p)) {
            return 0.0d;
        } else {
            double pa = 1.0d - p;
            return -MathUtils.INVLOG2 * (p * Math.log(p) + pa * Math.log(pa));
        }
    }

    public static double calculateEntropy2p(double... ps) {
        double entropy = 0.0d;
        double rest = 1.0d;
        for (double p : ps) {
            if (p > 0.0d && p < 1.0d) {
                entropy -= p * Math.log(p);
            }
            rest -= p;
        }
        double p = rest;
        if (p > 0.0d && p < 1.0d) {
            entropy -= p * Math.log(p);
        }
        return MathUtils.INVLOG2 * entropy;

    }

    public static double calculateEntropy2pSplit(double pSplit, double p0, double p1) {
        return pSplit * DecisionTreeUtils.calculateEntropy2p(p0) + (1.0d - pSplit) * DecisionTreeUtils.calculateEntropy2p(p1);
    }

    public static double calculateInformationGain(double pSplit, double p0, double p1) {
        return DecisionTreeUtils.calculateEntropy2p(pSplit * p0 + (1.0d - pSplit) * p1) - pSplit * DecisionTreeUtils.calculateEntropy2p(p0) - (1.0d - pSplit) * DecisionTreeUtils.calculateEntropy2p(p1);
    }

    public static double calculateInformationGain(int total, int group1, int positive1, int positive2) {
        return calculateInformationGain((double) group1 / total, (double) positive1 / group1, (double) positive2 / (total - group1));
    }

    public static <TSource> double calculateEntropy(Iterable<TSource> sources) {
        return calculateEntropy(sources, new FunctionIdentity<TSource>());
    }

    public static <TSource, TTarget> int calculateEntropyFlipIndex(Iterable<TSource> sources, Function<TSource, TTarget> function, Tuple2<Integer, Double> total_entropy) {
        final HashMap<TTarget, Integer> rFreq = new HashMap<>();
        Holder<Integer> subholder = total_entropy;
        if (subholder == null) {
            subholder = new HolderBase<>();
        }
        double rRawS = calculateRawEntropy(sources, rFreq, function, subholder);
        return calculateEntropyFlipIndex(subholder, rFreq, sources, function, rRawS, total_entropy);
    }

    private static <TSource, TTarget> int calculateEntropyFlipIndex(Holder<Integer> subholder, final HashMap<TTarget, Integer> rFreq, Iterable<TSource> sources, Function<TSource, TTarget> function, double rRawSi, Tuple2<Integer, Double> total_entropy) {
        double lRawS = 0.0d, rRawS = rRawSi;
        final int N = subholder.getData();
        final HashMap<TTarget, Integer> lFreq = new HashMap<>(rFreq.size());
        int lN = 0x00, rN = N, maxFlip = -0x01, newF;
        double maxS = Double.POSITIVE_INFINITY, lS, rS, S;
        for (TSource s : sources) {
            lN++;
            if (lN < N) {
                TTarget target = function.evaluate(s);
                rN--;
                newF = CollectionUtils.incrementKey(lFreq, target);
                lRawS -= newF * Math.log(newF);
                if (newF > 0x01) {
                    lRawS += (newF - 0x01) * Math.log(newF - 0x01);
                }
                newF = CollectionUtils.decrementKey(rFreq, target);
                rRawS += (newF + 0x01) * Math.log(newF + 0x01);
                if (newF > 0x00) {
                    rRawS -= newF * Math.log(newF);
                }
                lS = lN * Math.log(lN) + lRawS;
                rS = rN * Math.log(rN) + rRawS;
                S = lS + rS;
                if (S < maxS) {
                    maxS = S;
                    maxFlip = lN;
                }
            }

        }
        if (total_entropy != null) {
            total_entropy.setItem2(maxS / (MathUtils.LOG2 * N));
        }
        return maxFlip;
    }

    public static <TSource, TTarget> int calculateInformationGainFlipIndex(Iterable<TSource> sources, Function<TSource, TTarget> function, Tuple2<Integer, Double> total_informationGain) {
        final HashMap<TTarget, Integer> rFreq = new HashMap<>();
        Holder<Integer> subholder = total_informationGain;
        if (subholder == null) {
            subholder = new HolderBase<>();
        }
        double rRawS = calculateRawEntropy(sources, rFreq, function, subholder);
        double rawS = rRawS;
        int index = calculateEntropyFlipIndex(subholder, rFreq, sources, function, rRawS, total_informationGain);
        if (total_informationGain != null) {
            int ttl = total_informationGain.getItem1();
            total_informationGain.setItem2((rawS / ttl + Math.log(ttl)) * MathUtils.INVLOG2 - total_informationGain.getItem2());
        }
        return index;
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

    public static <TSource, TTarget> double calculateInformationGainPartition(Iterable<? extends Iterable<? extends TSource>> sources, Function<TSource, TTarget> function) {
        final HashMap<TTarget, Integer> frequency = new HashMap<>();
        final HashMap<TTarget, Integer> totFrequency = new HashMap<>();
        Holder<Integer> ttl = new HolderBase<>();
        int total = 0;
        int subtotal;
        double entropy = 0.0d;
        double subentropy;
        for (Iterable<? extends TSource> subsource : sources) {
            subentropy = calculateEntropy(subsource, frequency, function, ttl);
            CollectionUtils.mergeMapWithAdd(totFrequency, frequency);
            frequency.clear();
            subtotal = ttl.getData();
            entropy += subentropy * subtotal;
            total += subtotal;
        }
        return calculateEntropy(totFrequency, null) - entropy / total;
    }

    public static <TSource, TTarget> double calculateInformationGainReduce(Iterable<? extends Iterable<? extends TSource>> sources, Function<TSource, TTarget> function) {
        return -calculateInformationGainPartition(sources, function);
    }

    private DecisionTreeUtils() {// $COVERAGE-IGNORE$
    }// $COVERAGE-IGNORE$

}
