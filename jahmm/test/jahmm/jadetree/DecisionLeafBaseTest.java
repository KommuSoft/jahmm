package jahmm.jadetree;

import jahmm.jadetree.foo.TestLeapYear;
import jahmm.jadetree.objectattributes.NominalObjectAttribute;
import jahmm.jadetree.objectattributes.ObjectAttribute;
import jahmm.jadetree.objectattributes.ObjectAttributeInspector;
import junit.framework.Assert;
import jutils.collections.CollectionUtils;
import jutils.testing.AssertExtensions;
import jutlis.lists.ListArray;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kommusoft
 */
public class DecisionLeafBaseTest {

    public DecisionLeafBaseTest() {
    }

    /**
     * Test of insert method, of class DecisionLeafBase.
     */
    @Test
    public void testInsertStoredSources() {
        Object ob1 = 0.05d;
        Object ob2 = "Foo";
        Object ob3 = null;
        Object ob4 = "Bar";
        Object ob5 = -0x09;
        Object ob6 = Float.NaN;
        Iterable<Object> expected = null;
        DecisionLeafBase<Object> dlb = new DecisionLeafBase<>(null);
        Assert.assertEquals(null, dlb.getParent());
        Assert.assertEquals(null, dlb.getTree());
        Assert.assertEquals(0x00, CollectionUtils.size(dlb.getStoredSources()));
        Assert.assertEquals(0x01, CollectionUtils.size(dlb.getPartitionedStoredSources()));
        expected = new ListArray<>();
        AssertExtensions.assertEqualsOrderedDeep(expected, dlb.getStoredSources());
        AssertExtensions.assertEqualsOrderedDeep(new ListArray<>(expected), dlb.getPartitionedStoredSources());
        dlb.insert(ob1);
        Assert.assertEquals(0x01, CollectionUtils.size(dlb.getStoredSources()));
        Assert.assertEquals(0x01, CollectionUtils.size(dlb.getPartitionedStoredSources()));
        expected = new ListArray<>(ob1);
        AssertExtensions.assertEqualsOrderedDeep(expected, dlb.getStoredSources());
        AssertExtensions.assertEqualsOrderedDeep(new ListArray<>(expected), dlb.getPartitionedStoredSources());
        dlb.insert(ob2);
        Assert.assertEquals(0x02, CollectionUtils.size(dlb.getStoredSources()));
        Assert.assertEquals(0x01, CollectionUtils.size(dlb.getPartitionedStoredSources()));
        expected = new ListArray<>(ob1, ob2);
        AssertExtensions.assertEqualsOrderedDeep(expected, dlb.getStoredSources());
        AssertExtensions.assertEqualsOrderedDeep(new ListArray<>(expected), dlb.getPartitionedStoredSources());
        dlb.insert(ob3);
        Assert.assertEquals(0x03, CollectionUtils.size(dlb.getStoredSources()));
        Assert.assertEquals(0x01, CollectionUtils.size(dlb.getPartitionedStoredSources()));
        expected = new ListArray<>(ob1, ob2, ob3);
        AssertExtensions.assertEqualsOrderedDeep(expected, dlb.getStoredSources());
        AssertExtensions.assertEqualsOrderedDeep(new ListArray<>(expected), dlb.getPartitionedStoredSources());
        dlb.insert(ob4);
        Assert.assertEquals(0x04, CollectionUtils.size(dlb.getStoredSources()));
        Assert.assertEquals(0x01, CollectionUtils.size(dlb.getPartitionedStoredSources()));
        AssertExtensions.assertEqualsOrdered(new ListArray<>(ob1, ob2, ob3, ob4), dlb.getStoredSources());
        expected = new ListArray<>(ob1, ob2, ob3, ob4);
        AssertExtensions.assertEqualsOrderedDeep(expected, dlb.getStoredSources());
        AssertExtensions.assertEqualsOrderedDeep(new ListArray<>(expected), dlb.getPartitionedStoredSources());
        dlb.insert(ob5);
        Assert.assertEquals(0x05, CollectionUtils.size(dlb.getStoredSources()));
        Assert.assertEquals(0x01, CollectionUtils.size(dlb.getPartitionedStoredSources()));
        AssertExtensions.assertEqualsOrdered(new ListArray<>(ob1, ob2, ob3, ob4, ob5), dlb.getStoredSources());
        expected = new ListArray<>(ob1, ob2, ob3, ob4, ob5);
        AssertExtensions.assertEqualsOrderedDeep(expected, dlb.getStoredSources());
        AssertExtensions.assertEqualsOrderedDeep(new ListArray<>(expected), dlb.getPartitionedStoredSources());
        dlb.insert(ob6);
        Assert.assertEquals(0x06, CollectionUtils.size(dlb.getStoredSources()));
        Assert.assertEquals(0x01, CollectionUtils.size(dlb.getPartitionedStoredSources()));
        expected = new ListArray<>(ob1, ob2, ob3, ob4, ob5, ob6);
        AssertExtensions.assertEqualsOrderedDeep(expected, dlb.getStoredSources());
        AssertExtensions.assertEqualsOrderedDeep(new ListArray<>(expected), dlb.getPartitionedStoredSources());
        dlb.insert(ob5);
        Assert.assertEquals(0x07, CollectionUtils.size(dlb.getStoredSources()));
        Assert.assertEquals(0x01, CollectionUtils.size(dlb.getPartitionedStoredSources()));
        expected = new ListArray<>(ob1, ob2, ob3, ob4, ob5, ob6, ob5);
        AssertExtensions.assertEqualsOrderedDeep(expected, dlb.getStoredSources());
        AssertExtensions.assertEqualsOrderedDeep(new ListArray<>(expected), dlb.getPartitionedStoredSources());
        dlb.insert(ob4);
        Assert.assertEquals(0x08, CollectionUtils.size(dlb.getStoredSources()));
        Assert.assertEquals(0x01, CollectionUtils.size(dlb.getPartitionedStoredSources()));
        expected = new ListArray<>(ob1, ob2, ob3, ob4, ob5, ob6, ob5, ob4);
        AssertExtensions.assertEqualsOrderedDeep(expected, dlb.getStoredSources());
        AssertExtensions.assertEqualsOrderedDeep(new ListArray<>(expected), dlb.getPartitionedStoredSources());
        dlb.insert(ob3);
        Assert.assertEquals(0x09, CollectionUtils.size(dlb.getStoredSources()));
        Assert.assertEquals(0x01, CollectionUtils.size(dlb.getPartitionedStoredSources()));
        expected = new ListArray<>(ob1, ob2, ob3, ob4, ob5, ob6, ob5, ob4, ob3);
        AssertExtensions.assertEqualsOrderedDeep(expected, dlb.getStoredSources());
        AssertExtensions.assertEqualsOrderedDeep(new ListArray<>(expected), dlb.getPartitionedStoredSources());
        dlb.insert(ob2);
        Assert.assertEquals(0x0a, CollectionUtils.size(dlb.getStoredSources()));
        Assert.assertEquals(0x01, CollectionUtils.size(dlb.getPartitionedStoredSources()));
        expected = new ListArray<>(ob1, ob2, ob3, ob4, ob5, ob6, ob5, ob4, ob3, ob2);
        AssertExtensions.assertEqualsOrderedDeep(expected, dlb.getStoredSources());
        AssertExtensions.assertEqualsOrderedDeep(new ListArray<>(expected), dlb.getPartitionedStoredSources());
        dlb.insert(ob1);
        Assert.assertEquals(0x0b, CollectionUtils.size(dlb.getStoredSources()));
        Assert.assertEquals(0x01, CollectionUtils.size(dlb.getPartitionedStoredSources()));
        expected = new ListArray<>(ob1, ob2, ob3, ob4, ob5, ob6, ob5, ob4, ob3, ob2, ob1);
        AssertExtensions.assertEqualsOrderedDeep(expected, dlb.getStoredSources());
        AssertExtensions.assertEqualsOrderedDeep(new ListArray<>(expected), dlb.getPartitionedStoredSources());
    }

