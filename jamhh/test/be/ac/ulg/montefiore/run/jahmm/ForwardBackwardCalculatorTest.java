/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.ac.ulg.montefiore.run.jahmm;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kommusoft
 */
public class ForwardBackwardCalculatorTest {
    
    public ForwardBackwardCalculatorTest() {
    }

    @Test
    public void testComputeProbability() {
        double expResult = 0.2;
        Hmm hmm = new Hmm(new double[]{0.5,0.5},new double[][] {{0.7,0.3},{0.3,0.7}},new OpdfInteger(0.9,0.1),new OpdfInteger(0.2,0.8));
        double result = ForwardBackwardCalculator.Instance.computeProbability(null,hmm);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testComputeProbability_List_Hmm() {
        
    }

    @Test
    public void testComputeAlpha() {
        
    }

    @Test
    public void testComputeBeta() {
        
    }

    @Test
    public void testComputeAll() {
        
    }
    
}
