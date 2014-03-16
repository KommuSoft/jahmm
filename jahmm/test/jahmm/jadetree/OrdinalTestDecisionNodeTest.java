package jahmm.jadetree;

import jahmm.jadetree.objectattributes.ComparableOrdinalObjectAttributeBase;
import java.util.logging.Logger;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author kommusoft
 */
public class OrdinalTestDecisionNodeTest {

    private static final Logger LOG = Logger.getLogger(OrdinalTestDecisionNodeTest.class.getName());
    private ComparableOrdinalObjectAttributeBase<Integer> ooa1 = new ComparableOrdinalObjectAttributeBase<>("value");
    private ComparableOrdinalObjectAttributeBase<String> ooa2 = new ComparableOrdinalObjectAttributeBase<>("username");

    public OrdinalTestDecisionNodeTest() {
    }

    /**
     * Test of test method, of class OrdinalTestDecisionNode.
     */
    @Test
    public void testTest() {
        OrdinalTestDecisionNode<Integer,Integer> otdn1 = new OrdinalTestDecisionNode<>(null,ooa1,4);
        OrdinalTestDecisionNode<String,String> otdn2 = new OrdinalTestDecisionNode<>(null,ooa2,"kommusoft");
    }

    /**
     * Test of toString method, of class OrdinalTestDecisionNode.
     */
    @Test
    public void testToString() {
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOrdinalArgument method, of class OrdinalTestDecisionNode.
     */
    @Test
    public void testGetOrdinalArgument() {
        fail("The test case is a prototype.");
    }

    /**
     * Test of getState method, of class OrdinalTestDecisionNode.
     */
    @Test
    public void testGetState() {
        fail("The test case is a prototype.");
    }

}
