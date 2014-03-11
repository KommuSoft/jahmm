package jahmm.jadetree.objectattributes;

import jahmm.jadetree.DecisionTreeUtils;
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
    private final ObjectAttribute<TestLeapYear, ? extends Object> oad2, oad4, oad8, oad16, oad100, oad200, oad500, oad1000, target;

    String[] names = new String[]{"div2", "div4", "div8", "div16", "div100", "div200", "div500", "div1000", "leap"};

    public NominalInspectedObjectAttributeTest() {
        oad2 = ObjectAttributeInspector.inspect(TestLeapYear.class, "div2");
        oad4 = ObjectAttributeInspector.inspect(TestLeapYear.class, "div4");
        oad8 = ObjectAttributeInspector.inspect(TestLeapYear.class, "div8");
        oad16 = ObjectAttributeInspector.inspect(TestLeapYear.class, "div16");
        oad100 = ObjectAttributeInspector.inspect(TestLeapYear.class, "div100");
        oad200 = ObjectAttributeInspector.inspect(TestLeapYear.class, "div200");
        oad500 = ObjectAttributeInspector.inspect(TestLeapYear.class, "div500");
        oad1000 = ObjectAttributeInspector.inspect(TestLeapYear.class, "div1000");
        target = ObjectAttributeInspector.inspect(TestLeapYear.class, "leap");
    }

    /**
     * Test of getPossibleValues method, of class
     * NominalInspectedObjectAttribute.
     */
    @Test
    public void testGetPossibleValues() {
        for (ObjectAttribute<TestLeapYear, ? extends Object> tly : ObjectAttributeInspector.inspect(TestLeapYear.class, this.names)) {
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
        for (String name : names) {
            ObjectAttribute<TestLeapYear, ? extends Object> tly = ObjectAttributeInspector.inspect(TestLeapYear.class, name);
            AssertExtensions.assertTypeof(NominalInspectedObjectAttribute.class, tly);
            Assert.assertEquals(name, tly.getName());
            NominalInspectedObjectAttribute<TestLeapYear, ? extends Object> nioa = (NominalInspectedObjectAttribute<TestLeapYear, ? extends Object>) tly;
            Assert.assertEquals(name, nioa.getName());
        }
    }

    @Test
    public void testScore00() {
        //a boring test
        TestLeapYear[] tly = new TestLeapYear[TestParameters.NUMBER_OF_TESTS];
        for (int i = 0x01, j = 0x00; j < TestParameters.NUMBER_OF_TESTS; i += 0x04, j++) {
            tly[j] = new TestLeapYear(i);
        }
        AssertExtensions.assertEquals(0.0d, oad2.calculateScore(target, null, tly));
        AssertExtensions.assertEquals(0.0d, oad4.calculateScore(target, null, tly));
        AssertExtensions.assertEquals(0.0d, oad8.calculateScore(target, null, tly));
        AssertExtensions.assertEquals(0.0d, oad16.calculateScore(target, null, tly));
        AssertExtensions.assertEquals(0.0d, oad100.calculateScore(target, null, tly));
        AssertExtensions.assertEquals(0.0d, oad200.calculateScore(target, null, tly));
        AssertExtensions.assertEquals(0.0d, oad500.calculateScore(target, null, tly));
        AssertExtensions.assertEquals(0.0d, oad1000.calculateScore(target, null, tly));
    }

    @Test
    public void testScore01() {
        int n = 160;
        int y = (n + 3) / 4 - (n + 99) / 100 - (n + 999) / 1000;
        double expected, score;
        double c;
        AssertExtensions.pushEpsilon(0.001d);
        TestLeapYear[] tly = new TestLeapYear[n];
        for (int i = 0x00; i < n; i++) {
            tly[i] = new TestLeapYear(i);
        }
        c = 2.0d;
        expected = DecisionTreeUtils.calculateEntropy2pSplit(1.0d / c, y*c/n, 0.0d);
        score = oad2.calculateScore(target, null, tly);
        AssertExtensions.assertEquals(expected, score);
        c = 4.0d;
        expected = DecisionTreeUtils.calculateEntropy2pSplit(1.0d / c, y * c / n, 0.0d);
        score = oad4.calculateScore(target, null, tly);
        AssertExtensions.assertEquals(expected, score);
        c = 8.0d;
        expected = DecisionTreeUtils.calculateEntropy2pSplit(1.0d / c, 21*c/n, 19*c/n);
        score = oad8.calculateScore(target, null, tly);
        AssertExtensions.assertEquals(expected, score);
        c = 16.0d;
        expected = DecisionTreeUtils.calculateEntropy2pSplit(1.0d / c, (n / 2.0d - 1.0d) / n, 0.0d);
        score = oad16.calculateScore(target, null, tly);
        AssertExtensions.assertEquals(expected, score);
        AssertExtensions.popEpsilon();
    }

}
