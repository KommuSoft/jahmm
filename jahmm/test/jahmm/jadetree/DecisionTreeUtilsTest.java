package jahmm.jadetree;

import jahmm.jadetree.foo.FooIntDouble;
import jahmm.jadetree.foo.Test2B1T;
import jahmm.jadetree.foo.TrisEnum;
import jahmm.jadetree.objectattributes.ObjectAttribute;
import jahmm.jadetree.objectattributes.ObjectAttributeInspector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.logging.Logger;
import jutils.MathUtils;
import jutils.collections.CollectionUtils;
import jutils.iterators.ListGenericIterable;
import jutils.probability.ProbabilityUtils;
import jutils.testing.AssertExtensions;
import static jutils.testing.AssertExtensions.assertEquals;
import jutlis.algebra.Function;
import jutlis.lists.ListArray;
import jutlis.tuples.HolderBase;
import jutlis.tuples.Tuple2;
import jutlis.tuples.Tuple2Base;
import org.junit.Assert;
import org.junit.Test;
import utils.TestParameters;

/**
 *
 * @author kommusoft
 */
public class DecisionTreeUtilsTest {

    private static final Logger LOG = Logger.getLogger(DecisionTreeUtilsTest.class.getName());

    public DecisionTreeUtilsTest() {
    }

    @Test
    public void testCalculateEntropy00() {
        double expResult;
        double result;
        expResult = 0.0d;
        result = DecisionTreeUtils.calculateEntropy(new ListGenericIterable<>(new Foo(0x00)));
        assertEquals(expResult, result);
        expResult = 1.0d;
        result = DecisionTreeUtils.calculateEntropy(new ListGenericIterable<>(new Foo(0x00), new Foo(0x01)));
        assertEquals(expResult, result);
        expResult = 2.0d;
        result = DecisionTreeUtils.calculateEntropy(new ListGenericIterable<>(new Foo(0x00), new Foo(0x01), new Foo(0x02), new Foo(0x03)));
        assertEquals(expResult, result);
        expResult = -0.75d * MathUtils.log2(0.75d) - 0.25d * MathUtils.log2(0.25d);
        result = DecisionTreeUtils.calculateEntropy(new ListGenericIterable<>(new Foo(0x01), new Foo(0x01), new Foo(0x01), new Foo(0x03)));
        assertEquals(expResult, result);
        result = DecisionTreeUtils.calculateEntropy(new ListGenericIterable<>(new Foo(0x01), new Foo(0x01), new Foo(0x03), new Foo(0x01)));
        assertEquals(expResult, result);
        result = DecisionTreeUtils.calculateEntropy(new ListGenericIterable<>(new Foo(0x01), new Foo(0x03), new Foo(0x01), new Foo(0x01)));
        assertEquals(expResult, result);
        result = DecisionTreeUtils.calculateEntropy(new ListGenericIterable<>(new Foo(0x03), new Foo(0x01), new Foo(0x01), new Foo(0x01)));
        assertEquals(expResult, result);
        expResult = -0.5d * MathUtils.log2(0.5d) - 0.5d * MathUtils.log2(0.25d);
        result = DecisionTreeUtils.calculateEntropy(new ListGenericIterable<>(new Foo(0x03), new Foo(0x03), new Foo(0x02), new Foo(0x01)));
        assertEquals(expResult, result);
    }

    @Test
    public void testCalculateEntropy01() {
        ObjectAttribute<Test2B1T, ? extends Object> target = ObjectAttributeInspector.inspect(Test2B1T.class, "bool1");
        for (int t = 0x00; t < TestParameters.NUMBER_OF_TESTS; t++) {
            double p = ProbabilityUtils.nextDouble();
            int N = ProbabilityUtils.nextInt(TestParameters.TEST_SIZE);
            ArrayList<Test2B1T> data = new ArrayList<>(N);
            int n = 0x00;
            for (int i = 0x00; i < N; i++) {
                Test2B1T t2i = new Test2B1T(p);
                data.add(t2i);
                if (t2i.isBool1()) {
                    n++;
                }
            }
            double expected = DecisionTreeUtils.calculateEntropy2p((double) n / N);
            double result = DecisionTreeUtils.calculateEntropy(data, target);
            String msg = data.toString();
            AssertExtensions.assertEquals(expected, result);
        }
    }

