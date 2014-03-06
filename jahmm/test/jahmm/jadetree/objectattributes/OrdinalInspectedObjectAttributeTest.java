package jahmm.jadetree.objectattributes;

import jahmm.jadetree.foo.TestLeapYear;
import java.util.logging.Logger;
import jutils.testing.AssertExtensions;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
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
        System.out.println("compareWith");
        Object source = null;
        Object target = null;
        OrdinalInspectedObjectAttribute instance = null;
        int expResult = 0;
        int result = instance.compareWith(source, target);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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

    /**
     * Test of compare method, of class OrdinalInspectedObjectAttribute.
     */
    @Test
    public void testCompare() {
        System.out.println("compare");
        Object t1 = null;
        Object t2 = null;
        OrdinalInspectedObjectAttribute instance = null;
        int expResult = 0;
        int result = instance.compare(t1, t2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
