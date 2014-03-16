package jahmm.jadetree;

import jahmm.jadetree.foo.Test2B1T;
import jahmm.jadetree.foo.TrisEnum;
import jahmm.jadetree.objectattributes.NominalObjectAttribute;
import jahmm.jadetree.objectattributes.ObjectAttribute;
import jahmm.jadetree.objectattributes.ObjectAttributeInspector;
import java.util.logging.Logger;
import junit.framework.Assert;
import jutils.testing.AssertExtensions;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author kommusoft
 */
public class EnumerableDecisionNodeTest {

    private static final Logger LOG = Logger.getLogger(EnumerableDecisionNodeTest.class.getName());
    private static ObjectAttribute<Test2B1T, ? extends Object> oae;

    public EnumerableDecisionNodeTest() {
        oae = ObjectAttributeInspector.inspect(Test2B1T.class, "tris");
    }

    /**
     * Test of nextHop method, of class EnumerableDecisionNode.
     */
    @Test
    public void testCorrectType() {
        AssertExtensions.assertTypeof(NominalObjectAttribute.class, oae);
    }

    /**
     * Test of nextHop method, of class EnumerableDecisionNode.
     */
    @Test
    public void testNextHop() {
        EnumerableDecisionNode<Test2B1T, ? extends Object> edn = new EnumerableDecisionNode<>(null, oae);
        DecisionRealNode<Test2B1T> la = edn.nextHop(new Test2B1T(false, false, TrisEnum.Odin));
        DecisionRealNode<Test2B1T> lb = edn.nextHop(new Test2B1T(false, false, TrisEnum.Dva));
        DecisionRealNode<Test2B1T> lc = edn.nextHop(new Test2B1T(false, false, TrisEnum.Tri));
        AssertExtensions.assertTypeof(DecisionLeaf.class, la);
        AssertExtensions.assertTypeof(DecisionLeaf.class, lb);
        AssertExtensions.assertTypeof(DecisionLeaf.class, lc);
        Assert.assertNotSame(la, lb);
        Assert.assertNotSame(la, lc);
        Assert.assertNotSame(lb, lc);
        Assert.assertEquals(la, edn.nextHop(new Test2B1T(false, false, TrisEnum.Odin)));
        Assert.assertEquals(lb, edn.nextHop(new Test2B1T(false, false, TrisEnum.Dva)));
        Assert.assertEquals(lc, edn.nextHop(new Test2B1T(false, false, TrisEnum.Tri)));
        fail("The test case is a prototype.");
    }

    /**
     * Test of replaceChild method, of class EnumerableDecisionNode.
     */
    @Test
    public void testReplaceChild() {
        fail("The test case is a prototype.");
    }

    /**
     * Test of getChildren method, of class EnumerableDecisionNode.
     */
    @Test
    public void testGetChildren() {
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class EnumerableDecisionNode.
     */
    @Test
    public void testToString() {
        EnumerableDecisionNode<Test2B1T, ? extends Object> edn = new EnumerableDecisionNode<>(null, oae);
        Assert.assertEquals("tris", edn.toString());
    }

}
