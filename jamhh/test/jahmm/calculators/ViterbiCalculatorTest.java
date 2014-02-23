/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jahmm.calculators;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kommusoft
 */
public class ViterbiCalculatorTest {
    
    public ViterbiCalculatorTest() {
    }

    /**
     * Test of lnProbability method, of class ViterbiCalculator.
     */
    @Test
    public void testLnProbability() {
        System.out.println("lnProbability");
        ViterbiCalculator instance = null;
        double expResult = 0.0;
        double result = instance.lnProbability();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stateSequence method, of class ViterbiCalculator.
     */
    @Test
    public void testStateSequence() {
        System.out.println("stateSequence");
        ViterbiCalculator instance = null;
        int[] expResult = null;
        int[] result = instance.stateSequence();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
