/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jahmm.jadetree.objectattributes;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kommusoft
 */
public class FloatInspectedContinuObjectAttributeTest {
    
    public FloatInspectedContinuObjectAttributeTest() {
    }

    /**
     * Test of getName method, of class FloatInspectedContinuObjectAttribute.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        FloatInspectedContinuObjectAttribute instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of evaluate method, of class FloatInspectedContinuObjectAttribute.
     */
    @Test
    public void testEvaluate() {
        System.out.println("evaluate");
        Object x = null;
        FloatInspectedContinuObjectAttribute instance = null;
        Float expResult = null;
        Float result = instance.evaluate(x);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