    @Test
    public void testCalculateEntropy02() {
        ObjectAttribute<Test2B1T, ? extends Object> target = ObjectAttributeInspector.inspect(Test2B1T.class, "tris");
        TrisEnum[] tev = TrisEnum.values();
        int[] counters = new int[tev.length];
        double[] probs = new double[tev.length];
        for (int t = 0x00; t < TestParameters.NUMBER_OF_TESTS; t++) {
            int N = ProbabilityUtils.nextInt(TestParameters.TEST_SIZE);
            ArrayList<Test2B1T> data = new ArrayList<>(N);
            Arrays.fill(counters, 0x00);
            int n = 0x00;
            for (int i = 0x00; i < N; i++) {
                int j = ProbabilityUtils.nextInt(tev.length);
                counters[j]++;
                Test2B1T t2i = new Test2B1T(tev[j]);
                data.add(t2i);
            }
            for (int i = 0x00; i < tev.length; i++) {
                probs[i] = (double) counters[i] / N;
            }
            double expected = DecisionTreeUtils.calculateEntropy2p(probs);
            double result = DecisionTreeUtils.calculateEntropy(data, target);
            AssertExtensions.assertEquals(expected, result);
            expected = 0.0d;
        }
    }

    @Test
    public void testCalculateEntropy03() {
        for (int t = 0x00; t < TestParameters.NUMBER_OF_TESTS; t++) {
            int M = 0x01 + ProbabilityUtils.nextInt(TestParameters.NUMBER_OF_CATEGORIES - 0x01);
            int[] counters = new int[M];
            double[] probs = new double[M];
            int N = ProbabilityUtils.nextInt(TestParameters.TEST_SIZE);
            ArrayList<Integer> data = new ArrayList<>(N);
            for (int i = 0x00; i < N; i++) {
                int j = ProbabilityUtils.nextInt(M);
                counters[j]++;
                data.add(j);
            }
            for (int i = 0x00; i < M; i++) {
                probs[i] = (double) counters[i] / N;
            }
            double expected = DecisionTreeUtils.calculateEntropy2p(probs);
            double result = DecisionTreeUtils.calculateEntropy(data);
            AssertExtensions.assertEquals(expected, result);
            if(N != 0x00) {
                expected = N*(MathUtils.LOG2*DecisionTreeUtils.calculateEntropy2p(probs)-Math.log(N));
            }
            result = DecisionTreeUtils.calculateRawEntropy(data);
            AssertExtensions.assertEquals(expected, result);
            HolderBase<Integer> hb = new HolderBase<>();
            result = DecisionTreeUtils.calculateRawEntropy(data,hb);
            AssertExtensions.assertEquals(expected, result);
            AssertExtensions.assertEquals(N, hb.getData());
        }
    }

    @Test
    public void testCalculateEntropy04() {
        for (int t = 0x00; t < TestParameters.NUMBER_OF_TESTS; t++) {
            int M = 0x01 + ProbabilityUtils.nextInt(TestParameters.NUMBER_OF_CATEGORIES - 0x01);
            int[] counters = new int[M];
            double[] probs = new double[M];
            int N = ProbabilityUtils.nextInt(TestParameters.TEST_SIZE);
            HashMap<Integer, Integer> data = new HashMap<>(M);
            for (int i = 0x00; i < M; i++) {
                data.put(i, 0x00);
            }
            for (int i = 0x00; i < N; i++) {
                int j = ProbabilityUtils.nextInt(M);
                counters[j]++;
                data.put(j, data.get(j) + 0x01);
            }
            for (int i = 0x00; i < M; i++) {
                probs[i] = (double) counters[i] / N;
            }
            double expected = DecisionTreeUtils.calculateEntropy2p(probs);
            double result = DecisionTreeUtils.calculateEntropy(data, null);
            AssertExtensions.assertEquals(expected, result);
            HolderBase<Integer> hb = new HolderBase<>();
            result = DecisionTreeUtils.calculateEntropy(data, hb);
            AssertExtensions.assertEquals(expected, result);
            AssertExtensions.assertEquals(N, hb.getData());
        }
    }

