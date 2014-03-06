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
public class ContinuObjectAttributePredicateTest {
    
    public ContinuObjectAttributePredicateTest() {
    }

    /**
     * Test of evaluate method, of class ContinuObjectAttributePredicate.
     */
    @Test
    public void testEvaluate() {
        System.out.println("evaluate");
        Object x = null;
        ContinuObjectAttributePredicate instance = null;
        Boolean expResult = null;
        Boolean result = instance.evaluate(x);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
