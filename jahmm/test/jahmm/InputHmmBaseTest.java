package jahmm;

import jahmm.jadetree.foo.FooEnum;
import jahmm.jadetree.foo.TrisEnum;
import jahmm.observables.InputObservationTuple;
import jahmm.observables.ObservationEnum;
import jahmm.observables.ObservationInteger;
import jahmm.observables.OpdfEnum;
import jahmm.observables.OpdfEnumFactory;
import jahmm.observables.OpdfInteger;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.logging.Logger;
import junit.framework.Assert;
import jutils.probability.ProbabilityUtils;
import jutils.testing.AssertExtensions;
import jutlis.lists.ListArray;
import static org.junit.Assert.fail;
import org.junit.Test;
import utils.TestParameters;

/**
 *
 * @author kommusoft
 */
public class InputHmmBaseTest {

    private static final double[] ihmm_pi = {0.25d, 0.40d, 0.35d};
    private static final double a000 = 0.50d, a001 = 0.15d, a002 = 0.35d, a010 = 0.16d, a011 = 0.25d, a012 = 0.59d, a020 = 1.00d, a021 = 0.00d, a022 = 0.00d;
    private static final double a100 = 0.30d, a101 = 0.40d, a102 = 0.30d, a110 = 0.30d, a111 = 0.60d, a112 = 0.10d, a120 = 0.50d, a121 = 0.50d, a122 = 0.00d;
    private static final double a200 = 0.10d, a201 = 0.20d, a202 = 0.70d, a210 = 0.05d, a211 = 0.55d, a212 = 0.40d, a220 = 0.33d, a221 = 0.33d, a222 = 0.34d;
    private static final double[][][] ihmm_a = {
        {{a000, a001, a002}, {a010, a011, a012}, {a020, a021, a022}},
        {{a100, a101, a102}, {a110, a111, a112}, {a120, a121, a122}},
        {{a200, a201, a202}, {a210, a211, a212}, {a220, a221, a222}}
    };
    private static final double b000 = 0.45d, b001 = 0.55d, b010 = 1.00d, b011 = 0.00d, b020 = 0.39d, b021 = 0.61d;
    private static final double b100 = 0.25d, b101 = 0.75d, b110 = 0.14d, b111 = 0.86d, b120 = 0.05d, b121 = 0.95d;
    private static final double b200 = 0.67d, b201 = 0.33d, b210 = 0.52d, b211 = 0.48d, b220 = 0.12d, b221 = 0.88d;
    @SuppressWarnings("unchecked")
    private static final ListArray<ListArray<OpdfInteger>> ihmm_opdf = new ListArray(
            new ListArray<>(new OpdfInteger(b000, b001), new OpdfInteger(b010, b011), new OpdfInteger(b020, b021)),
            new ListArray<>(new OpdfInteger(b100, b101), new OpdfInteger(b110, b111), new OpdfInteger(b120, b121)),
            new ListArray<>(new OpdfInteger(b200, b201), new OpdfInteger(b210, b211), new OpdfInteger(b220, b221))
    );
    @SuppressWarnings("unchecked")
    private static final ListArray<InputObservationTuple<Integer, ObservationInteger>> ihmm_sequence = new ListArray<>(
            new InputObservationTuple<>(0x00, new ObservationInteger(0x00)),
            new InputObservationTuple<>(0x01, new ObservationInteger(0x01)),
            new InputObservationTuple<>(0x02, new ObservationInteger(0x00)),
            new InputObservationTuple<>(0x00, new ObservationInteger(0x01)),
            new InputObservationTuple<>(0x01, new ObservationInteger(0x00)),
            new InputObservationTuple<>(0x02, new ObservationInteger(0x01))
    );
    private static final ListArray<Integer> ihmm_inputs = new ListArray<>(new Integer[]{0x00, 0x01, 0x02});
    private static final Logger LOG = Logger.getLogger(InputHmmBaseTest.class.getName());
    private final InputHmmBase<ObservationInteger, Integer> ihmm;

    public InputHmmBaseTest() throws CloneNotSupportedException {
        ihmm = new InputHmmBase<>(ihmm_pi, ihmm_a, ihmm_opdf, ihmm_inputs);
    }