    @Test
    public void testCalculateEntropy05() {
        for (int t = 0x00; t < TestParameters.NUMBER_OF_TESTS; t++) {
            int M = 0x01 + ProbabilityUtils.nextInt(TestParameters.NUMBER_OF_CATEGORIES - 0x01);
            int[] counters = new int[M];
            double[] probs = new double[M];
            int N = ProbabilityUtils.nextInt(TestParameters.TEST_SIZE);
            HashMap<Integer, Integer> data = new HashMap<>(M);
            for (int i = 0x00; i < M; i++) {
                data.put(i, 0x00);
            }
            data.put(M, null);
            for (int i = 0x00; i < N; i++) {
                int j = ProbabilityUtils.nextInt(M);
                counters[j]++;
                data.put(j, data.get(j) + 0x01);
            }
            for (int i = 0x00; i < M; i++) {
                probs[i] = (double) counters[i] / N;
            }
            double expected = 0.0d;
            if(N != 0x00) {
                expected = N*(MathUtils.LOG2*DecisionTreeUtils.calculateEntropy2p(probs)-Math.log(N));
            }
            double result = DecisionTreeUtils.calculateRawEntropy(data, null);
            AssertExtensions.assertEquals(expected, result);
        }
    }
    
    @Test
    public void testCalculateEntropy2p () {
        for(int t = 0x03; t < TestParameters.NUMBER_OF_TESTS; t++) {
            double p = 1.0d/t;
            double[] pis = new double[t-0x01];
            for(int i = 0x00; i < pis.length; i++) {
                pis[i] = p;
            }
            AssertExtensions.assertEquals(-Math.log(p)/Math.log(2.0d), DecisionTreeUtils.calculateEntropy2p(pis));
        }
    }

    @Test
    public void testCalculateEntropyFlipIndex00() {
        ObjectAttribute<FooIntDouble, ? extends Object> target = ObjectAttributeInspector.inspect(FooIntDouble.class, "integer");
        ListArray<FooIntDouble> tids = new ListArray<>(new FooIntDouble(0x00, 0.0d), new FooIntDouble(0x00, 0.4d), new FooIntDouble(0x00, 0.8d), new FooIntDouble(0x00, 1.2d), new FooIntDouble(0x01, 1.6d), new FooIntDouble(0x01, 2.0d));
        Tuple2<Integer, Double> total_entropy = new Tuple2Base<>();
        int split = DecisionTreeUtils.calculateEntropyFlipIndex(tids, target, total_entropy);
        Assert.assertEquals(0x04, split);
        Assert.assertEquals(0x06, (int) total_entropy.getItem1());
        AssertExtensions.assertEquals(0.0d, total_entropy.getItem2());
    }

    @Test
    public void testCalculateEntropyFlipIndex01() {
        ObjectAttribute<FooIntDouble, ? extends Object> target = ObjectAttributeInspector.inspect(FooIntDouble.class, "integer");
        for (int t = 0x00; t < TestParameters.NUMBER_OF_TESTS; t++) {
            int n = ProbabilityUtils.nextInt(TestParameters.TEST_SIZE - 0x02) + 0x02;
            int m = ProbabilityUtils.nextInt(n - 0x01) + 0x01;
            ArrayList<FooIntDouble> tids = new ArrayList<>();
            for (int i = 0x00; i < m; i++) {
                tids.add(new FooIntDouble(0x00, (double) i / n));
            }
            for (int i = m; i < n; i++) {
                tids.add(new FooIntDouble(0x01, (double) i / n));
            }
            int split = DecisionTreeUtils.calculateEntropyFlipIndex(tids, target, null);
            Assert.assertEquals(m, split);
        }
    }

