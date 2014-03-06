package jahmm.jadetree.objectattributes;

import jahmm.jadetree.foo.TestLeapYear;
import java.util.logging.Logger;
import jutils.testing.AssertExtensions;
import jutils.types.TypeUtils;
import org.junit.Assert;
import org.junit.Test;
import utils.TestParameters;

/**
 *
 * @author kommusoft
 */
public class NominalInspectedObjectAttributeTest {

    private static final Logger LOG = Logger.getLogger(NominalInspectedObjectAttributeTest.class.getName());

    public NominalInspectedObjectAttributeTest() {
    }

    /**
     * Test of getPossibleValues method, of class
     * NominalInspectedObjectAttribute.
     */
    @Test
    public void testGetPossibleValues() {
        for (ObjectAttribute<TestLeapYear, ? extends Object> tly : ObjectAttributeInspector.inspect(TestLeapYear.class) ) {
            AssertExtensions.assertTypeof(NominalInspectedObjectAttribute.class, tly);
            NominalInspectedObjectAttribute<TestLeapYear, ? extends Object> nioa = (NominalInspectedObjectAttribute<TestLeapYear, ? extends Object>) tly;
            Assert.assertEquals(TypeUtils.getNominalSet(Boolean.class), nioa.getPossibleValues());
        }
    }

    /**
     * Test of evaluate method, of class NominalInspectedObjectAttribute.
     */
    @Test
    public void testEvaluate() {
        ObjectAttribute<TestLeapYear, ? extends Object> tly = ObjectAttributeInspector.inspect(TestLeapYear.class, "div4");
        AssertExtensions.assertTypeof(NominalInspectedObjectAttribute.class, tly);
        NominalInspectedObjectAttribute<TestLeapYear, ? extends Object> nioa = (NominalInspectedObjectAttribute<TestLeapYear, ? extends Object>) tly;
        for (int i = 0x00; i < TestParameters.NUMBER_OF_TESTS; i++) {
            TestLeapYear tlyi = new TestLeapYear(i);
            Assert.assertEquals(tlyi.Div4(), nioa.evaluate(tlyi));
        }
    }

    /**
     * Test of getName method, of class NominalInspectedObjectAttribute.
     */
    @Test
    public void testGetName() {
        ObjectAttribute<TestLeapYear, ? extends Object> tly = ObjectAttributeInspector.inspect(TestLeapYear.class, "div4");
        AssertExtensions.assertTypeof(NominalInspectedObjectAttribute.class, tly);
        Assert.assertEquals("div4", tly.getName());
        NominalInspectedObjectAttribute<TestLeapYear, ? extends Object> nioa = (NominalInspectedObjectAttribute<TestLeapYear, ? extends Object>) tly;
        Assert.assertEquals("div4", nioa.getName());
    }

}
