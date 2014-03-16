package jahmm.jadetree;

import jahmm.jadetree.foo.Test2B1T;
import jahmm.jadetree.foo.TrisEnum;
import jahmm.jadetree.objectattributes.NominalObjectAttribute;
import jahmm.jadetree.objectattributes.ObjectAttribute;
import jahmm.jadetree.objectattributes.ObjectAttributeInspector;
import java.util.logging.Logger;
import junit.framework.Assert;
import jutils.collections.CollectionUtils;
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
        edn.insert(new Test2B1T(false, false, TrisEnum.Odin));
        DecisionRealNode<Test2B1T> la2 = edn.nextHop(new Test2B1T(false, false, TrisEnum.Odin));
        AssertExtensions.assertTypeof(DecisionLeaf.class, la2);
        Assert.assertEquals(la, la2);
        Assert.assertEquals(0x01, CollectionUtils.size(la2));
        edn.insert(new Test2B1T(false, true, TrisEnum.Dva));
        DecisionRealNode<Test2B1T> lb2 = edn.nextHop(new Test2B1T(false, true, TrisEnum.Dva));
        AssertExtensions.assertTypeof(DecisionLeaf.class, lb2);
        Assert.assertEquals(lb, lb2);
        Assert.assertEquals(0x01, CollectionUtils.size(lb2));
        edn.insert(new Test2B1T(true, false, TrisEnum.Tri));
        DecisionRealNode<Test2B1T> lc2 = edn.nextHop(new Test2B1T(true, false, TrisEnum.Tri));
        AssertExtensions.assertTypeof(DecisionLeaf.class, lc2);
        Assert.assertEquals(lc, lc2);
        Assert.assertEquals(0x01, CollectionUtils.size(lc2));
        edn.insert(new Test2B1T(true, true, TrisEnum.Odin));
        DecisionRealNode<Test2B1T> la3 = edn.nextHop(new Test2B1T(true, false, TrisEnum.Odin));
        AssertExtensions.assertTypeof(DecisionLeaf.class, la3);
        Assert.assertEquals(la, la3);
        Assert.assertEquals(0x02, CollectionUtils.size(la3));
    }

    /**
     * Test of replaceChild method, of class EnumerableDecisionNode.
     */
    @Test
    public void testReplaceChild() {
        EnumerableDecisionNode<Test2B1T, ? extends Object> edn = new EnumerableDecisionNode<>(null, oae);
        DecisionRealNode<Test2B1T> la = edn.nextHop(new Test2B1T(false, false, TrisEnum.Odin));
        DecisionRealNode<Test2B1T> lb = edn.nextHop(new Test2B1T(false, false, TrisEnum.Dva));
        DecisionRealNode<Test2B1T> lc = edn.nextHop(new Test2B1T(false, false, TrisEnum.Tri));
        edn.insert(new Test2B1T(false, false, TrisEnum.Odin));
        DecisionRealNode<Test2B1T> la2 = edn.nextHop(new Test2B1T(false, false, TrisEnum.Odin));
        edn.insert(new Test2B1T(false, true, TrisEnum.Dva));
        DecisionRealNode<Test2B1T> lb2 = edn.nextHop(new Test2B1T(false, true, TrisEnum.Dva));
        edn.insert(new Test2B1T(true, false, TrisEnum.Tri));
        DecisionRealNode<Test2B1T> lc2 = edn.nextHop(new Test2B1T(true, false, TrisEnum.Tri));
        edn.insert(new Test2B1T(true, true, TrisEnum.Odin));
        DecisionRealNode<Test2B1T> la3 = edn.nextHop(new Test2B1T(true, false, TrisEnum.Odin));
        DecisionLeafBase<Test2B1T> la4 = new DecisionLeafBase<>(null);
        edn.replaceChild(la, la4);
        DecisionRealNode<Test2B1T> la5 = edn.nextHop(new Test2B1T(false, false, TrisEnum.Odin));
        Assert.assertEquals(la5,la4);
        edn.replaceChild(lb, la);
        DecisionRealNode<Test2B1T> lb5 = edn.nextHop(new Test2B1T(false, false, TrisEnum.Dva));
        Assert.assertEquals(lb5, la);
        edn.replaceChild(lc, lb);
        DecisionRealNode<Test2B1T> lc5 = edn.nextHop(new Test2B1T(false, false, TrisEnum.Tri));
        Assert.assertEquals(lc5, lb);
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
