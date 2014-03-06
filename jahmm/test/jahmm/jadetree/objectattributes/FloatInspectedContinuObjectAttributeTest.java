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
public class FloatInspectedContinuObjectAttributeTest {

    private static final Logger LOG = Logger.getLogger(FloatInspectedContinuObjectAttributeTest.class.getName());

    String[] names = new String[]{"x1", "x2"};

    public FloatInspectedContinuObjectAttributeTest() {
    }

    /**
     * Test of getName method, of class FloatInspectedContinuObjectAttribute.
     */
    @Test
    public void testGetName() {
        for (String name : names) {
            ObjectAttribute<TestSquareEquation, ? extends Object> tly = ObjectAttributeInspector.inspect(TestSquareEquation.class, name);
            AssertExtensions.assertTypeof(FloatInspectedContinuObjectAttribute.class, tly);
            Assert.assertEquals(name, tly.getName());
            @SuppressWarnings("unchecked")
            FloatInspectedContinuObjectAttribute<TestSquareEquation> nioa = (FloatInspectedContinuObjectAttribute<TestSquareEquation>) tly;
            Assert.assertEquals(name, nioa.getName());
        }
    }

    /**
     * Test of evaluate method, of class FloatInspectedContinuObjectAttribute.
     */
    @Test
    public void testEvaluate() {
        ObjectAttribute<TestSquareEquation, ? extends Object> tly;
        FloatInspectedContinuObjectAttribute<TestSquareEquation> nioa;
        tly = ObjectAttributeInspector.inspect(TestSquareEquation.class, "x1");
        AssertExtensions.assertTypeof(FloatInspectedContinuObjectAttribute.class, tly);
        nioa = (FloatInspectedContinuObjectAttribute<TestSquareEquation>) tly;
        for (int i = 0x00; i < TestParameters.NUMBER_OF_TESTS; i++) {
            TestSquareEquation tlyi = TestSquareEquation.randomInstance();
            Assert.assertEquals((Float) tlyi.getX1(), nioa.evaluate(tlyi));
        }
        tly = ObjectAttributeInspector.inspect(TestSquareEquation.class, "x2");
        AssertExtensions.assertTypeof(FloatInspectedContinuObjectAttribute.class, tly);
        nioa = (FloatInspectedContinuObjectAttribute<TestSquareEquation>) tly;
        for (int i = 0x00; i < TestParameters.NUMBER_OF_TESTS; i++) {
            TestSquareEquation tlyi = TestSquareEquation.randomInstance();
            Assert.assertEquals((Float) tlyi.getX2(), nioa.evaluate(tlyi));
        }
    }

}
