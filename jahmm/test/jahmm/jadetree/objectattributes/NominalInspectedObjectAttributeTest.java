/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jahmm.jadetree.objectattributes;

import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kommusoft
 */
public class NominalInspectedObjectAttributeTest {
    
    public NominalInspectedObjectAttributeTest() {
    }

    /**
     * Test of getPossibleValues method, of class NominalInspectedObjectAttribute.
     */
    @Test
    public void testGetPossibleValues() {
        System.out.println("getPossibleValues");
        NominalInspectedObjectAttribute instance = null;
        Set expResult = null;
        Set result = instance.getPossibleValues();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of evaluate method, of class NominalInspectedObjectAttribute.
     */
    @Test
    public void testEvaluate() {
        System.out.println("evaluate");
        Object source = null;
        NominalInspectedObjectAttribute instance = null;
        Object expResult = null;
        Object result = instance.evaluate(source);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class NominalInspectedObjectAttribute.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        NominalInspectedObjectAttribute instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
