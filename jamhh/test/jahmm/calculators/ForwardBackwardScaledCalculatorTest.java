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
public class ForwardBackwardScaledCalculatorTest {
    
    public ForwardBackwardScaledCalculatorTest() {
    }

    /**
     * Test of computeProbability method, of class ForwardBackwardScaledCalculator.
     */
    @Test
    public void testComputeProbability() {
        System.out.println("computeProbability");
        ForwardBackwardScaledCalculator instance = new ForwardBackwardScaledCalculator();
        double expResult = 0.0;
        double result = instance.computeProbability(null);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of computeAlpha method, of class ForwardBackwardScaledCalculator.
     */
    @Test
    public void testComputeAlpha() {
        System.out.println("computeAlpha");
        ForwardBackwardScaledCalculator instance = new ForwardBackwardScaledCalculator();
        double[][] expResult = null;
        double[][] result = instance.computeAlpha(null);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of computeBeta method, of class ForwardBackwardScaledCalculator.
     */
    @Test
    public void testComputeBeta() {
        System.out.println("computeBeta");
        ForwardBackwardScaledCalculator instance = new ForwardBackwardScaledCalculator();
        double[][] expResult = null;
        double[][] result = instance.computeBeta(null);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