    @Test
    public void testConstructor() {
        Assert.assertEquals(ihmm_pi[0x00], ihmm.getPi(0x00));
        Assert.assertEquals(ihmm_pi[0x01], ihmm.getPi(0x01));
        Assert.assertEquals(ihmm_pi[0x02], ihmm.getPi(0x02));
        Assert.assertEquals(a000, ihmm.getAixj(0x00, 0x00, 0x00));
        Assert.assertEquals(a001, ihmm.getAixj(0x00, 0x00, 0x01));
        Assert.assertEquals(a002, ihmm.getAixj(0x00, 0x00, 0x02));
        Assert.assertEquals(a010, ihmm.getAixj(0x00, 0x01, 0x00));
        Assert.assertEquals(a011, ihmm.getAixj(0x00, 0x01, 0x01));
        Assert.assertEquals(a012, ihmm.getAixj(0x00, 0x01, 0x02));
        Assert.assertEquals(a020, ihmm.getAixj(0x00, 0x02, 0x00));
        Assert.assertEquals(a021, ihmm.getAixj(0x00, 0x02, 0x01));
        Assert.assertEquals(a022, ihmm.getAixj(0x00, 0x02, 0x02));
        Assert.assertEquals(a100, ihmm.getAixj(0x01, 0x00, 0x00));
        Assert.assertEquals(a101, ihmm.getAixj(0x01, 0x00, 0x01));
        Assert.assertEquals(a102, ihmm.getAixj(0x01, 0x00, 0x02));
        Assert.assertEquals(a110, ihmm.getAixj(0x01, 0x01, 0x00));
        Assert.assertEquals(a111, ihmm.getAixj(0x01, 0x01, 0x01));
        Assert.assertEquals(a112, ihmm.getAixj(0x01, 0x01, 0x02));
        Assert.assertEquals(a120, ihmm.getAixj(0x01, 0x02, 0x00));
        Assert.assertEquals(a121, ihmm.getAixj(0x01, 0x02, 0x01));
        Assert.assertEquals(a122, ihmm.getAixj(0x01, 0x02, 0x02));
        Assert.assertEquals(a200, ihmm.getAixj(0x02, 0x00, 0x00));
        Assert.assertEquals(a201, ihmm.getAixj(0x02, 0x00, 0x01));
        Assert.assertEquals(a202, ihmm.getAixj(0x02, 0x00, 0x02));
        Assert.assertEquals(a210, ihmm.getAixj(0x02, 0x01, 0x00));
        Assert.assertEquals(a211, ihmm.getAixj(0x02, 0x01, 0x01));
        Assert.assertEquals(a212, ihmm.getAixj(0x02, 0x01, 0x02));
        Assert.assertEquals(a220, ihmm.getAixj(0x02, 0x02, 0x00));
        Assert.assertEquals(a221, ihmm.getAixj(0x02, 0x02, 0x01));
        Assert.assertEquals(a222, ihmm.getAixj(0x02, 0x02, 0x02));
        for (int i = 0x00; i < 0x03; i++) {
            for (int j = 0x00; j < 0x03; j++) {
                Assert.assertNotNull(ihmm.getOpdf(i, j));
                AssertExtensions.assertTypeof(OpdfInteger.class, ihmm.getOpdf(i, j));
            }
        }
        for (int i = 0x00; i < 0x03; i++) {
            for (int j = 0x00; j < 0x03; j++) {
                for (int k = 0x00; k < 0x03; k++) {
                    for (int l = 0x00; l < 0x03; l++) {
                        if (i == k && j == l) {
                            Assert.assertSame(ihmm.getOpdf(k, l), ihmm.getOpdf(i, j));
                        } else {
                            Assert.assertNotSame(ihmm.getOpdf(k, l), ihmm.getOpdf(i, j));
                        }
                    }
                }
            }
        }
        ObservationInteger oi0 = new ObservationInteger(0x00);
        ObservationInteger oi1 = new ObservationInteger(0x01);
        Assert.assertEquals(b000, ihmm.getOpdf(0x00, 0x00).probability(oi0));
        Assert.assertEquals(b001, ihmm.getOpdf(0x00, 0x00).probability(oi1));
        Assert.assertEquals(b010, ihmm.getOpdf(0x00, 0x01).probability(oi0));
        Assert.assertEquals(b011, ihmm.getOpdf(0x00, 0x01).probability(oi1));
        Assert.assertEquals(b020, ihmm.getOpdf(0x00, 0x02).probability(oi0));
        Assert.assertEquals(b021, ihmm.getOpdf(0x00, 0x02).probability(oi1));
        Assert.assertEquals(b100, ihmm.getOpdf(0x01, 0x00).probability(oi0));
        Assert.assertEquals(b101, ihmm.getOpdf(0x01, 0x00).probability(oi1));
        Assert.assertEquals(b110, ihmm.getOpdf(0x01, 0x01).probability(oi0));
        Assert.assertEquals(b111, ihmm.getOpdf(0x01, 0x01).probability(oi1));
        Assert.assertEquals(b120, ihmm.getOpdf(0x01, 0x02).probability(oi0));
        Assert.assertEquals(b121, ihmm.getOpdf(0x01, 0x02).probability(oi1));
        Assert.assertEquals(b200, ihmm.getOpdf(0x02, 0x00).probability(oi0));
        Assert.assertEquals(b201, ihmm.getOpdf(0x02, 0x00).probability(oi1));
        Assert.assertEquals(b210, ihmm.getOpdf(0x02, 0x01).probability(oi0));
        Assert.assertEquals(b211, ihmm.getOpdf(0x02, 0x01).probability(oi1));
        Assert.assertEquals(b220, ihmm.getOpdf(0x02, 0x02).probability(oi0));
        Assert.assertEquals(b221, ihmm.getOpdf(0x02, 0x02).probability(oi1));
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
        try {
            InputHmmBase.cloneA(new double[0x01][0x00][0x00]);
            fail("Should throw an exception.");
        } catch (IllegalArgumentException t) {
        }
    }

    /**
     * Test of cloneA method, of class InputHmmBase.
     */
    @Test
    public void testCloneA08() {
        try {
            InputHmmBase.cloneA(new double[0x01][0x01][0x00]);
            fail("Should throw an exception.");
        } catch (IllegalArgumentException t) {
        }
    }

