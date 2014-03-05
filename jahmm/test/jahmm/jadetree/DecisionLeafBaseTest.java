/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jahmm.jadetree;

import java.sql.Date;
import junit.framework.Assert;
import jutils.collections.CollectionUtils;
import jutils.iterators.SingleIterable;
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
     * Test of expandScore method, of class DecisionLeafBase.
     */
    @Test
    public void testExpandScore() {
        System.out.println("expandScore");
        DecisionLeafBase instance = null;
        double expResult = 0.0;
        double result = instance.expandScore();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insert method, of class DecisionLeafBase.
     */
    @Test
    public void testInsert() {
        Object ob1 = 0.05d;
        Object ob2 = "Foo";
        Object ob3 = null;
        Object ob4 = "Bar";
        Object ob5 = -0x09;
        Object ob6 = Float.NaN;
        DecisionLeafBase<Object> dlb = new DecisionLeafBase<>(null);
        Assert.assertEquals(null, dlb.getParent());
        Assert.assertEquals(null, dlb.getTree());
        Assert.assertEquals(0x00, CollectionUtils.size(dlb.getStoredSources()));
        Assert.assertEquals(0x01, CollectionUtils.size(dlb.getPartitionedStoredSources()));
        dlb.insert(ob1);
        Assert.assertEquals(0x01, CollectionUtils.size(dlb.getStoredSources()));
        Assert.assertEquals(0x01, CollectionUtils.size(dlb.getPartitionedStoredSources()));
        AssertExtensions.assertEqualsOrdered(new SingleIterable<>(ob1), dlb.getStoredSources());
        dlb.insert(ob2);
        Assert.assertEquals(0x02, CollectionUtils.size(dlb.getStoredSources()));
        Assert.assertEquals(0x01, CollectionUtils.size(dlb.getPartitionedStoredSources()));
        AssertExtensions.assertEqualsOrdered(new ListArray<>(ob1, ob2), dlb.getStoredSources());
        dlb.insert(ob3);
        Assert.assertEquals(0x03, CollectionUtils.size(dlb.getStoredSources()));
        Assert.assertEquals(0x01, CollectionUtils.size(dlb.getPartitionedStoredSources()));
        AssertExtensions.assertEqualsOrdered(new ListArray<>(ob1, ob2, ob3), dlb.getStoredSources());
        dlb.insert(ob4);
        Assert.assertEquals(0x04, CollectionUtils.size(dlb.getStoredSources()));
        Assert.assertEquals(0x01, CollectionUtils.size(dlb.getPartitionedStoredSources()));
        AssertExtensions.assertEqualsOrdered(new ListArray<>(ob1, ob2, ob3, ob4), dlb.getStoredSources());
        dlb.insert(ob5);
        Assert.assertEquals(0x05, CollectionUtils.size(dlb.getStoredSources()));
        Assert.assertEquals(0x01, CollectionUtils.size(dlb.getPartitionedStoredSources()));
        AssertExtensions.assertEqualsOrdered(new ListArray<>(ob1, ob2, ob3, ob4, ob5), dlb.getStoredSources());
        dlb.insert(ob6);
        Assert.assertEquals(0x06, CollectionUtils.size(dlb.getStoredSources()));
        Assert.assertEquals(0x01, CollectionUtils.size(dlb.getPartitionedStoredSources()));
        AssertExtensions.assertEqualsOrdered(new ListArray<>(ob1, ob2, ob3, ob4, ob5, ob6), dlb.getStoredSources());
    }

    /**
     * Test of isLeaf method, of class DecisionLeafBase.
     */
    @Test
    public void testIsLeaf() {
        System.out.println("isLeaf");
        DecisionLeafBase instance = null;
        boolean expResult = false;
        boolean result = instance.isLeaf();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isDirty method, of class DecisionLeafBase.
     */
    @Test
    public void testIsDirty() {
        System.out.println("isDirty");
        DecisionLeafBase instance = null;
        boolean expResult = false;
        boolean result = instance.isDirty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of makeDirty method, of class DecisionLeafBase.
     */
    @Test
    public void testMakeDirty() {
        System.out.println("makeDirty");
        DecisionLeafBase instance = null;
        instance.makeDirty();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of recalcMaximumExpandLeaf method, of class DecisionLeafBase.
     */
    @Test
    public void testRecalcMaximumExpandLeaf() {
        System.out.println("recalcMaximumExpandLeaf");
        DecisionLeafBase instance = null;
        DecisionLeaf expResult = null;
        DecisionLeaf result = instance.recalcMaximumExpandLeaf();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of recalcMaximumReduceInode method, of class DecisionLeafBase.
     */
    @Test
    public void testRecalcMaximumReduceInode() {
        System.out.println("recalcMaximumReduceInode");
        DecisionLeafBase instance = null;
        DecisionRealInode expResult = null;
        DecisionRealInode result = instance.recalcMaximumReduceInode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStoredSources method, of class DecisionLeafBase.
     */
    @Test
    public void testGetStoredSources() {
        System.out.println("getStoredSources");
        DecisionLeafBase instance = null;
        Iterable expResult = null;
        Iterable result = instance.getStoredSources();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPartitionedStoredSources method, of class DecisionLeafBase.
     */
    @Test
    public void testGetPartitionedStoredSources() {
         fail("The test case is a prototype.");
    }

    /**
     * Test of expandThis method, of class DecisionLeafBase.
     */
    @Test
    public void testExpandThis() {
        System.out.println("expandThis");
        DecisionLeafBase instance = null;
        instance.expandThis();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reduceScore method, of class DecisionLeafBase.
     */
    @Test
    public void testReduceScore() {
        System.out.println("reduceScore");
        DecisionLeafBase instance = null;
        double expResult = 0.0;
        double result = instance.reduceScore();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
