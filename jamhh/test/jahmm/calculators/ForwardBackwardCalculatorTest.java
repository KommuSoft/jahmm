/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jahmm.calculators;

import org.junit.Test;

/**
 *
 * @author kommusoft
 */
public class ForwardBackwardCalculatorTest {

    public ForwardBackwardCalculatorTest() {
    }

    /**
     * Test of computeAll method, of class ForwardBackwardCalculator. Based on
     * the Umbrella world of Russell & Norvig 2010 Chapter 15 pp. 566
     */
    @Test
    public void testComputeAll() {
        double[][] trans = {{0.7d, 0.3d}, {0.3d, 0.7d}};
        double[][] exhaust = {{0.9d, 0.1d}, {0.2d, 0.8d}};
        Opdf<> state0 = new OpdfDiscrete<>();
        double[] pi = {0.5d, 0.5d};
        //Hmm hmm = new Hmm();
    }

    public enum UnbrellaEvents {

        Umbrella,
        NoUmbrella
    }

}