    /**
     * Test of isLeaf method, of class DecisionLeafBase.
     */
    @Test
    public void testIsLeaf() {
        DecisionLeafBase<Object> dlb = new DecisionLeafBase<>(null);
        Assert.assertTrue(dlb.isLeaf());
    }

    /**
     * Test of recalcMaximumExpandLeaf method, of class DecisionLeafBase.
     */
    @Test
    public void testRecalcMaximumExpandLeaf() {
        Object ob1 = 0.05d;
        Object ob2 = "Foo";
        Object ob3 = null;
        Object ob4 = "Bar";
        Object ob5 = -0x09;
        Object ob6 = Float.NaN;
        DecisionLeafBase<Object> dlb = new DecisionLeafBase<>(null);
        Assert.assertEquals(dlb, dlb.recalcMaximumExpandLeaf());
        dlb.insert(ob1);
        Assert.assertEquals(dlb, dlb.recalcMaximumExpandLeaf());
        dlb.insert(ob2);
        Assert.assertEquals(dlb, dlb.recalcMaximumExpandLeaf());
        dlb.insert(ob3);
        Assert.assertEquals(dlb, dlb.recalcMaximumExpandLeaf());
        dlb.insert(ob4);
        Assert.assertEquals(dlb, dlb.recalcMaximumExpandLeaf());
    }

