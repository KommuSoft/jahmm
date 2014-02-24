/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jahmm.calculators;

import jahmm.Hmm;
import jahmm.observables.ObservationDiscrete;
import jahmm.observables.Opdf;
import jahmm.observables.OpdfDiscrete;
import java.util.Arrays;
import java.util.List;
import junit.framework.Assert;
import jutils.testing.AssertExtensions;
import jutlis.lists.ListArray;
import jutlis.tuples.Tuple3;
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
        Opdf<ObservationDiscrete<Events>> state0 = new OpdfDiscrete<>(Events.class, 0.9d, 0.1d);
        Opdf<ObservationDiscrete<Events>> state1 = new OpdfDiscrete<>(Events.class, 0.2d, 0.8d);
        double[] pi = {0.5d, 0.5d};
        @SuppressWarnings("unchecked")
        Hmm<ObservationDiscrete<Events>> hmm = new Hmm<>(pi, trans, state0, state1);
        @SuppressWarnings("unchecked")
        List<ObservationDiscrete<Events>> sequence = new ListArray<>(new ObservationDiscrete<>(Events.Umbrella), new ObservationDiscrete<>(Events.Umbrella), new ObservationDiscrete<>(Events.NoUmbrella), new ObservationDiscrete<>(Events.Umbrella), new ObservationDiscrete<>(Events.Umbrella));
        Tuple3<double[][], double[][], Double> abp = ForwardBackwardCalculator.Instance.computeAll(hmm, sequence);
        double[][] a = abp.getItem1();
        double[][] b = abp.getItem2();
        double p = abp.getItem3();
        double[] expecteda = {0.5, 0.8182, 0.8834, 0.1907, 0.7308, 0.8673};
        double[] expectedb = {0.6469, 0.5923, 0.3763, 0.6533, 0.6273};
        System.out.println("a:\n"+Arrays.deepToString(a));
        System.out.println("b:\n"+Arrays.deepToString(b));
        for (int t = 0x00; t < expecteda.length; t++) {
            AssertExtensions.assertEquals(expecteda[t], a[t][0x00]);
            AssertExtensions.assertEquals(1.0 - expecteda[t], a[t][0x01]);
        }
        for (int t = 0x00; t < expectedb.length; t++) {
            AssertExtensions.assertEquals(expectedb[t], b[t][0x00]);
            AssertExtensions.assertEquals(1.0 - expectedb[t], b[t][0x01]);
        }
        AssertExtensions.assertEquals(1.0d, b[expectedb.length][0x00]);
        AssertExtensions.assertEquals(1.0d, b[expectedb.length][0x01]);
    }

    public enum Events {

        Umbrella,
        NoUmbrella
    }

}
