package jahmm;

import jahmm.jadetree.foo.TrisEnum;
import jahmm.observables.ObservationDiscrete;
import jahmm.observables.OpdfDiscrete;
import jahmm.observables.OpdfDiscreteFactory;
import java.util.logging.Logger;
import junit.framework.Assert;
import jutils.probability.ProbabilityUtils;
import jutils.testing.AssertExtensions;
import static org.junit.Assert.fail;
import org.junit.Test;
import utils.TestParameters;

/**
 *
 * @author kommusoft
 */
public class InputHmmBaseTest {

    private static final Logger LOG = Logger.getLogger(InputHmmBaseTest.class.getName());

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
    public void testScenario00() throws CloneNotSupportedException {
        int N = 0x05;
        TrisEnum[] inputs = TrisEnum.values();
        int M = inputs.length;
        InputHmmBase<ObservationDiscrete<TrisEnum>, TrisEnum> hmm = new InputHmmBase<>(N, new OpdfDiscreteFactory<>(TrisEnum.class), TrisEnum.class);
        Assert.assertEquals(N, hmm.nbStates());
        Assert.assertEquals(M, hmm.nbSymbols());
        for (int i = 0x00; i < inputs.length; i++) {
            for (int j = 0x00; j < inputs.length; j++) {
                if (i == j) {
                    Assert.assertEquals(hmm.getInputIndex(inputs[i]), hmm.getInputIndex(inputs[j]));
                } else {
                    Assert.assertNotSame(hmm.getInputIndex(inputs[i]), hmm.getInputIndex(inputs[j]));
                }
            }
        }
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
        for (int i = 0x00; i < N; i++) {
            for (int j = 0x00; j < M; j++) {
                AssertExtensions.assertTypeof(OpdfDiscrete.class, hmm.getOpdf(i, j));
                for (int l = 0x00; l < N; l++) {
                    for (int m = 0x00; m < M; m++) {
                        if (l != i && m != j) {
                            Assert.assertNotSame(hmm.getOpdf(i, j), hmm.getOpdf(l, m));
                        }
                    }
                }
            }
        }
        InputHmmBase<ObservationDiscrete<TrisEnum>, TrisEnum> hmm2 = hmm.clone();
        for (int i = 0x00; i < N; i++) {
            for (int j = 0x00; j < M; j++) {
                for (int k = 0x00; k < N; k++) {
                    Assert.assertEquals(hmm.getAixj(i, j, k), hmm2.getAixj(i, j, k));
                    for (int l = 0x00; l < N; l++) {
                        for (int m = 0x00; m < M; m++) {
                            for (int n = 0x00; n < N; n++) {
                                Assert.assertEquals(hmm2.getAixj(i, j, k), hmm2.getAixj(l, m, n));
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0x00; i < N; i++) {
            for (int j = 0x00; j < M; j++) {
                Assert.assertNotSame(hmm.getOpdf(i, j), hmm2.getOpdf(i, j));
                AssertExtensions.assertTypeof(OpdfDiscrete.class, hmm2.getOpdf(i, j));
                for (int l = 0x00; l < N; l++) {
                    for (int m = 0x00; m < M; m++) {
                        if (l != i && m != j) {
                            Assert.assertNotSame(hmm2.getOpdf(i, j), hmm2.getOpdf(l, m));
                        }
                    }
                }
            }
        }
    }

    @Test
    public void testScenario01() {
        int N = 0x05;
        TrisEnum[] inputs = TrisEnum.values();
        int M = inputs.length;
        InputHmmBase<ObservationDiscrete<TrisEnum>, TrisEnum> hmm = new InputHmmBase<>(N, new OpdfDiscreteFactory<>(TrisEnum.class), inputs);
        Assert.assertEquals(N, hmm.nbStates());
        Assert.assertEquals(M, hmm.nbSymbols());
        for (int i = 0x00; i < inputs.length; i++) {
            for (int j = 0x00; j < inputs.length; j++) {
                if (i == j) {
                    Assert.assertEquals(hmm.getInputIndex(inputs[i]), hmm.getInputIndex(inputs[j]));
                } else {
                    Assert.assertNotSame(hmm.getInputIndex(inputs[i]), hmm.getInputIndex(inputs[j]));
                }
            }
        }
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
        for (int i = 0x00; i < N; i++) {
            for (int j = 0x00; j < M; j++) {
                AssertExtensions.assertTypeof(OpdfDiscrete.class, hmm.getOpdf(i, j));
                for (int l = 0x00; l < N; l++) {
                    for (int m = 0x00; m < M; m++) {
                        if (l != i && m != j) {
                            Assert.assertNotSame(hmm.getOpdf(i, j), hmm.getOpdf(l, m));
                        }
                    }
                }
            }
        }
    }

    /**
     * Test of generateA method, of class InputHmmBase.
     */
    @Test
    public void testGenerateA() {
    }

    /**
     * Test of nbStates method, of class InputHmmBase.
     */
    @Test
    public void testNbStates() {
    }

    /**
     * Test of nbSymbols method, of class InputHmmBase.
     */
    @Test
    public void testNbSymbols() {
    }

    /**
     * Test of clone method, of class InputHmmBase.
     */
    @Test
    public void testClone() throws Exception {
    }

    /**
     * Test of getAij method, of class InputHmmBase.
     */
    @Test
    public void testGetAij_int_int() {
    }

    /**
     * Test of getAij method, of class InputHmmBase.
     */
    @Test
    public void testGetAij_3args() {
    }

    /**
     * Test of toString method, of class InputHmmBase.
     */
    @Test
    public void testToString_0args() {
    }

    /**
     * Test of toString method, of class InputHmmBase.
     */
    @Test
    public void testToString_NumberFormat() {
    }

    /**
     * Test of fold method, of class InputHmmBase.
     */
    @Test
    public void testFold_int() {
    }

    /**
     * Test of getOpdf method, of class InputHmmBase.
     */
    @Test
    public void testGetOpdf() {
    }

    /**
     * Test of lnProbability method, of class InputHmmBase.
     */
    @Test
    public void testLnProbability() {
    }

    /**
     * Test of mostLikelyStateSequence method, of class InputHmmBase.
     */
    @Test
    public void testMostLikelyStateSequence() {
    }

    /**
     * Test of probability method, of class InputHmmBase.
     */
    @Test
    public void testProbability_List() {
    }

    /**
     * Test of probability method, of class InputHmmBase.
     */
    @Test
    public void testProbability_List_intArr() {
    }

    /**
     * Test of fold method, of class InputHmmBase.
     */
    @Test
    public void testFold_Iterable() {
    }

    /**
     * Test of splitInput method, of class InputHmmBase.
     */
    @Test
    public void testSplitInput() {
    }

    /**
     * Test of mergeInput method, of class InputHmmBase.
     */
    @Test
    public void testMergeInput() {
    }

    /**
     * Test of getInputIndex method, of class InputHmmBase.
     */
    @Test
    public void testGetInputIndex_GenericType() {
    }

    /**
     * Test of getAixj method, of class InputHmmBase.
     */
    @Test
    public void testGetAixj_3args_1() {
    }

    /**
     * Test of getAixj method, of class InputHmmBase.
     */
    @Test
    public void testGetAixj_3args_2() {;
    }

    /**
     * Test of getInputIndex method, of class InputHmmBase.
     */
    @Test
    public void testGetInputIndex_Tagable() {
    }

    /**
     * Test of getAixj method, of class InputHmmBase.
     */
    @Test
    public void testGetAixj_3args_3() {
    }

    /**
     * Test of setAixj method, of class InputHmmBase.
     */
    @Test
    public void testSetAixj_4args_1() {
    }

    /**
     * Test of setAixj method, of class InputHmmBase.
     */
    @Test
    public void testSetAixj_4args_2() {
    }

    /**
     * Test of setAixj method, of class InputHmmBase.
     */
    @Test
    public void testSetAixj_4args_3() {
    }

}