    /**
     * Test of cloneA method, of class InputHmmBase.
     */
    @Test
    public void testCloneA09() {
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
    public void testCloneA10() {
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

    /**
     * Test of generateA method, of class InputHmmBase.
     */
    @Test
    public void testGenerateA00() {
        try {
            InputHmmBase.generateA(-0x05, -0x03);
            fail("Should throw an exception.");
        } catch (IllegalArgumentException t) {
        }
    }

    /**
     * Test of generateA method, of class InputHmmBase.
     */
    @Test
    public void testGenerateA01() {
        try {
            InputHmmBase.generateA(-0x05, 20);
            fail("Should throw an exception.");
        } catch (IllegalArgumentException t) {
        }
    }

    /**
     * Test of generateA method, of class InputHmmBase.
     */
    @Test
    public void testGenerateA02() {
        try {
            InputHmmBase.generateA(10, -0x03);
            fail("Should throw an exception.");
        } catch (IllegalArgumentException t) {
        }
    }

    /**
     * Test of generateA method, of class InputHmmBase.
     */
    @Test
    public void testGenerateA03() {
        int M = 0x05;
        int N = 0x03;
        double[][][] a = InputHmmBase.generateA(M, N);
        Assert.assertNotNull(a);
        Assert.assertEquals(N, a.length);
        for (int i = 0x00; i < N; i++) {
            Assert.assertNotNull(a[i]);
            Assert.assertEquals(M, a[i].length);
            for (int j = 0x00; j < M; j++) {
                Assert.assertNotNull(a[i][j]);
                Assert.assertEquals(N, a[i][j].length);
                double sum = 0.0d;
                for (int k = 0x00; k < N; k++) {
                    sum += a[i][j][k];
                    for (int l = 0x00; l < N; l++) {
                        AssertExtensions.assertEquals(a[i][j][k], a[i][j][l]);
                    }
                }
                AssertExtensions.assertEquals(1.0d, sum);
            }
        }
    }

    @Test
    public void testScenario00() throws CloneNotSupportedException {
        int N = 0x05;
        TrisEnum[] inputs = TrisEnum.values();
        int M = inputs.length;
        InputHmmBase<ObservationEnum<TrisEnum>, TrisEnum> hmm = new InputEnumHmmBase<>(N, new OpdfEnumFactory<>(TrisEnum.class), TrisEnum.class);
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
                AssertExtensions.assertTypeof(OpdfEnum.class, hmm.getOpdf(i, j));
                for (int l = 0x00; l < N; l++) {
                    for (int m = 0x00; m < M; m++) {
                        if (l != i && m != j) {
                            Assert.assertNotSame(hmm.getOpdf(i, j), hmm.getOpdf(l, m));
                        }
                    }
                }
            }
        }
        InputHmmBase<ObservationEnum<TrisEnum>, TrisEnum> hmm2 = hmm.clone();
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
                AssertExtensions.assertTypeof(OpdfEnum.class, hmm2.getOpdf(i, j));
                for (int l = 0x00; l < N; l++) {
                    for (int m = 0x00; m < M; m++) {
                        if (l != i && m != j) {
                            Assert.assertNotSame(hmm2.getOpdf(i, j), hmm2.getOpdf(l, m));
                        }
                    }
                }
            }
        }
        for (int i = 0x00; i < inputs.length; i++) {
            for (int j = 0x00; j < inputs.length; j++) {
                if (i == j) {
                    Assert.assertEquals(hmm2.getInputIndex(inputs[i]), hmm2.getInputIndex(inputs[j]));
                } else {
                    Assert.assertNotSame(hmm2.getInputIndex(inputs[i]), hmm2.getInputIndex(inputs[j]));
                }
            }
        }
    }

    @Test
    public void testScenario01() throws CloneNotSupportedException {
        int N = 0x05;
        TrisEnum[] inputs = new TrisEnum[]{TrisEnum.Odin, TrisEnum.Dva};
        int M = inputs.length;
        InputHmmBase<ObservationEnum<TrisEnum>, TrisEnum> hmm = new InputHmmBase<>(N, new OpdfEnumFactory<>(TrisEnum.class), inputs);
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
                AssertExtensions.assertTypeof(OpdfEnum.class, hmm.getOpdf(i, j));
                for (int l = 0x00; l < N; l++) {
                    for (int m = 0x00; m < M; m++) {
                        if (l != i && m != j) {
                            Assert.assertNotSame(hmm.getOpdf(i, j), hmm.getOpdf(l, m));
                        }
                    }
                }
            }
        }
        InputHmmBase<ObservationEnum<TrisEnum>, TrisEnum> hmm2 = hmm.clone();
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
                AssertExtensions.assertTypeof(OpdfEnum.class, hmm2.getOpdf(i, j));
                for (int l = 0x00; l < N; l++) {
                    for (int m = 0x00; m < M; m++) {
                        if (l != i && m != j) {
                            Assert.assertNotSame(hmm2.getOpdf(i, j), hmm2.getOpdf(l, m));
                        }
                    }
                }
            }
        }
        for (int i = 0x00; i < M; i++) {
            for (int j = 0x00; j < M; j++) {
                if (i == j) {
                    Assert.assertEquals(hmm2.getInputIndex(inputs[i]), hmm2.getInputIndex(inputs[j]));
                } else {
                    Assert.assertNotSame(hmm2.getInputIndex(inputs[i]), hmm2.getInputIndex(inputs[j]));
                }
            }
        }
        hmm2.mergeInput(TrisEnum.Tri, inputs);
        M = 0x01;
        Assert.assertEquals(N, hmm2.nbStates());
        Assert.assertEquals(M, hmm2.nbSymbols());
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
        hmm2.splitInput(TrisEnum.Tri, inputs);
        M = 0x02;
        Assert.assertEquals(N, hmm2.nbStates());
        Assert.assertEquals(M, hmm2.nbSymbols());
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
                AssertExtensions.assertTypeof(OpdfEnum.class, hmm2.getOpdf(i, j));
                for (int l = 0x00; l < N; l++) {
                    for (int m = 0x00; m < M; m++) {
                        if (l != i && m != j) {
                            Assert.assertNotSame(hmm2.getOpdf(i, j), hmm2.getOpdf(l, m));
                        }
                    }
                }
            }
        }
        for (int i = 0x00; i < M; i++) {
            for (int j = 0x00; j < M; j++) {
                if (i == j) {
                    Assert.assertEquals(hmm2.getInputIndex(inputs[i]), hmm2.getInputIndex(inputs[j]));
                } else {
                    Assert.assertNotSame(hmm2.getInputIndex(inputs[i]), hmm2.getInputIndex(inputs[j]));
                }
            }
        }
    }

    /**
     * Test of nbStates method, of class InputHmmBase.
     */
    @Test
    public void testNbStates00() {
        for (int t = 0x00; t < TestParameters.NUMBER_OF_TESTS; t++) {
            int N = ProbabilityUtils.nextInt(TestParameters.TEST_SIZE_SMALL) + 0x02;
            FooEnum[] inputs = new FooEnum[]{FooEnum.Qux, FooEnum.Foobar, FooEnum.Quux};
            ProbabilityUtils.shuffle1(inputs);
            FooEnum[] splitres = new FooEnum[]{FooEnum.Foo, FooEnum.Bar};
            ProbabilityUtils.shuffle1(splitres);
            int M = inputs.length;
            InputHmmBase<ObservationEnum<FooEnum>, FooEnum> hmm = new InputHmmBase<>(N, new OpdfEnumFactory<>(FooEnum.class), inputs);
            Assert.assertEquals(N, hmm.nbStates());
        }
    }

    /**
     * Test of nbSymbols method, of class InputHmmBase.
     */
    @Test
    public void testNbSymbols00() {
        ListArray<FooEnum> foobar = new ListArray<>(FooEnum.values());
        HashSet<FooEnum> bag = new HashSet<>();
        for (int t = 0x00; t < TestParameters.NUMBER_OF_TESTS; t++) {
            int N = ProbabilityUtils.nextInt(TestParameters.TEST_SIZE_SMALL) + 0x02;
            bag.add(ProbabilityUtils.nextElement(foobar));
            while (ProbabilityUtils.nextDouble() < 0.5d) {
                bag.add(ProbabilityUtils.nextElement(foobar));
            }
            InputHmmBase<ObservationEnum<FooEnum>, FooEnum> hmm = new InputHmmBase<>(N, new OpdfEnumFactory<>(FooEnum.class), bag);
            Assert.assertEquals(bag.size(), hmm.nbSymbols());
            bag.clear();
        }
    }

    /**
     * Test of fold method, of class InputHmmBase.
     */
    @Test
    public void testFold_int00() throws CloneNotSupportedException {
        for (int t = 0x00; t < TestParameters.NUMBER_OF_TESTS; t++) {
            int m = ProbabilityUtils.nextInt(TestParameters.TEST_SIZE_SMALL) + 0x01;
            TrisEnum[] ti = new TrisEnum[]{TrisEnum.Odin, TrisEnum.Dva};
            int n = ti.length;
            InputHmmBase<ObservationEnum<TrisEnum>, TrisEnum> hmm = generateRandomIHmm1(m, ti, n);
            int f = ProbabilityUtils.nextInt(TestParameters.TEST_SIZE_SMALL);
            InputHmmBase<ObservationEnum<TrisEnum>, TrisEnum> hmm2 = hmm.clone();
            for (int i = 0x00; i < f; i++) {
                hmm.fold();
            }
            hmm2.fold(f);
            for (int i = 0x00; i < m; i++) {
                AssertExtensions.assertEquals(hmm.getPi(i), hmm2.getPi(i));
            }
        }
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
    public void testProbability00() {
        int l = 0x01;
        double pi00 = ihmm_pi[0x00];
        double pi01 = ihmm_pi[0x01];
        double pi02 = ihmm_pi[0x02];

        double pa = pi00 * b000 + pi01 * b100 + pi02 * b200;

        AssertExtensions.assertEquals(pa, ihmm.probability(ihmm_sequence.subList(0x00, l)));
    }

    /**
     * Test of probability method, of class InputHmmBase.
     */
    @Test
    public void testProbability01() {
        int l = 0x02;
        double pi00 = ihmm_pi[0x00];
        double pi01 = ihmm_pi[0x01];
        double pi02 = ihmm_pi[0x02];

        double pa = pi00 * b000 + pi01 * b100 + pi02 * b200;

        double pi10 = pi00 * a000 + pi01 * a100 + pi02 * a200;
        double pi11 = pi00 * a001 + pi01 * a101 + pi02 * a201;
        double pi12 = pi00 * a002 + pi01 * a102 + pi02 * a202;

        pa *= pi10 * b011 + pi11 * b111 + pi12 * b211;

        AssertExtensions.assertEquals(pa, ihmm.probability(ihmm_sequence.subList(0x00, l)));
    }

    /**
     * Test of probability method, of class InputHmmBase.
     */
    @Test
    public void testProbability02() {
        int l = 0x03;
        double pi00 = ihmm_pi[0x00];
        double pi01 = ihmm_pi[0x01];
        double pi02 = ihmm_pi[0x02];

        double pa = pi00 * b000 + pi01 * b100 + pi02 * b200;

        double pi10 = pi00 * a000 + pi01 * a100 + pi02 * a200;
        double pi11 = pi00 * a001 + pi01 * a101 + pi02 * a201;
        double pi12 = pi00 * a002 + pi01 * a102 + pi02 * a202;

        pa *= pi10 * b011 + pi11 * b111 + pi12 * b211;

        double pi20 = pi10 * a010 + pi11 * a110 + pi12 * a210;
        double pi21 = pi10 * a011 + pi11 * a111 + pi12 * a211;
        double pi22 = pi10 * a012 + pi11 * a112 + pi12 * a212;

        pa *= pi20 * b020 + pi21 * b120 + pi22 * b220;

        AssertExtensions.assertEquals(pa, ihmm.probability(ihmm_sequence.subList(0x00, l)));
    }

    /**
     * Test of probability method, of class InputHmmBase.
     */
    @Test
    public void testProbability03() {
        int l = 0x04;
        double pi00 = ihmm_pi[0x00];
        double pi01 = ihmm_pi[0x01];
        double pi02 = ihmm_pi[0x02];

        double pa = pi00 * b000 + pi01 * b100 + pi02 * b200;

        double pi10 = pi00 * a000 + pi01 * a100 + pi02 * a200;
        double pi11 = pi00 * a001 + pi01 * a101 + pi02 * a201;
        double pi12 = pi00 * a002 + pi01 * a102 + pi02 * a202;

        pa *= pi10 * b011 + pi11 * b111 + pi12 * b211;

        double pi20 = pi10 * a010 + pi11 * a110 + pi12 * a210;
        double pi21 = pi10 * a011 + pi11 * a111 + pi12 * a211;
        double pi22 = pi10 * a012 + pi11 * a112 + pi12 * a212;

        pa *= pi20 * b020 + pi21 * b120 + pi22 * b220;

        double pi30 = pi20 * a020 + pi21 * a120 + pi22 * a220;
        double pi31 = pi20 * a021 + pi21 * a121 + pi22 * a221;
        double pi32 = pi20 * a022 + pi21 * a122 + pi22 * a222;

        pa *= pi30 * b001 + pi31 * b101 + pi32 * b201;

        AssertExtensions.assertEquals(pa, ihmm.probability(ihmm_sequence.subList(0x00, l)));
    }

    /**
     * Test of probability method, of class InputHmmBase.
     */
    @Test
    public void testProbability04() {
        int l = 0x05;
        double pi00 = ihmm_pi[0x00];
        double pi01 = ihmm_pi[0x01];
        double pi02 = ihmm_pi[0x02];

        double pa = pi00 * b000 + pi01 * b100 + pi02 * b200;

        double pi10 = pi00 * a000 + pi01 * a100 + pi02 * a200;
        double pi11 = pi00 * a001 + pi01 * a101 + pi02 * a201;
        double pi12 = pi00 * a002 + pi01 * a102 + pi02 * a202;

        pa *= pi10 * b011 + pi11 * b111 + pi12 * b211;

        double pi20 = pi10 * a010 + pi11 * a110 + pi12 * a210;
        double pi21 = pi10 * a011 + pi11 * a111 + pi12 * a211;
        double pi22 = pi10 * a012 + pi11 * a112 + pi12 * a212;

        pa *= pi20 * b020 + pi21 * b120 + pi22 * b220;

        double pi30 = pi20 * a020 + pi21 * a120 + pi22 * a220;
        double pi31 = pi20 * a021 + pi21 * a121 + pi22 * a221;
        double pi32 = pi20 * a022 + pi21 * a122 + pi22 * a222;

        pa *= pi30 * b001 + pi31 * b101 + pi32 * b201;

        double pi40 = pi30 * a000 + pi31 * a100 + pi32 * a200;
        double pi41 = pi30 * a001 + pi31 * a101 + pi32 * a201;
        double pi42 = pi30 * a002 + pi31 * a102 + pi32 * a202;

        pa *= pi40 * b010 + pi41 * b110 + pi42 * b210;

        AssertExtensions.assertEquals(pa, ihmm.probability(ihmm_sequence.subList(0x00, l)));
    }

    /**
     * Test of probability method, of class InputHmmBase.
     */
    @Test
    public void testProbability05() {
        int l = 0x06;
        double pi00 = ihmm_pi[0x00];
        double pi01 = ihmm_pi[0x01];
        double pi02 = ihmm_pi[0x02];

        double pa = pi00 * b000 + pi01 * b100 + pi02 * b200;

        double pi10 = pi00 * a000 + pi01 * a100 + pi02 * a200;
        double pi11 = pi00 * a001 + pi01 * a101 + pi02 * a201;
        double pi12 = pi00 * a002 + pi01 * a102 + pi02 * a202;

        pa *= pi10 * b011 + pi11 * b111 + pi12 * b211;

        double pi20 = pi10 * a010 + pi11 * a110 + pi12 * a210;
        double pi21 = pi10 * a011 + pi11 * a111 + pi12 * a211;
        double pi22 = pi10 * a012 + pi11 * a112 + pi12 * a212;

        pa *= pi20 * b020 + pi21 * b120 + pi22 * b220;

        double pi30 = pi20 * a020 + pi21 * a120 + pi22 * a220;
        double pi31 = pi20 * a021 + pi21 * a121 + pi22 * a221;
        double pi32 = pi20 * a022 + pi21 * a122 + pi22 * a222;

        pa *= pi30 * b001 + pi31 * b101 + pi32 * b201;

        double pi40 = pi30 * a000 + pi31 * a100 + pi32 * a200;
        double pi41 = pi30 * a001 + pi31 * a101 + pi32 * a201;
        double pi42 = pi30 * a002 + pi31 * a102 + pi32 * a202;

        pa *= pi40 * b010 + pi41 * b110 + pi42 * b210;

        double pi50 = pi40 * a010 + pi41 * a110 + pi42 * a210;
        double pi51 = pi40 * a011 + pi41 * a111 + pi42 * a211;
        double pi52 = pi40 * a012 + pi41 * a112 + pi42 * a212;

        pa *= pi50 * b021 + pi51 * b121 + pi52 * b221;

        AssertExtensions.assertEquals(pa, ihmm.probability(ihmm_sequence.subList(0x00, l)));
    }

    /**
     * Test of probability method, of class InputHmmBase.
     */
    @Test
    public void testProbability_List_intArr() {
    }

    /**
     * Test of fold method, of class InputHmmBase.
     *
     * @throws java.lang.CloneNotSupportedException
     */
    @Test
    public void testFold_Iterable00() throws CloneNotSupportedException {
        for (int t = 0x00; t < TestParameters.NUMBER_OF_TESTS; t++) {
            int m = ProbabilityUtils.nextInt(TestParameters.TEST_SIZE_SMALL) + 0x01;
            TrisEnum[] ti = new TrisEnum[]{TrisEnum.Odin, TrisEnum.Dva};
            int n = ti.length;
            InputHmmBase<ObservationEnum<TrisEnum>, TrisEnum> hmm = generateRandomIHmm1(m, ti, n);
            LinkedList<TrisEnum> iterable = new LinkedList<>();
            while (ProbabilityUtils.nextBoolean(0.85d)) {
                iterable.add(ProbabilityUtils.nextElement(ti));
            }
            InputHmmBase<ObservationEnum<TrisEnum>, TrisEnum> hmm2 = hmm.clone();
            for (TrisEnum te : iterable) {
                hmm.fold(te);
            }
            hmm2.fold(iterable);
            for (int i = 0x00; i < m; i++) {
                AssertExtensions.assertEquals(hmm.getPi(i), hmm2.getPi(i));
            }
        }
    }

    @Test
    public void testSplitInput00() throws CloneNotSupportedException {
        for (int t = 0x00; t < TestParameters.NUMBER_OF_TESTS; t++) {
            int N = ProbabilityUtils.nextInt(TestParameters.TEST_SIZE_SMALL) + 0x02;
            FooEnum[] inputs = new FooEnum[]{FooEnum.Qux, FooEnum.Foobar, FooEnum.Quux};
            ProbabilityUtils.shuffle1(inputs);
            FooEnum[] splitres = new FooEnum[]{FooEnum.Foo, FooEnum.Bar};
            ProbabilityUtils.shuffle1(splitres);
            int M = inputs.length;
            InputHmmBase<ObservationEnum<FooEnum>, FooEnum> hmm = new InputHmmBase<>(N, new OpdfEnumFactory<>(FooEnum.class
            ), inputs);
            InputHmmBase<ObservationEnum<FooEnum>, FooEnum> hmm2 = hmm.clone();

            Assert.assertEquals(inputs.length, hmm2.nbSymbols());
            double pa = ProbabilityUtils.nextDouble();
            double pb = ProbabilityUtils.nextDouble();

            hmm2.setAixj(
                    0x00, FooEnum.Foobar, 0x00, pa);
            hmm2.setAixj(
                    0x00, FooEnum.Foobar, 0x01, 1.0d - pa);
            hmm2.setAixj(
                    0x01, FooEnum.Foobar, 0x00, pb);
            hmm2.setAixj(
                    0x01, FooEnum.Foobar, 0x01, 1.0 - pb);
            hmm2.splitInput(FooEnum.Foobar, splitres);

            Assert.assertEquals(inputs.length
                    + splitres.length - 0x01, hmm2.nbSymbols());
            AssertExtensions.assertEquals(pa, hmm2.getAixj(0x00, FooEnum.Foo, 0x00));
            AssertExtensions.assertEquals(
                    1.0d - pa, hmm2.getAixj(0x00, FooEnum.Foo, 0x01));
            AssertExtensions.assertEquals(pb, hmm2.getAixj(0x01, FooEnum.Foo, 0x00));
            AssertExtensions.assertEquals(
                    1.0d - pb, hmm2.getAixj(0x01, FooEnum.Foo, 0x01));
            AssertExtensions.assertEquals(pa, hmm2.getAixj(0x00, FooEnum.Bar, 0x00));
            AssertExtensions.assertEquals(
                    1.0d - pa, hmm2.getAixj(0x00, FooEnum.Bar, 0x01));
            AssertExtensions.assertEquals(pb, hmm2.getAixj(0x01, FooEnum.Bar, 0x00));
            AssertExtensions.assertEquals(
                    1.0d - pb, hmm2.getAixj(0x01, FooEnum.Bar, 0x01));
        }
    }

    /**
     * Test of mergeInput method, of class InputHmmBase.
     *
     * @throws java.lang.CloneNotSupportedException
     */
    @Test
    public void testMergeInput00() throws CloneNotSupportedException {
        for (int t = 0x00; t < TestParameters.NUMBER_OF_TESTS; t++) {
            int N = ProbabilityUtils.nextInt(TestParameters.TEST_SIZE_SMALL) + 0x02;
            FooEnum[] inputs = new FooEnum[]{FooEnum.Qux, FooEnum.Foo, FooEnum.Bar, FooEnum.Quux};
            ProbabilityUtils.shuffle1(inputs);
            FooEnum[] tomerge = new FooEnum[]{FooEnum.Foo, FooEnum.Bar};
            ProbabilityUtils.shuffle1(tomerge);
            int M = inputs.length;
            InputHmmBase<ObservationEnum<FooEnum>, FooEnum> hmm = new InputHmmBase<>(N, new OpdfEnumFactory<>(FooEnum.class
            ), inputs);
            InputHmmBase<ObservationEnum<FooEnum>, FooEnum> hmm2 = hmm.clone();

            Assert.assertEquals(inputs.length, hmm2.nbSymbols());
            double pa = ProbabilityUtils.nextDouble();
            double pb = ProbabilityUtils.nextDouble();
            double pc = ProbabilityUtils.nextDouble();
            double pd = ProbabilityUtils.nextDouble();

            hmm2.setAixj(
                    0x00, FooEnum.Foo, 0x00, pa);
            hmm2.setAixj(
                    0x00, FooEnum.Foo, 0x01, 1.0d - pa);
            hmm2.setAixj(
                    0x01, FooEnum.Foo, 0x00, pb);
            hmm2.setAixj(
                    0x01, FooEnum.Foo, 0x01, 1.0 - pb);
            hmm2.setAixj(
                    0x00, FooEnum.Bar, 0x00, pc);
            hmm2.setAixj(
                    0x00, FooEnum.Bar, 0x01, 1.0d - pc);
            hmm2.setAixj(
                    0x01, FooEnum.Bar, 0x00, pd);
            hmm2.setAixj(
                    0x01, FooEnum.Bar, 0x01, 1.0 - pd);
            hmm2.mergeInput(FooEnum.Foobar, tomerge);

            Assert.assertEquals(inputs.length
                    - tomerge.length + 0x01, hmm2.nbSymbols());
            AssertExtensions.assertEquals(
                    0.5d * (pa + pc), hmm2.getAixj(0x00, FooEnum.Foobar, 0x00));
            AssertExtensions.assertEquals(
                    1.0d - 0.5d * (pa + pc), hmm2.getAixj(0x00, FooEnum.Foobar, 0x01));
            AssertExtensions.assertEquals(
                    0.5d * (pb + pd), hmm2.getAixj(0x01, FooEnum.Foobar, 0x00));
            AssertExtensions.assertEquals(
                    1.0d - 0.5d * (pb + pd), hmm2.getAixj(0x01, FooEnum.Foobar, 0x01));
        }
    }

    /**
     * Test of getInputIndex method, of class InputHmmBase.
     */
    @Test
    public void testGetIndexRegister00() {
        ListArray<FooEnum> foobar = new ListArray<>(FooEnum.values());
        HashSet<FooEnum> bag = new HashSet<>();
        for (int t = 0x00; t < TestParameters.NUMBER_OF_TESTS; t++) {
            int N = ProbabilityUtils.nextInt(TestParameters.TEST_SIZE_SMALL) + 0x02;
            bag.add(ProbabilityUtils.nextElement(foobar));
            while (ProbabilityUtils.nextDouble() < 0.5d) {
                bag.add(ProbabilityUtils.nextElement(foobar));

            }
            InputHmmBase<ObservationEnum<FooEnum>, FooEnum> hmm = new InputHmmBase<>(N, new OpdfEnumFactory<>(FooEnum.class
            ), bag);
            for (Integer index
                    : hmm.getIndexRegister()
                    .values()) {
                Assert.assertNotNull(index);
                int idx = index;
                AssertExtensions.assertLessThan(idx, hmm.nbSymbols());
                AssertExtensions.assertGreaterThanOrEqual(idx, 0x00);
            }

            bag.clear();
        }
    }

    /**
     * Test of getInputIndex method, of class InputHmmBase.
     */
    @Test
    public void testGetIndexRegister01() throws CloneNotSupportedException {
        for (int t = 0x00; t < TestParameters.NUMBER_OF_TESTS; t++) {
            int N = ProbabilityUtils.nextInt(TestParameters.TEST_SIZE_SMALL) + 0x02;
            FooEnum[] inputs = new FooEnum[]{FooEnum.Qux, FooEnum.Foobar, FooEnum.Quux};
            ProbabilityUtils.shuffle1(inputs);
            FooEnum[] splitres = new FooEnum[]{FooEnum.Foo, FooEnum.Bar};
            ProbabilityUtils.shuffle1(splitres);
            int M = inputs.length;
            InputHmmBase<ObservationEnum<FooEnum>, FooEnum> hmm = new InputHmmBase<>(N, new OpdfEnumFactory<>(FooEnum.class
            ), inputs);
            for (Integer index
                    : hmm.getIndexRegister()
                    .values()) {
                Assert.assertNotNull(index);
                int idx = index;
                AssertExtensions.assertLessThan(idx, hmm.nbSymbols());
                AssertExtensions.assertGreaterThanOrEqual(idx, 0x00);
            }

            hmm.splitInput(FooEnum.Foobar, splitres);
            for (Integer index
                    : hmm.getIndexRegister()
                    .values()) {
                Assert.assertNotNull(index);
                int idx = index;
                AssertExtensions.assertLessThan(idx, hmm.nbSymbols());
                AssertExtensions.assertGreaterThanOrEqual(idx, 0x00);
            }
        }
    }

    /**
     * Test of getInputIndex method, of class InputHmmBase.
     */
    @Test
    public void testGetIndexRegister02() throws CloneNotSupportedException {
        for (int t = 0x00; t < TestParameters.NUMBER_OF_TESTS; t++) {
            int N = ProbabilityUtils.nextInt(TestParameters.TEST_SIZE_SMALL) + 0x02;
            FooEnum[] inputs = new FooEnum[]{FooEnum.Qux, FooEnum.Foo, FooEnum.Bar, FooEnum.Quux};
            ProbabilityUtils.shuffle1(inputs);
            FooEnum[] tomerge = new FooEnum[]{FooEnum.Foo, FooEnum.Bar};
            ProbabilityUtils.shuffle1(tomerge);
            int M = inputs.length;
            InputHmmBase<ObservationEnum<FooEnum>, FooEnum> hmm = new InputHmmBase<>(N, new OpdfEnumFactory<>(FooEnum.class
            ), inputs);
            InputHmmBase<ObservationEnum<FooEnum>, FooEnum> hmm2 = hmm.clone();

            Assert.assertEquals(inputs.length, hmm2.nbSymbols());
            for (Integer index
                    : hmm.getIndexRegister()
                    .values()) {
                Assert.assertNotNull(index);
                int idx = index;
                AssertExtensions.assertLessThan(idx, hmm.nbSymbols());
                AssertExtensions.assertGreaterThanOrEqual(idx, 0x00);
            }

            hmm2.mergeInput(FooEnum.Foobar, tomerge);
            for (Integer index
                    : hmm.getIndexRegister()
                    .values()) {
                Assert.assertNotNull(index);
                int idx = index;
                AssertExtensions.assertLessThan(idx, hmm.nbSymbols());
                AssertExtensions.assertGreaterThanOrEqual(idx, 0x00);
            }
        }
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

    /**
     * Test of generateB method, of class InputHmmBase.
     */
    @Test
    public void testGenerateB_3args_1() {
    }

    /**
     * Test of generateB method, of class InputHmmBase.
     */
    @Test
    public void testGenerateB_3args_2() throws Exception {
    }

    /**
     * Test of cloneB method, of class InputHmmBase.
     */
    @Test
    public void testCloneB() throws Exception {
    }

    /**
     * Test of getOpdf method, of class InputHmmBase.
     */
    @Test
    public void testGetOpdf_int_int() {
    }

    /**
     * Test of getOpdf method, of class InputHmmBase.
     */
    @Test
    public void testGetOpdf_int_GenericType() {
    }

    /**
     * Test of getOpdf method, of class InputHmmBase.
     */
    @Test
    public void testGetOpdf_int_Tagable() {
    }

    /**
     * Test of collapsedA method, of class InputHmmBase.
     */
    @Test
    public void testCollapsedA() {
        for (int t = 0x00; t < TestParameters.NUMBER_OF_TESTS; t++) {
            int m = ProbabilityUtils.nextInt(TestParameters.TEST_SIZE_SMALL) + 0x01;
            TrisEnum[] ti = new TrisEnum[]{TrisEnum.Odin, TrisEnum.Dva};
            int n = ti.length;
            InputHmmBase<ObservationEnum<TrisEnum>, TrisEnum> hmm = generateRandomIHmm1(m, ti, n);
            double[][] cola = hmm.collapsedA();
            for (int i = 0x00; i < m; i++) {
                for (int k = 0x00; k < m; k++) {
                    AssertExtensions.assertEquals(hmm.getAij(i, k), cola[i][k]);

                }
            }
        }
    }

    private InputHmmBase<ObservationEnum<TrisEnum>, TrisEnum> generateRandomIHmm1(int m, TrisEnum[] ti, int n) {
        InputHmmBase<ObservationEnum<TrisEnum>, TrisEnum> hmm = new InputHmmBase<>(m, new OpdfEnumFactory<>(TrisEnum.class
        ), ti);
        for (int i = 0x00;
                i < m;
                i++) {
            for (int j = 0x00; j < n; j++) {
                for (int k = 0x00; k < m; k++) {
                    hmm.setAixj(i, j, k, ProbabilityUtils.nextDouble());
                }
            }
        }
        return hmm;
    }

    /**
     * Test of fold method, of class InputHmmBase.
     */
    @Test
    public void testFold_GenericType() {
    }

}
