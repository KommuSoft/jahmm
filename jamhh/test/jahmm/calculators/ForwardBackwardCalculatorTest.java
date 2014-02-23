/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jahmm.calculators;

import jahmm.Hmm;
import jahmm.observables.ObservationInteger;
import jahmm.observables.OpdfInteger;
import java.util.EnumSet;
import static junit.framework.Assert.fail;
import jutlis.tuples.Tuple3;
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
    public void testComputeProbability1A() {
        double expResult, result;
        @SuppressWarnings("unchecked")
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
    public void testComputeProbability2A() {
        double expResult, result;
        @SuppressWarnings("unchecked")
        Hmm<ObservationInteger> hmm = new Hmm<>(new double[]{0.6, 0.4}, new double[][]{{0.7, 0.3}, {0.5, 0.5}}, new OpdfInteger(0.9, 0.1), new OpdfInteger(0.4, 0.6));
        expResult = 0.6 * 0.9 * 0.7 * 0.1 + 0.6 * 0.9 * 0.3 * 0.6 + 0.4 * 0.4 * 0.5 * 0.1 + 0.4 * 0.4 * 0.5 * 0.6;
        result = ForwardBackwardCalculator.Instance.computeProbability(hmm, new ObservationInteger(0x00), new ObservationInteger(0x01));
        assertEquals(expResult, result, 1e-6);
    }

    @Test
    public void testComputeProbability1B() {
        double expResult, result;
        @SuppressWarnings("unchecked")
        Hmm<ObservationInteger> hmm = new Hmm<>(new double[]{1.0}, new double[][]{{1.0}}, new OpdfInteger(0.9, 0.1));
        expResult = 0.09;
        result = ForwardBackwardCalculator.Instance.computeProbability(hmm, EnumSet.of(ComputationType.BETA), new ObservationInteger(0x00), new ObservationInteger(0x01));
        assertEquals(expResult, result, 1e-6);
        expResult = 0.81;
        result = ForwardBackwardCalculator.Instance.computeProbability(hmm, EnumSet.of(ComputationType.BETA), new ObservationInteger(0x00), new ObservationInteger(0x00));
        assertEquals(expResult, result, 1e-6);
        expResult = 0.09;
        result = ForwardBackwardCalculator.Instance.computeProbability(hmm, EnumSet.of(ComputationType.BETA), new ObservationInteger(0x01), new ObservationInteger(0x00));
        assertEquals(expResult, result, 1e-6);
        expResult = 0.01;
        result = ForwardBackwardCalculator.Instance.computeProbability(hmm, EnumSet.of(ComputationType.BETA), new ObservationInteger(0x01), new ObservationInteger(0x01));
        assertEquals(expResult, result, 1e-6);
    }

    @Test
    public void testComputeProbability2B() {
        double expResult, result;
        @SuppressWarnings("unchecked")
        Hmm<ObservationInteger> hmm = new Hmm<>(new double[]{0.6, 0.4}, new double[][]{{0.7, 0.3}, {0.5, 0.5}}, new OpdfInteger(0.9, 0.1), new OpdfInteger(0.4, 0.6));
        expResult = 0.6 * 0.9 * 0.7 * 0.1 + 0.6 * 0.9 * 0.3 * 0.6 + 0.4 * 0.4 * 0.5 * 0.1 + 0.4 * 0.4 * 0.5 * 0.6;
        result = ForwardBackwardCalculator.Instance.computeProbability(hmm, EnumSet.of(ComputationType.BETA), new ObservationInteger(0x00), new ObservationInteger(0x01));
        assertEquals(expResult, result, 1e-6);
    }

    @Test
    public void testComputeProbability1AB() {
        double expResult, result;
        @SuppressWarnings("unchecked")
        Hmm<ObservationInteger> hmm = new Hmm<>(new double[]{1.0}, new double[][]{{1.0}}, new OpdfInteger(0.9, 0.1));
        expResult = 0.09;
        result = ForwardBackwardCalculator.Instance.computeProbability(hmm, EnumSet.of(ComputationType.ALPHA, ComputationType.BETA), new ObservationInteger(0x00), new ObservationInteger(0x01));
        assertEquals(expResult, result, 1e-6);
        expResult = 0.81;
        result = ForwardBackwardCalculator.Instance.computeProbability(hmm, EnumSet.of(ComputationType.ALPHA, ComputationType.BETA), new ObservationInteger(0x00), new ObservationInteger(0x00));
        assertEquals(expResult, result, 1e-6);
        expResult = 0.09;
        result = ForwardBackwardCalculator.Instance.computeProbability(hmm, EnumSet.of(ComputationType.ALPHA, ComputationType.BETA), new ObservationInteger(0x01), new ObservationInteger(0x00));
        assertEquals(expResult, result, 1e-6);
        expResult = 0.01;
        result = ForwardBackwardCalculator.Instance.computeProbability(hmm, EnumSet.of(ComputationType.ALPHA, ComputationType.BETA), new ObservationInteger(0x01), new ObservationInteger(0x01));
        assertEquals(expResult, result, 1e-6);
    }

    @Test
    public void testComputeProbability2AB() {
        double expResult, result;
        @SuppressWarnings("unchecked")
        Hmm<ObservationInteger> hmm = new Hmm<>(new double[]{0.6, 0.4}, new double[][]{{0.7, 0.3}, {0.5, 0.5}}, new OpdfInteger(0.9, 0.1), new OpdfInteger(0.4, 0.6));
        expResult = 0.6 * 0.9 * 0.7 * 0.1 + 0.6 * 0.9 * 0.3 * 0.6 + 0.4 * 0.4 * 0.5 * 0.1 + 0.4 * 0.4 * 0.5 * 0.6;
        result = ForwardBackwardCalculator.Instance.computeProbability(hmm, EnumSet.of(ComputationType.ALPHA, ComputationType.BETA), new ObservationInteger(0x00), new ObservationInteger(0x01));
        assertEquals(expResult, result, 1e-6);
    }

    /**
     * Test of computeProbability method, of class ForwardBackwardCalculator.
     */
    @Test
    public void testComputeProbability_5args() {
        System.out.println("computeProbability");
        ForwardBackwardCalculator instance = new ForwardBackwardCalculator();
        double expResult = 0.0;
        double result = instance.computeProbability(null);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of computeProbability method, of class ForwardBackwardCalculator.
     */
    @Test
    public void testComputeProbability_3args() {
        System.out.println("computeProbability");
        ForwardBackwardCalculator instance = new ForwardBackwardCalculator();
        double expResult = 0.0;
        double result = instance.computeProbability(null);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of computeAlpha method, of class ForwardBackwardCalculator.
     */
    @Test
    public void testComputeAlpha() {
        System.out.println("computeAlpha");
        ForwardBackwardCalculator instance = new ForwardBackwardCalculator();
        double[][] expResult = null;
        double[][] result = instance.computeAlpha(null);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of computeBeta method, of class ForwardBackwardCalculator.
     */
    @Test
    public void testComputeBeta() {
        System.out.println("computeBeta");
        ForwardBackwardCalculator instance = new ForwardBackwardCalculator();
        double[][] expResult = null;
        double[][] result = instance.computeBeta(null);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of computeAll method, of class ForwardBackwardCalculator.
     */
    @Test
    public void testComputeAll() {
        System.out.println("computeAll");
        ForwardBackwardCalculator instance = new ForwardBackwardCalculator();
        Tuple3 expResult = null;
        Tuple3 result = instance.computeAll(null);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
