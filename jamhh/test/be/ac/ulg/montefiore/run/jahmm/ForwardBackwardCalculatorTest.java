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
        double expResult = 0.09;
        Hmm hmm = new Hmm(new double[]{1.0}, new double[][]{{1.0}}, new OpdfInteger(0.9, 0.1));
        double result = ForwardBackwardCalculator.Instance.computeProbability(hmm,new ObservationInteger(0x00),new ObservationInteger(0x01));
        assertEquals(expResult, result, 0.0);
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
