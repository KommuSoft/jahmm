package jahmm.calculators;

import jahmm.RegularHmmBase;
import jahmm.observables.ObservationDiscrete;
import jahmm.observables.Opdf;
import jahmm.observables.OpdfDiscrete;
import java.util.ArrayList;
import java.util.List;
import jutils.probability.ProbabilityUtils;
import jutils.testing.AssertExtensions;
import jutlis.lists.ListArray;
import jutlis.tuples.Tuple3;
import org.junit.Test;
import utils.TestParameters;

/**
 *
 * @author kommusoft
 */
public class ForwardBackwardScaledCalculatorTest {

    public ForwardBackwardScaledCalculatorTest() {
    }

    /**
     * Test of computeAll method, of class ForwardBackwardScaledCalculator.
     * Based on the Umbrella world of Russell & Norvig 2010 Chapter 15 pp. 566
     */
    @Test
    public void testComputeAll() {
        double[][] trans = {{0.7d, 0.3d}, {0.3d, 0.7d}};
        double[][] exhaust = {{0.9d, 0.1d}, {0.2d, 0.8d}};
        Opdf<ObservationDiscrete<Events>> state0 = new OpdfDiscrete<>(Events.class, exhaust[0x00]);
        Opdf<ObservationDiscrete<Events>> state1 = new OpdfDiscrete<>(Events.class, exhaust[0x01]);
        double[] pi = {0.5d, 0.5d};
        @SuppressWarnings("unchecked")
        RegularHmmBase<ObservationDiscrete<Events>> hmm = new RegularHmmBase<>(pi, trans, state0, state1);
        @SuppressWarnings("unchecked")
        List<ObservationDiscrete<Events>> sequence = new ListArray<>(new ObservationDiscrete<>(Events.Umbrella), new ObservationDiscrete<>(Events.Umbrella), new ObservationDiscrete<>(Events.NoUmbrella), new ObservationDiscrete<>(Events.Umbrella), new ObservationDiscrete<>(Events.Umbrella));
        Tuple3<double[][], double[][], Double> abp = RegularForwardBackwardScaledCalculatorBase.Instance.computeAll(hmm, sequence);
        double[][] a = abp.getItem1();
        double[][] b = abp.getItem2();
        double p = abp.getItem3();
        double[] expecteda = {0.8182, 0.8834, 0.1907, 0.7308, 0.8673};
        double[] expectedb = {0.5923, 0.3763, 0.6533, 0.6273};
        AssertExtensions.pushEpsilon(0.0001);
        for (int t = 0x00; t < expecteda.length; t++) {
            AssertExtensions.assertEquals(expecteda[t], a[t][0x00]);
            AssertExtensions.assertEquals(1.0d - expecteda[t], a[t][0x01]);
        }
        AssertExtensions.setEpsilon(0.001);
        for (int t = 0x00; t < expectedb.length; t++) {
            AssertExtensions.assertEquals(b[t][0x00] * (1.0d - expectedb[t]), b[t][0x01] * expectedb[t]);
        }
        AssertExtensions.assertEquals(b[expectedb.length][0x00], b[expectedb.length][0x01]);
        AssertExtensions.popEpsilon();
    }

    /**
     * Test if the probability is the same of the ForwardBackwardCalculator.
     */
    @Test
    public void testSameProbability() {
        double[][] trans = {{0.0d, 0.0d, 0.0d}, {0.0d, 0.0d, 0.0d}, {0.0d, 0.0d, 0.0d}};
        double[][] exhaust = {{0.0d, 0.0d, 0.0d}, {0.0d, 0.0d, 0.0d}, {0.0d, 0.0d, 0.0d}};
        double[] pi = {0.0d, 0.0d, 0.0d};
        ArrayList<ObservationDiscrete<Tris>> tris = new ArrayList<>(0x90);
        for (int i = 0x00; i < 0x90; i++) {
            tris.add(null);
        }
        Tris[] trisvals = Tris.values();
        for (int t = 0x00; t < TestParameters.NUMBER_OF_TESTS; t++) {
            for (int i = 0x00; i < 0x03; i++) {
                ProbabilityUtils.fillRandomScale(trans[i]);
                ProbabilityUtils.fillRandomScale(exhaust[i]);
            }
            for (int i = 0x00; i < 0x90; i++) {
                tris.set(i, new ObservationDiscrete<>(trisvals[ProbabilityUtils.nextInt(0x03)]));
            }
            ProbabilityUtils.fillRandomScale(pi);
            Opdf<ObservationDiscrete<Tris>> state0 = new OpdfDiscrete<>(Tris.class, exhaust[0x00]);
            Opdf<ObservationDiscrete<Tris>> state1 = new OpdfDiscrete<>(Tris.class, exhaust[0x01]);
            Opdf<ObservationDiscrete<Tris>> state2 = new OpdfDiscrete<>(Tris.class, exhaust[0x02]);
            @SuppressWarnings("unchecked")
            RegularHmmBase<ObservationDiscrete<Tris>> hmm = new RegularHmmBase<>(pi, trans, state0, state1, state2);
            double expected = RegularForwardBackwardCalculatorBase.Instance.computeProbability(hmm, tris);
            double actual = RegularForwardBackwardScaledCalculatorBase.Instance.computeProbability(hmm, tris);
            AssertExtensions.assertEquals(expected, actual);
        }
    }

    public enum Events {

        Umbrella,
        NoUmbrella
    }

    public enum Tris {

        One,
        Two,
        Three
    }

}