    @Test
    public void testCalculateEntropyFlipIndex02() {
        ObjectAttribute<FooIntDouble, ? extends Object> target = ObjectAttributeInspector.inspect(FooIntDouble.class, "integer");
        for (int t = 0x00; t < TestParameters.NUMBER_OF_TESTS; t++) {
            int n = 0x02 + ProbabilityUtils.nextInt(TestParameters.TEST_SIZE);
            int m = 0x01 + ProbabilityUtils.nextInt(n - 0x01);
            double ps = ProbabilityUtils.nextDouble();
            ArrayList<FooIntDouble> tids = new ArrayList<>();
            for (int i = 0x00; i < m; i++) {
                double p = ps * ProbabilityUtils.nextDouble();
                FooIntDouble tid = new FooIntDouble(0x00, p);
                tids.add(tid);
            }
            for (int i = m; i < n; i++) {
                double p = ps + (1.0d - ps) * ProbabilityUtils.nextDouble();
                FooIntDouble tid = new FooIntDouble(0x01, p);
                tids.add(tid);
            }
            int split = DecisionTreeUtils.calculateEntropyFlipIndex(tids, target, null);
            Assert.assertEquals(m, split);
        }
    }

    @Test
    public void testCalculateEntropyFlipIndex03() {
        ObjectAttribute<FooIntDouble, ? extends Object> target = ObjectAttributeInspector.inspect(FooIntDouble.class, "integer");
        for (int t = 0x00; t < TestParameters.NUMBER_OF_TESTS; t++) {
            int m = 0x01 + ProbabilityUtils.nextInt(TestParameters.NUMBER_OF_CATEGORIES);
            int n = 0x02 + ProbabilityUtils.nextInt(TestParameters.TEST_SIZE);
            double[] ps = ProbabilityUtils.fillRandomScale(m);
            Integer[] cnts = new Integer[m + 0x01];
            for (int i = 0x00; i < cnts.length; i++) {
                cnts[i] = 0x00;
            }
            ArrayList<FooIntDouble> tids = new ArrayList<>();
            for (int i = 0x00; i < n; i++) {
                double p = ProbabilityUtils.nextDouble();
                int category = CollectionUtils.getLowestInsertionPoint(ps, p);
                cnts[category]++;
                FooIntDouble tid = new FooIntDouble(category, p);
                tids.add(tid);
            }
            int sum = 0;
            boolean valid = true;
            for (int i = 0x00; i < cnts.length; i++) {
                if(cnts[i] == 0x00) {
                    valid = false;
                    break;
                }
                sum += cnts[i];
                cnts[i] = sum;
            }
            if (valid) {
                Collections.sort(tids);
                int split = DecisionTreeUtils.calculateEntropyFlipIndex(tids, target, null);
                AssertExtensions.assertContains(split, new ListArray<>(cnts));
            }
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testCalculateEntropyPartition00() {
        AssertExtensions.pushEpsilon(0.01d);
        ObjectAttribute<Test2B1T, ? extends Object> target = ObjectAttributeInspector.inspect(Test2B1T.class, "bool2");
        for (int t = 0x00; t < TestParameters.NUMBER_OF_TESTS; t++) {
            int N = ProbabilityUtils.nextInt(TestParameters.TEST_SIZE);
            ArrayList<Test2B1T> data0 = new ArrayList<>(N / 0x03);
            ArrayList<Test2B1T> data1 = new ArrayList<>(N / 0x03);
            int n = 0x00, n0 = 0x00, n1 = 0x00;
            double p = ProbabilityUtils.nextDouble();
            for (int i = 0x00; i < N; i++) {
                Test2B1T t2i = new Test2B1T(p);
                if (t2i.isBool1()) {
                    data0.add(t2i);
                    n++;
                    if (t2i.isBool2()) {
                        n0++;
                    }
                } else {
                    data1.add(t2i);
                    if (t2i.isBool2()) {
                        n1++;
                    }
                }
            }
            double expected = DecisionTreeUtils.calculateEntropy2pSplit((double) n / N, (double) n0 / n, (double) n1 / (N - n));
            double result = DecisionTreeUtils.calculateEntropyPartition(new ListArray<>(data0, data1), target);
            AssertExtensions.assertEquals(expected, result);
        }
        AssertExtensions.popEpsilon();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testCalculateEntropyPartition01() {
        String[] dat0 = {"tfO", "ttO", "tfO", "tfO", "tfO", "tfO", "tfO", "ttO", "ttO", "ttO", "tfO", "ttO", "tfO", "tfO", "ttO", "tfO", "tfO", "ttO", "tfO", "ttO", "tfO", "tfO", "ttO", "tfO", "ttO", "tfO", "ttO", "ttO", "ttO", "tfO", "tfO", "ttO", "tfO", "tfO", "tfO", "ttO", "ttO", "ttO", "tfO", "ttO", "ttO", "tfO", "ttO", "tfO"};
        String[] dat1 = {"ftO", "ftO", "ffO", "ftO", "ftO", "ffO", "ftO", "ffO", "ftO", "ftO", "ftO", "ffO", "ffO", "ftO", "ffO", "ffO", "ftO", "ftO", "ftO", "ftO", "ffO", "ffO", "ftO", "ftO", "ffO", "ffO", "ffO", "ftO", "ftO", "ftO", "ftO", "ftO", "ftO", "ftO", "ftO", "ffO"};
        ArrayList<Test2B1T> part0 = new ArrayList<>(dat0.length);
        ArrayList<Test2B1T> part1 = new ArrayList<>(dat1.length);
        for (String s : dat0) {
            part0.add(new Test2B1T(s));
        }
        for (String s : dat1) {
            part1.add(new Test2B1T(s));
        }
        ObjectAttribute<Test2B1T, ? extends Object> target = ObjectAttributeInspector.inspect(Test2B1T.class, "bool2");
        double expected = DecisionTreeUtils.calculateEntropy2pSplit(44.0d / 80.0d, 20.0d / 44.0d, 23.0d / 36.0d);
        @SuppressWarnings("unchecked")
        double result = DecisionTreeUtils.calculateEntropyPartition(new ListArray<>(part0, part1), target);
        AssertExtensions.assertEquals(expected, result);
        expected = DecisionTreeUtils.calculateInformationGain(44.0d / 80.0d, 20.0d / 44.0d, 23.0d / 36.0d);
        result = DecisionTreeUtils.calculateInformationGainPartition(new ListArray<>(part0, part1), target);
        AssertExtensions.assertEquals(expected, result);
        result = DecisionTreeUtils.calculateInformationGainReduce(new ListArray<>(part0, part1), target);
        AssertExtensions.assertEquals(-expected, result);
    }

    private class Foo implements Function<Foo, Integer> {

        private int value;

        Foo(int value) {
            this.value = value;
        }

        @Override
        public Integer evaluate(Foo x) {
            return x.getValue();
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 67 * hash + this.getValue();
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Foo other = (Foo) obj;
            if (this.getValue() != other.getValue()) {
                return false;
            }
            return true;
        }

        /**
         * @return the value
         */
        public int getValue() {
            return value;
        }

        /**
         * @param value the value to set
         */
        public void setValue(int value) {
            this.value = value;
        }

    }

}