    /**
     * Test of recalcMaximumReduceInode method, of class DecisionLeafBase.
     */
    @Test
    public void testRecalcMaximumReduceInode() {
        Object ob1 = 0.05d;
        Object ob2 = "Foo";
        Object ob3 = null;
        Object ob4 = "Bar";
        Object ob5 = -0x09;
        Object ob6 = Float.NaN;
        DecisionLeafBase<Object> dlb = new DecisionLeafBase<>(null);
        Assert.assertEquals(null, dlb.recalcMaximumReduceInode());
        dlb.insert(ob1);
        Assert.assertEquals(null, dlb.recalcMaximumReduceInode());
        dlb.insert(ob2);
        Assert.assertEquals(null, dlb.recalcMaximumReduceInode());
        dlb.insert(ob3);
        Assert.assertEquals(null, dlb.recalcMaximumReduceInode());
        dlb.insert(ob4);
        Assert.assertEquals(null, dlb.recalcMaximumReduceInode());
        dlb.insert(ob5);
        Assert.assertEquals(null, dlb.recalcMaximumReduceInode());
        dlb.insert(ob6);
        Assert.assertEquals(null, dlb.recalcMaximumReduceInode());
        dlb.insert(ob5);
        Assert.assertEquals(null, dlb.recalcMaximumReduceInode());
        dlb.insert(ob4);
    }

    /**
     * Test of expandThis method, of class DecisionLeafBase.
     */
    @Test
    public void testExpandThis() {
        TestLeapYear[] yrs = new TestLeapYear[0x400];
        for(int i = 0x00; i < 0x400; i++) {
            yrs[i] = new TestLeapYear(i);
        }
        @SuppressWarnings("unchecked")
        NominalObjectAttribute<TestLeapYear,? extends Object> oa = (NominalObjectAttribute<TestLeapYear,? extends Object>) ObjectAttributeInspector.inspect(TestLeapYear.class, "leap");
        Iterable<ObjectAttribute<TestLeapYear,? extends Object>> oas = ObjectAttributeInspector.inspect(TestLeapYear.class, "div2","div4","div8","div16","div100","div200","div500","div1000");
        Id3ClassificationTree<TestLeapYear> tree = new Id3ClassificationTree<TestLeapYear>(oa);
        tree.addSourceAttribute(oas);
        AssertExtensions.assertTypeof(DecisionLeafBase.class, tree.getRoot());
        DecisionLeafBase<TestLeapYear> root = (DecisionLeafBase<TestLeapYear>) tree.getRoot();
        Assert.assertEquals(tree, root.getParent());
        Assert.assertEquals(tree, root.getTree());
        tree.insert(null);
    }

    /**
     * Test of reduceScore method, of class DecisionLeafBase.
     */
    @Test
    public void testReduceScore() {
        fail("The test case is a prototype.");
    }

    /**
     * Test of isDirty method, of class DecisionLeafBase.
     */
    @Test
    public void testIsDirty() {
        fail("The test case is a prototype.");
    }

    /**
     * Test of makeDirty method, of class DecisionLeafBase.
     */
    @Test
    public void testMakeDirty() {
        Object ob1 = 0.05d;
        Object ob2 = "Foo";
        Object ob3 = null;
        Object ob4 = "Bar";
        Object ob5 = -0x09;
        Object ob6 = Float.NaN;
        DecisionLeafBase<Object> dlb = new DecisionLeafBase<>(null);
        dlb.insert(ob1);
        Assert.assertTrue(dlb.isDirty());
        dlb.insert(ob2);
        Assert.assertTrue(dlb.isDirty());
        dlb.insert(ob3);
        Assert.assertTrue(dlb.isDirty());
        dlb.insert(ob4);
        Assert.assertTrue(dlb.isDirty());
        dlb.insert(ob5);
        Assert.assertTrue(dlb.isDirty());
        dlb.insert(ob6);
        Assert.assertTrue(dlb.isDirty());
        dlb.insert(ob5);
        Assert.assertTrue(dlb.isDirty());
        dlb.insert(ob4);
        Assert.assertTrue(dlb.isDirty());
    }

    /**
     * Test of expandScore method, of class DecisionLeafBase.
     */
    @Test
    public void testExpandScore() {
        fail("The test case is a prototype.");
    }

}
