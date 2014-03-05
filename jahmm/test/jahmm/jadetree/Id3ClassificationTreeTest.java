/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jahmm.jadetree;

import jahmm.jadetree.objectattributes.NominalObjectAttribute;
import jahmm.jadetree.objectattributes.ObjectAttribute;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kommusoft
 */
public class Id3ClassificationTreeTest {
    
    public Id3ClassificationTreeTest() {
    }

    /**
     * Test of getParent method, of class Id3ClassificationTree.
     */
    @Test
    public void testGetParent() {
        System.out.println("getParent");
        Id3ClassificationTree instance = null;
        DecisionInode expResult = null;
        DecisionInode result = instance.getParent();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTree method, of class Id3ClassificationTree.
     */
    @Test
    public void testGetTree() {
        System.out.println("getTree");
        Id3ClassificationTree instance = null;
        DecisionTree expResult = null;
        DecisionTree result = instance.getTree();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reduce method, of class Id3ClassificationTree.
     */
    @Test
    public void testReduce() {
        System.out.println("reduce");
        Id3ClassificationTree instance = null;
        instance.reduce();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of expand method, of class Id3ClassificationTree.
     */
    @Test
    public void testExpand() {
        System.out.println("expand");
        Id3ClassificationTree instance = null;
        instance.expand();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of expandScore method, of class Id3ClassificationTree.
     */
    @Test
    public void testExpandScore() {
        System.out.println("expandScore");
        Id3ClassificationTree instance = null;
        double expResult = 0.0;
        double result = instance.expandScore();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reduceScore method, of class Id3ClassificationTree.
     */
    @Test
    public void testReduceScore() {
        System.out.println("reduceScore");
        Id3ClassificationTree instance = null;
        double expResult = 0.0;
        double result = instance.reduceScore();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insert method, of class Id3ClassificationTree.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        Object source = null;
        Id3ClassificationTree instance = null;
        instance.insert(source);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getChildren method, of class Id3ClassificationTree.
     */
    @Test
    public void testGetChildren() {
        System.out.println("getChildren");
        Id3ClassificationTree instance = null;
        Iterable<DecisionRealNode<TSource>> expResult = null;
        Iterable<DecisionRealNode<TSource>> result = instance.getChildren();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRoot method, of class Id3ClassificationTree.
     */
    @Test
    public void testGetRoot() {
        System.out.println("getRoot");
        Id3ClassificationTree instance = null;
        DecisionRealNode expResult = null;
        DecisionRealNode result = instance.getRoot();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of replaceChild method, of class Id3ClassificationTree.
     */
    @Test
    public void testReplaceChild() {
        System.out.println("replaceChild");
        Id3ClassificationTree instance = null;
        instance.replaceChild(null);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSourceAttributes method, of class Id3ClassificationTree.
     */
    @Test
    public void testGetSourceAttributes() {
        System.out.println("getSourceAttributes");
        Id3ClassificationTree instance = null;
        List<ObjectAttribute<TSource, Object>> expResult = null;
        List<ObjectAttribute<TSource, Object>> result = instance.getSourceAttributes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addSourceAttribute method, of class Id3ClassificationTree.
     */
    @Test
    public void testAddSourceAttribute() {
        System.out.println("addSourceAttribute");
        Id3ClassificationTree instance = null;
        instance.addSourceAttribute(null);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeSourceAttribute method, of class Id3ClassificationTree.
     */
    @Test
    public void testRemoveSourceAttribute() {
        System.out.println("removeSourceAttribute");
        Id3ClassificationTree instance = null;
        instance.removeSourceAttribute(null);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTargetAttribute method, of class Id3ClassificationTree.
     */
    @Test
    public void testGetTargetAttribute() {
        System.out.println("getTargetAttribute");
        Id3ClassificationTree instance = null;
        NominalObjectAttribute expResult = null;
        NominalObjectAttribute result = instance.getTargetAttribute();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkTrade method, of class Id3ClassificationTree.
     */
    @Test
    public void testCheckTrade() {
        System.out.println("checkTrade");
        Id3ClassificationTree instance = null;
        boolean expResult = false;
        boolean result = instance.checkTrade();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of trade method, of class Id3ClassificationTree.
     */
    @Test
    public void testTrade() {
        System.out.println("trade");
        Id3ClassificationTree instance = null;
        instance.trade();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of tradeExpand method, of class Id3ClassificationTree.
     */
    @Test
    public void testTradeExpand() {
        System.out.println("tradeExpand");
        Id3ClassificationTree instance = null;
        instance.tradeExpand();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reduceMemory method, of class Id3ClassificationTree.
     */
    @Test
    public void testReduceMemory() {
        System.out.println("reduceMemory");
        Id3ClassificationTree instance = null;
        instance.reduceMemory();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of classify method, of class Id3ClassificationTree.
     */
    @Test
    public void testClassify() {
        System.out.println("classify");
        Object element = null;
        Id3ClassificationTree instance = null;
        Object expResult = null;
        Object result = instance.classify(element);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of classifyInsert method, of class Id3ClassificationTree.
     */
    @Test
    public void testClassifyInsert() {
        System.out.println("classifyInsert");
        Object element = null;
        Id3ClassificationTree instance = null;
        Object expResult = null;
        Object result = instance.classifyInsert(element);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
