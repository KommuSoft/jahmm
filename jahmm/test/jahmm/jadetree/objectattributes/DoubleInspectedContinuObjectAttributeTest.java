package jahmm.jadetree.objectattributes;

import jahmm.jadetree.foo.TestSquareEquation;
import java.util.logging.Logger;
import jutils.testing.AssertExtensions;
import org.junit.Assert;
import org.junit.Test;
import utils.TestParameters;

/**
 *
 * @author kommusoft
 */
public class DoubleInspectedContinuObjectAttributeTest {

    private static final Logger LOG = Logger.getLogger(DoubleInspectedContinuObjectAttributeTest.class.getName());

    String[] names = new String[]{"a", "b", "c", "d2"};

    public DoubleInspectedContinuObjectAttributeTest() {
    }

    /**
     * Test of getName method, of class DoubleInspectedContinuObjectAttribute.
     */
    @Test
    public void testGetName() {
        for (String name : names) {
            ObjectAttribute<TestSquareEquation, ? extends Object> tly = ObjectAttributeInspector.inspect(TestSquareEquation.class, name);
            AssertExtensions.assertTypeof(DoubleInspectedContinuObjectAttribute.class, tly);
            Assert.assertEquals(name, tly.getName());
            @SuppressWarnings("unchecked")
            DoubleInspectedContinuObjectAttribute<TestSquareEquation> nioa = (DoubleInspectedContinuObjectAttribute<TestSquareEquation>) tly;
            Assert.assertEquals(name, nioa.getName());
        }
    }

    /**
     * Test of evaluate method, of class DoubleInspectedContinuObjectAttribute.
     */
    @Test
    public void testEvaluate() {
        ObjectAttribute<TestSquareEquation, ? extends Object> tly;
        DoubleInspectedContinuObjectAttribute<TestSquareEquation> nioa;
        tly = ObjectAttributeInspector.inspect(TestSquareEquation.class, "a");
        AssertExtensions.assertTypeof(DoubleInspectedContinuObjectAttribute.class, tly);
        nioa = (DoubleInspectedContinuObjectAttribute<TestSquareEquation>) tly;
        for (int i = 0x00; i < TestParameters.NUMBER_OF_TESTS; i++) {
            TestSquareEquation tlyi = TestSquareEquation.randomInstance();
            Assert.assertEquals((Double) tlyi.getA(), nioa.evaluate(tlyi));
        }
        tly = ObjectAttributeInspector.inspect(TestSquareEquation.class, "b");
        AssertExtensions.assertTypeof(DoubleInspectedContinuObjectAttribute.class, tly);
        nioa = (DoubleInspectedContinuObjectAttribute<TestSquareEquation>) tly;
        for (int i = 0x00; i < TestParameters.NUMBER_OF_TESTS; i++) {
            TestSquareEquation tlyi = TestSquareEquation.randomInstance();
            Assert.assertEquals((Double) tlyi.getB(), nioa.evaluate(tlyi));
        }
        tly = ObjectAttributeInspector.inspect(TestSquareEquation.class, "c");
        AssertExtensions.assertTypeof(DoubleInspectedContinuObjectAttribute.class, tly);
        nioa = (DoubleInspectedContinuObjectAttribute<TestSquareEquation>) tly;
        for (int i = 0x00; i < TestParameters.NUMBER_OF_TESTS; i++) {
            TestSquareEquation tlyi = TestSquareEquation.randomInstance();
            Assert.assertEquals((Double) tlyi.getC(), nioa.evaluate(tlyi));
        }
        tly = ObjectAttributeInspector.inspect(TestSquareEquation.class, "d2");
        AssertExtensions.assertTypeof(DoubleInspectedContinuObjectAttribute.class, tly);
        nioa = (DoubleInspectedContinuObjectAttribute<TestSquareEquation>) tly;
        for (int i = 0x00; i < TestParameters.NUMBER_OF_TESTS; i++) {
            TestSquareEquation tlyi = TestSquareEquation.randomInstance();
            Assert.assertEquals((Double) tlyi.getD2(), nioa.evaluate(tlyi));
        }
    }

    @Test
    public void testScore00() {

    }

}
