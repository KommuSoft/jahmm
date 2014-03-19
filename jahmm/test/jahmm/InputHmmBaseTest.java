/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jahmm;

import jahmm.observables.InputObservationTuple;
import jahmm.observables.Opdf;
import java.text.NumberFormat;
import java.util.List;
import junit.framework.Assert;
import jutils.probability.ProbabilityUtils;
import jutils.testing.AssertExtensions;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.TestParameters;

/**
 *
 * @author kommusoft
 */
public class InputHmmBaseTest {

    public InputHmmBaseTest() {
    }

    /**
     * Test of cloneA method, of class InputHmmBase.
     */
    @Test
    public void testCloneA00() {
        try {
            InputHmmBase.cloneA(null);
            fail("Should throw an exception.");
        } catch (IllegalArgumentException t) {
        }
    }

    /**
     * Test of cloneA method, of class InputHmmBase.
     */
    @Test
    public void testCloneA01() {
        try {
            InputHmmBase.cloneA(new double[0x00][][]);
            fail("Should throw an exception.");
        } catch (IllegalArgumentException t) {
        }
    }

    /**
     * Test of cloneA method, of class InputHmmBase.
     */
    @Test
    public void testCloneA02() {
        try {
            InputHmmBase.cloneA(new double[0x01][][]);
            fail("Should throw an exception.");
        } catch (IllegalArgumentException t) {
        }
    }

    /**
     * Test of cloneA method, of class InputHmmBase.
     */
    @Test
    public void testCloneA03() {
        try {
            InputHmmBase.cloneA(new double[][][]{new double[0x01][0x01], null});
            fail("Should throw an exception.");
        } catch (IllegalArgumentException t) {
        }
    }

    /**
     * Test of cloneA method, of class InputHmmBase.
     */
    @Test
    public void testCloneA04() {
        try {
            InputHmmBase.cloneA(new double[][][]{new double[0x01][0x01], new double[0x02][0x01]});
            fail("Should throw an exception.");
        } catch (IllegalArgumentException t) {
        }
    }

    /**
     * Test of cloneA method, of class InputHmmBase.
     */
    @Test
    public void testCloneA05() {
        try {
            InputHmmBase.cloneA(new double[][][]{new double[0x02][0x01], new double[0x02][0x01]});
            fail("Should throw an exception.");
        } catch (IllegalArgumentException t) {
        }
    }

    /**
     * Test of cloneA method, of class InputHmmBase.
     */
    @Test
    public void testCloneA06() {
        try {
            InputHmmBase.cloneA(new double[][][]{new double[][]{new double[0x02], null}, new double[0x02][0x02]});
            fail("Should throw an exception.");
        } catch (IllegalArgumentException t) {
        }
    }

    /**
     * Test of cloneA method, of class InputHmmBase.
     */
    @Test
    public void testCloneA07() {
        for (int t = 0x00; t < TestParameters.NUMBER_OF_TESTS; t++) {
            int N = 0x01 + ProbabilityUtils.nextInt(TestParameters.TEST_SIZE);
            int M = 0x01 + ProbabilityUtils.nextInt(TestParameters.TEST_SIZE);
            double[][][] vals = new double[N][M][N];
            for (int i = 0x00; i < N; i++) {
                for (int j = 0x00; j < M; j++) {
                    for (int k = 0x00; k < N; k++) {
                        vals[i][j][k] = ProbabilityUtils.nextGaussian();
                    }
                }
            }
            double[][][] clone = InputHmmBase.cloneA(vals);
            Assert.assertNotNull(clone);
            Assert.assertEquals(N, clone.length);
            for (int i = 0x00; i < N; i++) {
                Assert.assertNotNull(clone[i]);
                Assert.assertEquals(M, clone[i].length);
                for (int j = 0x00; j < M; j++) {
                    Assert.assertNotNull(clone[i][j]);
                    Assert.assertEquals(N, clone[i][j].length);
                    for (int k = 0x00; k < N; k++) {
                        AssertExtensions.assertEquals(vals[i][j][k], clone[i][j][k]);
                    }
                }
            }
        }
    }

}
