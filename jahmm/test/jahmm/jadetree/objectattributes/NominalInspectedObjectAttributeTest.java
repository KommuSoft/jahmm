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
    
    String[] names = new String[] {"div2","div4","div8","div16","div100","div200","div500","div1000","leap"};

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
        ObjectAttribute<TestLeapYear, ? extends Object> tly;
        NominalInspectedObjectAttribute<TestLeapYear, ? extends Object> nioa;
        tly = ObjectAttributeInspector.inspect(TestLeapYear.class, "div2");
        AssertExtensions.assertTypeof(NominalInspectedObjectAttribute.class, tly);
        nioa = (NominalInspectedObjectAttribute<TestLeapYear, ? extends Object>) tly;
        for (int i = 0x00; i < TestParameters.NUMBER_OF_TESTS; i++) {
            TestLeapYear tlyi = new TestLeapYear(i);
            Assert.assertEquals(tlyi.div2(), nioa.evaluate(tlyi));
        }
        tly = ObjectAttributeInspector.inspect(TestLeapYear.class, "div4");
        AssertExtensions.assertTypeof(NominalInspectedObjectAttribute.class, tly);
        nioa = (NominalInspectedObjectAttribute<TestLeapYear, ? extends Object>) tly;
        for (int i = 0x00; i < TestParameters.NUMBER_OF_TESTS; i++) {
            TestLeapYear tlyi = new TestLeapYear(i);
            Assert.assertEquals(tlyi.div4(), nioa.evaluate(tlyi));
        }
        tly = ObjectAttributeInspector.inspect(TestLeapYear.class, "div8");
        AssertExtensions.assertTypeof(NominalInspectedObjectAttribute.class, tly);
        nioa = (NominalInspectedObjectAttribute<TestLeapYear, ? extends Object>) tly;
        for (int i = 0x00; i < TestParameters.NUMBER_OF_TESTS; i++) {
            TestLeapYear tlyi = new TestLeapYear(i);
            Assert.assertEquals(tlyi.div8(), nioa.evaluate(tlyi));
        }
        tly = ObjectAttributeInspector.inspect(TestLeapYear.class, "div16");
        AssertExtensions.assertTypeof(NominalInspectedObjectAttribute.class, tly);
        nioa = (NominalInspectedObjectAttribute<TestLeapYear, ? extends Object>) tly;
        for (int i = 0x00; i < TestParameters.NUMBER_OF_TESTS; i++) {
            TestLeapYear tlyi = new TestLeapYear(i);
            Assert.assertEquals(tlyi.div16(), nioa.evaluate(tlyi));
        }
        tly = ObjectAttributeInspector.inspect(TestLeapYear.class, "div100");
        AssertExtensions.assertTypeof(NominalInspectedObjectAttribute.class, tly);
        nioa = (NominalInspectedObjectAttribute<TestLeapYear, ? extends Object>) tly;
        for (int i = 0x00; i < TestParameters.NUMBER_OF_TESTS; i++) {
            TestLeapYear tlyi = new TestLeapYear(i);
            Assert.assertEquals(tlyi.div100(), nioa.evaluate(tlyi));
        }
        tly = ObjectAttributeInspector.inspect(TestLeapYear.class, "div200");
        AssertExtensions.assertTypeof(NominalInspectedObjectAttribute.class, tly);
        nioa = (NominalInspectedObjectAttribute<TestLeapYear, ? extends Object>) tly;
        for (int i = 0x00; i < TestParameters.NUMBER_OF_TESTS; i++) {
            TestLeapYear tlyi = new TestLeapYear(i);
            Assert.assertEquals(tlyi.div200(), nioa.evaluate(tlyi));
        }
        tly = ObjectAttributeInspector.inspect(TestLeapYear.class, "div500");
        AssertExtensions.assertTypeof(NominalInspectedObjectAttribute.class, tly);
        nioa = (NominalInspectedObjectAttribute<TestLeapYear, ? extends Object>) tly;
        for (int i = 0x00; i < TestParameters.NUMBER_OF_TESTS; i++) {
            TestLeapYear tlyi = new TestLeapYear(i);
            Assert.assertEquals(tlyi.div500(), nioa.evaluate(tlyi));
        }
        tly = ObjectAttributeInspector.inspect(TestLeapYear.class, "div1000");
        AssertExtensions.assertTypeof(NominalInspectedObjectAttribute.class, tly);
        nioa = (NominalInspectedObjectAttribute<TestLeapYear, ? extends Object>) tly;
        for (int i = 0x00; i < TestParameters.NUMBER_OF_TESTS; i++) {
            TestLeapYear tlyi = new TestLeapYear(i);
            Assert.assertEquals(tlyi.div1000(), nioa.evaluate(tlyi));
        }
        tly = ObjectAttributeInspector.inspect(TestLeapYear.class, "leap");
        AssertExtensions.assertTypeof(NominalInspectedObjectAttribute.class, tly);
        nioa = (NominalInspectedObjectAttribute<TestLeapYear, ? extends Object>) tly;
        for (int i = 0x00; i < TestParameters.NUMBER_OF_TESTS; i++) {
            TestLeapYear tlyi = new TestLeapYear(i);
            Assert.assertEquals(tlyi.isLeapYear(), nioa.evaluate(tlyi));
        }
    }

    /**
     * Test of getName method, of class NominalInspectedObjectAttribute.
     */
    @Test
    public void testGetName() {
        for(String name : names) {
            ObjectAttribute<TestLeapYear, ? extends Object> tly = ObjectAttributeInspector.inspect(TestLeapYear.class, name);
            AssertExtensions.assertTypeof(NominalInspectedObjectAttribute.class, tly);
            Assert.assertEquals(name, tly.getName());
            NominalInspectedObjectAttribute<TestLeapYear, ? extends Object> nioa = (NominalInspectedObjectAttribute<TestLeapYear, ? extends Object>) tly;
            Assert.assertEquals(name, nioa.getName());
        }
    }

}
