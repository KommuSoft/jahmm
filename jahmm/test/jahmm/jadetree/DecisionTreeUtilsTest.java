package jahmm.jadetree;

import jahmm.jadetree.foo.Test2B1T;
import jahmm.jadetree.foo.TrisEnum;
import jahmm.jadetree.objectattributes.ObjectAttribute;
import jahmm.jadetree.objectattributes.ObjectAttributeInspector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;
import jutils.MathUtils;
import jutils.iterators.ListGenericIterable;
import jutils.probability.ProbabilityUtils;
import jutils.testing.AssertExtensions;
import static jutils.testing.AssertExtensions.assertEquals;
import jutlis.algebra.Function;
import jutlis.lists.ListArray;
import static org.junit.Assert.fail;
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
            for(int i = 0x00; i < tev.length; i++) {
                probs[i] = (double) counters[i]/N;
            }
            double expected = DecisionTreeUtils.calculateEntropy2p(probs);
            double result = DecisionTreeUtils.calculateEntropy(data, target);
            AssertExtensions.assertEquals(expected, result);
        }
    }

    @Test
    public void testCalculateEntropyFlipIndex() {
        fail("This test is a prototype");
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testCalculateEntropyPartition() {
        AssertExtensions.pushEpsilon(0.01d);
        ObjectAttribute<Test2B1T, ? extends Object> target = ObjectAttributeInspector.inspect(Test2B1T.class, "bool2");
        for (int t = 0x00; t < TestParameters.NUMBER_OF_TESTS; t++) {
            int N = ProbabilityUtils.nextInt(TestParameters.TEST_SIZE);
            ArrayList<Test2B1T> data0 = new ArrayList<>(N / 0x03);
            ArrayList<Test2B1T> data1 = new ArrayList<>(N / 0x03);
            int n = 0x00, n0 = 0x00, n1 = 0x00;
            double p = ProbabilityUtils.nextDouble();
            for (int i = 0x00; i < TestParameters.TEST_SIZE; i++) {
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
            System.out.println("---");
            for(Test2B1T item : data0) {
                System.out.println(item);
            }
            System.out.println("///");
            for(Test2B1T item : data1) {
                System.out.println(item);
            }
            AssertExtensions.assertEquals(expected, result);
        }
        AssertExtensions.popEpsilon();
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
