package jahmm.jadetree;

import jahmm.jadetree.objectattributes.InternalOrdinalObjectAttributeBase;
import java.util.logging.Logger;
import junit.framework.Assert;
import jutils.collections.CollectionUtils;
import jutils.testing.AssertExtensions;
import static org.junit.Assert.fail;
import org.junit.Test;
import utils.TestData;

/**
 *
 * @author kommusoft
 */
public class OrdinalTestDecisionNodeTest {

    private static final Logger LOG = Logger.getLogger(OrdinalTestDecisionNodeTest.class.getName());
    private final InternalOrdinalObjectAttributeBase<Integer> ooa1 = new InternalOrdinalObjectAttributeBase<>(TestData.name1);
    private final InternalOrdinalObjectAttributeBase<String> ooa2 = new InternalOrdinalObjectAttributeBase<>(TestData.name2);

    public OrdinalTestDecisionNodeTest() {
    }

    /**
     * Test of test method, of class OrdinalTestDecisionNode.
     */
    @Test
    public void testTest() {
        OrdinalTestDecisionNode<Integer, Integer> otdn1 = new OrdinalTestDecisionNode<>(null, ooa1, TestData.split1);
        OrdinalTestDecisionNode<String, String> otdn2 = new OrdinalTestDecisionNode<>(null, ooa2, TestData.split2);
        for (Integer v : TestData.vals1geq) {
            Assert.assertFalse(otdn1.test(v));
        }
        for (Integer v : TestData.vals1le) {
            Assert.assertTrue(otdn1.test(v));
        }
        for (String v : TestData.vals2geq) {
            Assert.assertFalse(otdn2.test(v));
        }
        for (String v : TestData.vals2le) {
            Assert.assertTrue(otdn2.test(v));
        }
    }

    /**
     * Test of toString method, of class OrdinalTestDecisionNode.
     */
    @Test
    public void testToString() {
        OrdinalTestDecisionNode<Integer, Integer> otdn1 = new OrdinalTestDecisionNode<>(null, ooa1, TestData.split1);
        OrdinalTestDecisionNode<String, String> otdn2 = new OrdinalTestDecisionNode<>(null, ooa2, TestData.split2);
        Assert.assertEquals(String.format("%s < %s", TestData.name1, TestData.split1), otdn1.toString());
        Assert.assertEquals(String.format("%s < %s", TestData.name2, TestData.split2), otdn2.toString());
    }

    /**
     * Test of getOrdinalArgument method, of class OrdinalTestDecisionNode.
     */
    @Test
    public void testGetOrdinalArgument() {
        OrdinalTestDecisionNode<Integer, Integer> otdn1 = new OrdinalTestDecisionNode<>(null, ooa1, TestData.split1);
        OrdinalTestDecisionNode<String, String> otdn2 = new OrdinalTestDecisionNode<>(null, ooa2, TestData.split2);
        Assert.assertEquals(ooa1, otdn1.getOrdinalArgument());
        Assert.assertEquals(ooa2, otdn2.getOrdinalArgument());
    }

    /**
     * Test of getState method, of class OrdinalTestDecisionNode.
     */
    @Test
    public void testGetState() {
        OrdinalTestDecisionNode<Integer, Integer> otdn1 = new OrdinalTestDecisionNode<>(null, ooa1, TestData.split1);
        OrdinalTestDecisionNode<String, String> otdn2 = new OrdinalTestDecisionNode<>(null, ooa2, TestData.split2);
        Assert.assertEquals(TestData.split1, otdn1.getState());
        Assert.assertEquals(TestData.split2, otdn2.getState());
    }

    /**
     * Test of test method, of class PredicateDecisionNode.
     */
    @Test
    public void testInsert() {
        OrdinalTestDecisionNode<Integer, Integer> otdn1 = new OrdinalTestDecisionNode<>(null, ooa1, TestData.split1,TestData.vals1);
        OrdinalTestDecisionNode<String, String> otdn2 = new OrdinalTestDecisionNode<>(null, ooa2, TestData.split2,TestData.vals2);
        AssertExtensions.assertEqualsOrdered(TestData.vals1le, otdn1.getTrueNode());
        AssertExtensions.assertEqualsOrdered(TestData.vals1geq, otdn1.getFalseNode());
        AssertExtensions.assertEqualsOrdered(TestData.vals2le, otdn2.getTrueNode());
        AssertExtensions.assertEqualsOrdered(TestData.vals2geq, otdn2.getFalseNode());
    }

}
