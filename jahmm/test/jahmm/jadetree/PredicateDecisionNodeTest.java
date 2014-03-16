package jahmm.jadetree;

import java.util.logging.Logger;
import junit.framework.Assert;
import jutils.testing.AssertExtensions;
import jutlis.algebra.Predicate;
import jutlis.algebra.predicates.GreaterThanOrEqualToPredicate;
import jutlis.algebra.predicates.LessThanPredicate;
import org.junit.Test;
import utils.TestData;

/**
 *
 * @author kommusoft
 */
public class PredicateDecisionNodeTest {

    private static final Logger LOG = Logger.getLogger(PredicateDecisionNodeTest.class.getName());
    Predicate<Integer> pred1;
    Predicate<String> pred2;

    public PredicateDecisionNodeTest() {
        pred1 = new LessThanPredicate<>(TestData.split1);
        pred2 = new GreaterThanOrEqualToPredicate<>(TestData.split2);
    }

    /**
     * Test of toString method, of class PredicateDecisionNode.
     */
    @Test
    public void testToString() {
        PredicateDecisionNode<Integer> pdn1 = new PredicateDecisionNode<>(null, pred1);
        Assert.assertEquals(String.format("< %s", TestData.split1), pdn1.toString());
        PredicateDecisionNode<String> pdn2 = new PredicateDecisionNode<>(null, pred2);
        Assert.assertEquals(String.format(">= %s", TestData.split2), pdn2.toString());
    }

    /**
     * Test of test method, of class PredicateDecisionNode.
     */
    @Test
    public void testTest() {
        PredicateDecisionNode<Integer> pdn1 = new PredicateDecisionNode<>(null, pred1);
        for(Integer v : TestData.vals1geq) {
            Assert.assertFalse(pdn1.test(v));
        }
        for(Integer v : TestData.vals1le) {
            Assert.assertTrue(pdn1.test(v));
        }
        PredicateDecisionNode<String> pdn2 = new PredicateDecisionNode<>(null, pred2);
        for(String v : TestData.vals2le) {
            Assert.assertFalse(pdn2.test(v));
        }
        for(String v : TestData.vals2geq) {
            Assert.assertTrue(pdn2.test(v));
        }
    }

    /**
     * Test of getPredicate method, of class PredicateDecisionNode.
     */
    @Test
    public void testGetPredicate() {
        PredicateDecisionNode<Integer> pdn1 = new PredicateDecisionNode<>(null, pred1);
        Assert.assertEquals(pred1, pdn1.getPredicate());
        PredicateDecisionNode<String> pdn2 = new PredicateDecisionNode<>(null, pred2);
        Assert.assertEquals(pred2, pdn2.getPredicate());
    }

    /**
     * Test of test method, of class PredicateDecisionNode.
     */
    @Test
    public void testInsert() {
        PredicateDecisionNode<Integer> pdn1 = new PredicateDecisionNode<>(null, pred1, TestData.vals1);
        PredicateDecisionNode<String> pdn2 = new PredicateDecisionNode<>(null, pred2, TestData.vals2);
        AssertExtensions.assertEqualsOrdered(TestData.vals1le, pdn1.getTrueNode());
        AssertExtensions.assertEqualsOrdered(TestData.vals1geq, pdn1.getFalseNode());
        AssertExtensions.assertEqualsOrdered(TestData.vals2geq, pdn2.getTrueNode());
        AssertExtensions.assertEqualsOrdered(TestData.vals2le, pdn2.getFalseNode());
    }

}
