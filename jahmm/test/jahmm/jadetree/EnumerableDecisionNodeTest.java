/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jahmm.jadetree;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kommusoft
 */
public class EnumerableDecisionNodeTest {
    
    public EnumerableDecisionNodeTest() {
    }

    /**
     * Test of nextHop method, of class EnumerableDecisionNode.
     */
    @Test
    public void testNextHop() {
        System.out.println("nextHop");
        Object source = null;
        EnumerableDecisionNode instance = null;
        DecisionRealNode expResult = null;
        DecisionRealNode result = instance.nextHop(source);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of replaceChild method, of class EnumerableDecisionNode.
     */
    @Test
    public void testReplaceChild() {
        System.out.println("replaceChild");
        EnumerableDecisionNode instance = null;
        instance.replaceChild(null);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getChildren method, of class EnumerableDecisionNode.
     */
    @Test
    public void testGetChildren() {
        System.out.println("getChildren");
        EnumerableDecisionNode instance = null;
        Iterable<DecisionRealNode<TSource>> expResult = null;
        Iterable<DecisionRealNode<TSource>> result = instance.getChildren();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class EnumerableDecisionNode.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        EnumerableDecisionNode instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
