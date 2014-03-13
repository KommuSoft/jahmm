package jahmm.jadetree.objectattributes;

import jahmm.jadetree.foo.TestLeapYear;
import java.util.logging.Logger;
import jutils.testing.AssertExtensions;
import org.junit.Assert;
import org.junit.Test;
import utils.TestParameters;

/**
 *
 * @author kommusoft
 */
public class OrdinalInspectedObjectAttributeTest {

    private static final Logger LOG = Logger.getLogger(OrdinalInspectedObjectAttributeTest.class.getName());

    String[] names = new String[]{"year"};

    public OrdinalInspectedObjectAttributeTest() {
    }

    /**
     * Test of compareWith method, of class OrdinalInspectedObjectAttribute.
     */
    @Test
    public void testCompareWith() {
        ObjectAttribute<TestLeapYear, ? extends Object> tly;
        OrdinalInspectedObjectAttribute<TestLeapYear, Object> nioa;
        tly = ObjectAttributeInspector.inspect(TestLeapYear.class, "year");
        AssertExtensions.assertTypeof(OrdinalInspectedObjectAttribute.class, tly);
        nioa = (OrdinalInspectedObjectAttribute<TestLeapYear, Object>) tly;
        for (int i = 0x00; i < TestParameters.NUMBER_OF_TESTS; i++) {
            TestLeapYear tlyi = new TestLeapYear(i);
            for (int j = 0x00; j < TestParameters.NUMBER_OF_TESTS; j++) {
                Assert.assertEquals(((Integer) tlyi.year()).compareTo(j), nioa.compareWith(tlyi, j));
            }
        }
    }

    /**
     * Test of getName method, of class OrdinalInspectedObjectAttribute.
     */
    @Test
    public void testGetName() {
        for (String name : names) {
            ObjectAttribute<TestLeapYear, ? extends Object> tly = ObjectAttributeInspector.inspect(TestLeapYear.class, name);
            AssertExtensions.assertTypeof(OrdinalInspectedObjectAttribute.class, tly);
            Assert.assertEquals(name, tly.getName());
            OrdinalInspectedObjectAttribute<TestLeapYear, ? extends Object> nioa = (OrdinalInspectedObjectAttribute<TestLeapYear, ? extends Object>) tly;
            Assert.assertEquals(name, nioa.getName());
        }
    }

    /**
     * Test of evaluate method, of class OrdinalInspectedObjectAttribute.
     */
    @Test
    public void testEvaluate() {
        ObjectAttribute<TestLeapYear, ? extends Object> tly;
        OrdinalInspectedObjectAttribute<TestLeapYear, ? extends Object> nioa;
        tly = ObjectAttributeInspector.inspect(TestLeapYear.class, "year");
        AssertExtensions.assertTypeof(OrdinalInspectedObjectAttribute.class, tly);
        nioa = (OrdinalInspectedObjectAttribute<TestLeapYear, ? extends Object>) tly;
        for (int i = 0x00; i < TestParameters.NUMBER_OF_TESTS; i++) {
            TestLeapYear tlyi = new TestLeapYear(i);
            Assert.assertEquals(tlyi.year(), nioa.evaluate(tlyi));
        }
    }

    @Test
    public void testScore00() {

    }

    /**
     * Test of compare method, of class OrdinalInspectedObjectAttribute.
     */
    @Test
    public void testCompare() {
        ObjectAttribute<TestLeapYear, ? extends Object> tly;
        OrdinalInspectedObjectAttribute<TestLeapYear, ? extends Object> nioa;
        tly = ObjectAttributeInspector.inspect(TestLeapYear.class, "year");
        AssertExtensions.assertTypeof(OrdinalInspectedObjectAttribute.class, tly);
        nioa = (OrdinalInspectedObjectAttribute<TestLeapYear, ? extends Object>) tly;
        for (int i = 0x00; i < TestParameters.NUMBER_OF_TESTS; i++) {
            TestLeapYear tlyi = new TestLeapYear(i);
            for (int j = 0x00; j < TestParameters.NUMBER_OF_TESTS; j++) {
                TestLeapYear tlyj = new TestLeapYear(j);
                Assert.assertEquals(((Integer) tlyi.year()).compareTo(tlyj.year()), nioa.compare(tlyi, tlyj));
            }
        }
    }

}
