package jahmm.jadetree;

import java.util.logging.Logger;
import junit.framework.Assert;
import jutlis.algebra.Predicate;
import jutlis.algebra.predicates.GreaterThanOrEqualToPredicate;
import jutlis.algebra.predicates.LessThanPredicate;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author kommusoft
 */
public class PredicateDecisionNodeTest {

    private static final Logger LOG = Logger.getLogger(PredicateDecisionNodeTest.class.getName());
    Predicate<Integer> pred1;
    Predicate<String> pred2;

    public PredicateDecisionNodeTest() {
         pred1 = new LessThanPredicate<>(4);
         pred2 = new GreaterThanOrEqualToPredicate<>("kommusoft");
    }

    /**
     * Test of toString method, of class PredicateDecisionNode.
     */
    @Test
    public void testToString() {
        PredicateDecisionNode<Integer> pdn1 = new PredicateDecisionNode<>(null,pred1);
        Assert.assertEquals("< 4", pdn1.toString());
        PredicateDecisionNode<String> pdn2 = new PredicateDecisionNode<>(null,pred2);
        Assert.assertEquals(">= kommusoft", pdn2.toString());
    }

    /**
     * Test of test method, of class PredicateDecisionNode.
     */
    @Test
    public void testTest() {
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPredicate method, of class PredicateDecisionNode.
     */
    @Test
    public void testGetPredicate() {
        fail("The test case is a prototype.");
    }

}
