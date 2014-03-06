package jahmm.jadetree.objectattributes;

import jahmm.jadetree.foo.TestLeapYear;
import java.util.logging.Logger;
import jutils.testing.AssertExtensions;
import jutils.types.TypeUtils;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

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
        ObjectAttribute<TestLeapYear,? extends Object> tly = ObjectAttributeInspector.inspect(TestLeapYear.class, "div4");
        AssertExtensions.assertTypeof(NominalInspectedObjectAttribute.class, tly);
        NominalInspectedObjectAttribute<TestLeapYear,? extends Object> nioa = (NominalInspectedObjectAttribute<TestLeapYear,? extends Object>) tly;
        Assert.assertEquals(TypeUtils.getNominalSet(Boolean.class), nioa.getPossibleValues());
    }

    /**
     * Test of evaluate method, of class NominalInspectedObjectAttribute.
     */
    @Test
    public void testEvaluate() {
        System.out.println("evaluate");
        Object source = null;
        NominalInspectedObjectAttribute instance = null;
        Object expResult = null;
        Object result = instance.evaluate(source);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class NominalInspectedObjectAttribute.
     */
    @Test
    public void testGetName() {
        ObjectAttribute<TestLeapYear,? extends Object> tly = ObjectAttributeInspector.inspect(TestLeapYear.class, "div4");
        AssertExtensions.assertTypeof(NominalInspectedObjectAttribute.class, tly);
        Assert.assertEquals("div4", tly.getName());
        NominalInspectedObjectAttribute<TestLeapYear,? extends Object> nioa = (NominalInspectedObjectAttribute<TestLeapYear,? extends Object>) tly;
        Assert.assertEquals("div4", nioa.getName());
    }

}
