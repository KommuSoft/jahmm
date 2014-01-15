/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulg.montefiore.run.jahmm;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author kommusoft
 */
public class ForwardBackwardCalculatorTest {

    public ForwardBackwardCalculatorTest() {
    }

    @Test
    public void testComputeProbability1() {
        double expResult, result;
        Hmm<ObservationInteger> hmm = new Hmm<>(new double[]{1.0}, new double[][]{{1.0}}, new OpdfInteger(0.9, 0.1));
        expResult = 0.09;
        result = ForwardBackwardCalculator.Instance.computeProbability(hmm, new ObservationInteger(0x00), new ObservationInteger(0x01));
        assertEquals(expResult, result, 1e-6);
        expResult = 0.81;
        result = ForwardBackwardCalculator.Instance.computeProbability(hmm, new ObservationInteger(0x00), new ObservationInteger(0x00));
        assertEquals(expResult, result, 1e-6);
        expResult = 0.09;
        result = ForwardBackwardCalculator.Instance.computeProbability(hmm, new ObservationInteger(0x01), new ObservationInteger(0x00));
        assertEquals(expResult, result, 1e-6);
        expResult = 0.01;
        result = ForwardBackwardCalculator.Instance.computeProbability(hmm, new ObservationInteger(0x01), new ObservationInteger(0x01));
        assertEquals(expResult, result, 1e-6);
    }

    @Test
    public void testComputeProbability2() {
        double expResult, result;
        Hmm<ObservationInteger> hmm = new Hmm<>(new double[]{0.6, 0.4}, new double[][]{{0.7, 0.3}, {0.5, 0.5}}, new OpdfInteger(0.9, 0.1), new OpdfInteger(0.4, 0.6));
        expResult = 0.6 * 0.9 * 0.7 * 0.1 + 0.6 * 0.9 * 0.3 * 0.6 + 0.4 * 0.4 * 0.5 * 0.1 + 0.4 * 0.4 * 0.5 * 0.6;
        result = ForwardBackwardCalculator.Instance.computeProbability(hmm, new ObservationInteger(0x00), new ObservationInteger(0x01));
        assertEquals(expResult, result, 1e-6);
    }

}
