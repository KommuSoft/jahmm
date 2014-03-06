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
public class OrdinalInspectedObjectAttributeTest {
    
    public OrdinalInspectedObjectAttributeTest() {
    }

    /**
     * Test of compareWith method, of class OrdinalInspectedObjectAttribute.
     */
    @Test
    public void testCompareWith() {
        System.out.println("compareWith");
        Object source = null;
        Object target = null;
        OrdinalInspectedObjectAttribute instance = null;
        int expResult = 0;
        int result = instance.compareWith(source, target);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class OrdinalInspectedObjectAttribute.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        OrdinalInspectedObjectAttribute instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of evaluate method, of class OrdinalInspectedObjectAttribute.
     */
    @Test
    public void testEvaluate() {
        System.out.println("evaluate");
        Object x = null;
        OrdinalInspectedObjectAttribute instance = null;
        Object expResult = null;
        Object result = instance.evaluate(x);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compare method, of class OrdinalInspectedObjectAttribute.
     */
    @Test
    public void testCompare() {
        System.out.println("compare");
        Object t1 = null;
        Object t2 = null;
        OrdinalInspectedObjectAttribute instance = null;
        int expResult = 0;
        int result = instance.compare(t1, t2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
