/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jahmm.jadetree;

import jahmm.jadetree.objectattributes.OrdinalObjectAttribute;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kommusoft
 */
public class OrdinalTestDecisionNodeTest {
    
    public OrdinalTestDecisionNodeTest() {
    }

    /**
     * Test of test method, of class OrdinalTestDecisionNode.
     */
    @Test
    public void testTest() {
        System.out.println("test");
        Object source = null;
        OrdinalTestDecisionNode instance = null;
        boolean expResult = false;
        boolean result = instance.test(source);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class OrdinalTestDecisionNode.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        OrdinalTestDecisionNode instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOrdinalArgument method, of class OrdinalTestDecisionNode.
     */
    @Test
    public void testGetOrdinalArgument() {
        System.out.println("getOrdinalArgument");
        OrdinalTestDecisionNode instance = null;
        OrdinalObjectAttribute expResult = null;
        OrdinalObjectAttribute result = instance.getOrdinalArgument();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getState method, of class OrdinalTestDecisionNode.
     */
    @Test
    public void testGetState() {
        System.out.println("getState");
        OrdinalTestDecisionNode instance = null;
        Object expResult = null;
        Object result = instance.getState();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
