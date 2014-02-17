package jadetree;

import java.util.logging.Logger;
import jutils.iterators.ListGenericIterable;
import jutlis.MathUtils;
import jutlis.algebra.Function;
import org.junit.Test;
import static utils.AssertExtensions.assertEquals;
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
    public void testCalculateEntropy() {
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
        expResult = -0.75d*MathUtils.log2(0.75d)-0.25d*MathUtils.log2(0.25d);
        result = DecisionTreeUtils.calculateEntropy(new ListGenericIterable<>(new Foo(0x01), new Foo(0x01), new Foo(0x01), new Foo(0x03)));
        assertEquals(expResult, result);
        result = DecisionTreeUtils.calculateEntropy(new ListGenericIterable<>(new Foo(0x01), new Foo(0x01), new Foo(0x03), new Foo(0x01)));
        assertEquals(expResult, result);
        result = DecisionTreeUtils.calculateEntropy(new ListGenericIterable<>(new Foo(0x01), new Foo(0x03), new Foo(0x01), new Foo(0x01)));
        assertEquals(expResult, result);
        result = DecisionTreeUtils.calculateEntropy(new ListGenericIterable<>(new Foo(0x03), new Foo(0x01), new Foo(0x01), new Foo(0x01)));
        assertEquals(expResult, result);
        expResult = -0.5d*MathUtils.log2(0.5d)-0.5d*MathUtils.log2(0.25d);result = DecisionTreeUtils.calculateEntropy(new ListGenericIterable<>(new Foo(0x03), new Foo(0x03), new Foo(0x02), new Foo(0x01)));
        assertEquals(expResult, result);
    }

    @Test
    public void testCalculateEntropyFlipIndex() {
        int expResult = 0;
        /*int result = DecisionTreeUtils.calculateEntropyFlipIndex(null);
         assertEquals(expResult, result);
         // TODO review the generated test code and remove the default call to fail.
         fail("The test case is a prototype.");//*/
    }

    @Test
    public void testCalculateEntropyPartition() {
        double expResult = 0.0;
        /*double result = DecisionTreeUtils.calculateEntropyPartition(null);
         assertEquals(expResult, result, 0.0);
         // TODO review the generated test code and remove the default call to fail.
         fail("The test case is a prototype.");//*/
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
