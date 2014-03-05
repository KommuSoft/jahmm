/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jahmm.jadetree;

import jutlis.algebra.Predicate;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kommusoft
 */
public class PredicateDecisionNodeTest {
    
    public PredicateDecisionNodeTest() {
    }

    /**
     * Test of toString method, of class PredicateDecisionNode.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        PredicateDecisionNode instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of test method, of class PredicateDecisionNode.
     */
    @Test
    public void testTest() {
        System.out.println("test");
        Object source = null;
        PredicateDecisionNode instance = null;
        boolean expResult = false;
        boolean result = instance.test(source);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPredicate method, of class PredicateDecisionNode.
     */
    @Test
    public void testGetPredicate() {
        System.out.println("getPredicate");
        PredicateDecisionNode instance = null;
        Predicate expResult = null;
        Predicate result = instance.getPredicate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
