/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jahmm;

import jahmm.jadetree.foo.TrisEnum;
import jahmm.observables.ObservationDiscrete;
import jahmm.observables.OpdfDiscreteFactory;
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
            int N = 0x01 + ProbabilityUtils.nextInt(TestParameters.TEST_SIZE_SMALL);
            int M = 0x01 + ProbabilityUtils.nextInt(TestParameters.TEST_SIZE_SMALL);
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

    @Test
    public void testConstructor() {
        int N = 0x05;
        int M = TrisEnum.values().length;
        InputHmmBase<ObservationDiscrete<TrisEnum>, TrisEnum> hmm = new InputHmmBase<>(N, new OpdfDiscreteFactory<>(TrisEnum.class), TrisEnum.class);
        Assert.assertEquals(N, hmm.nbStates());
        Assert.assertEquals(M, hmm.nbSymbols());
        for (int i = 0x00; i < N; i++) {
            for (int j = 0x00; j < M; j++) {
                for (int k = 0x00; k < N; k++) {
                    for (int l = 0x00; l < N; l++) {
                        for (int m = 0x00; m < M; m++) {
                            for (int n = 0x00; n < N; n++) {
                                Assert.assertEquals(hmm.getAixj(i, j, k), hmm.getAixj(l, m, n));
                            }
                        }
                    }
                }
            }
        }
        //Assert.assertEquals(N*M,hmm);
    }

    /**
     * Test of generateA method, of class InputHmmBase.
     */
    @Test
    public void testGenerateA() {
        fail("The test case is a prototype.");
    }

    /**
     * Test of nbStates method, of class InputHmmBase.
     */
    @Test
    public void testNbStates() {
        fail("The test case is a prototype.");
    }

    /**
     * Test of nbSymbols method, of class InputHmmBase.
     */
    @Test
    public void testNbSymbols() {
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class InputHmmBase.
     */
    @Test
    public void testClone() throws Exception {
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAij method, of class InputHmmBase.
     */
    @Test
    public void testGetAij_int_int() {
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAij method, of class InputHmmBase.
     */
    @Test
    public void testGetAij_3args() {
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class InputHmmBase.
     */
    @Test
    public void testToString_0args() {
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class InputHmmBase.
     */
    @Test
    public void testToString_NumberFormat() {
        fail("The test case is a prototype.");
    }

    /**
     * Test of fold method, of class InputHmmBase.
     */
    @Test
    public void testFold_int() {
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOpdf method, of class InputHmmBase.
     */
    @Test
    public void testGetOpdf() {
        fail("The test case is a prototype.");
    }

    /**
     * Test of lnProbability method, of class InputHmmBase.
     */
    @Test
    public void testLnProbability() {
        fail("The test case is a prototype.");
    }

    /**
     * Test of mostLikelyStateSequence method, of class InputHmmBase.
     */
    @Test
    public void testMostLikelyStateSequence() {
        fail("The test case is a prototype.");
    }

    /**
     * Test of probability method, of class InputHmmBase.
     */
    @Test
    public void testProbability_List() {
        fail("The test case is a prototype.");
    }

    /**
     * Test of probability method, of class InputHmmBase.
     */
    @Test
    public void testProbability_List_intArr() {
        fail("The test case is a prototype.");
    }

    /**
     * Test of fold method, of class InputHmmBase.
     */
    @Test
    public void testFold_Iterable() {
        fail("The test case is a prototype.");
    }

    /**
     * Test of splitInput method, of class InputHmmBase.
     */
    @Test
    public void testSplitInput() {
        fail("The test case is a prototype.");
    }

    /**
     * Test of mergeInput method, of class InputHmmBase.
     */
    @Test
    public void testMergeInput() {
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInputIndex method, of class InputHmmBase.
     */
    @Test
    public void testGetInputIndex_GenericType() {
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAixj method, of class InputHmmBase.
     */
    @Test
    public void testGetAixj_3args_1() {
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAixj method, of class InputHmmBase.
     */
    @Test
    public void testGetAixj_3args_2() {
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInputIndex method, of class InputHmmBase.
     */
    @Test
    public void testGetInputIndex_Tagable() {
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAixj method, of class InputHmmBase.
     */
    @Test
    public void testGetAixj_3args_3() {
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAixj method, of class InputHmmBase.
     */
    @Test
    public void testSetAixj_4args_1() {
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAixj method, of class InputHmmBase.
     */
    @Test
    public void testSetAixj_4args_2() {
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAixj method, of class InputHmmBase.
     */
    @Test
    public void testSetAixj_4args_3() {
        fail("The test case is a prototype.");
    }

}
