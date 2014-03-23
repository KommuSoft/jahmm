package jahmm;

import jahmm.jadetree.foo.FooEnum;
import jahmm.jadetree.foo.TrisEnum;
import jahmm.observables.ObservationEnum;
import jahmm.observables.OpdfEnum;
import jahmm.observables.OpdfEnumFactory;
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
            while(ProbabilityUtils.nextBoolean(0.85d)) {
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
            InputHmmBase<ObservationEnum<FooEnum>, FooEnum> hmm = new InputHmmBase<>(N, new OpdfEnumFactory<>(FooEnum.class), inputs);
            InputHmmBase<ObservationEnum<FooEnum>, FooEnum> hmm2 = hmm.clone();
            Assert.assertEquals(inputs.length, hmm2.nbSymbols());
            double pa = ProbabilityUtils.nextDouble();
            double pb = ProbabilityUtils.nextDouble();
            hmm2.setAixj(0x00, FooEnum.Foobar, 0x00, pa);
            hmm2.setAixj(0x00, FooEnum.Foobar, 0x01, 1.0d - pa);
            hmm2.setAixj(0x01, FooEnum.Foobar, 0x00, pb);
            hmm2.setAixj(0x01, FooEnum.Foobar, 0x01, 1.0 - pb);
            hmm2.splitInput(FooEnum.Foobar, splitres);
            Assert.assertEquals(inputs.length + splitres.length - 0x01, hmm2.nbSymbols());
            AssertExtensions.assertEquals(pa, hmm2.getAixj(0x00, FooEnum.Foo, 0x00));
            AssertExtensions.assertEquals(1.0d - pa, hmm2.getAixj(0x00, FooEnum.Foo, 0x01));
            AssertExtensions.assertEquals(pb, hmm2.getAixj(0x01, FooEnum.Foo, 0x00));
            AssertExtensions.assertEquals(1.0d - pb, hmm2.getAixj(0x01, FooEnum.Foo, 0x01));
            AssertExtensions.assertEquals(pa, hmm2.getAixj(0x00, FooEnum.Bar, 0x00));
            AssertExtensions.assertEquals(1.0d - pa, hmm2.getAixj(0x00, FooEnum.Bar, 0x01));
            AssertExtensions.assertEquals(pb, hmm2.getAixj(0x01, FooEnum.Bar, 0x00));
            AssertExtensions.assertEquals(1.0d - pb, hmm2.getAixj(0x01, FooEnum.Bar, 0x01));
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
            InputHmmBase<ObservationEnum<FooEnum>, FooEnum> hmm = new InputHmmBase<>(N, new OpdfEnumFactory<>(FooEnum.class), inputs);
            InputHmmBase<ObservationEnum<FooEnum>, FooEnum> hmm2 = hmm.clone();
            Assert.assertEquals(inputs.length, hmm2.nbSymbols());
            double pa = ProbabilityUtils.nextDouble();
            double pb = ProbabilityUtils.nextDouble();
            double pc = ProbabilityUtils.nextDouble();
            double pd = ProbabilityUtils.nextDouble();
            hmm2.setAixj(0x00, FooEnum.Foo, 0x00, pa);
            hmm2.setAixj(0x00, FooEnum.Foo, 0x01, 1.0d - pa);
            hmm2.setAixj(0x01, FooEnum.Foo, 0x00, pb);
            hmm2.setAixj(0x01, FooEnum.Foo, 0x01, 1.0 - pb);
            hmm2.setAixj(0x00, FooEnum.Bar, 0x00, pc);
            hmm2.setAixj(0x00, FooEnum.Bar, 0x01, 1.0d - pc);
            hmm2.setAixj(0x01, FooEnum.Bar, 0x00, pd);
            hmm2.setAixj(0x01, FooEnum.Bar, 0x01, 1.0 - pd);
            hmm2.mergeInput(FooEnum.Foobar, tomerge);
            Assert.assertEquals(inputs.length - tomerge.length + 0x01, hmm2.nbSymbols());
            AssertExtensions.assertEquals(0.5d * (pa + pc), hmm2.getAixj(0x00, FooEnum.Foobar, 0x00));
            AssertExtensions.assertEquals(1.0d - 0.5d * (pa + pc), hmm2.getAixj(0x00, FooEnum.Foobar, 0x01));
            AssertExtensions.assertEquals(0.5d * (pb + pd), hmm2.getAixj(0x01, FooEnum.Foobar, 0x00));
            AssertExtensions.assertEquals(1.0d - 0.5d * (pb + pd), hmm2.getAixj(0x01, FooEnum.Foobar, 0x01));
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
            InputHmmBase<ObservationEnum<FooEnum>, FooEnum> hmm = new InputHmmBase<>(N, new OpdfEnumFactory<>(FooEnum.class), bag);
            for (Integer index : hmm.getIndexRegister().values()) {
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
            InputHmmBase<ObservationEnum<FooEnum>, FooEnum> hmm = new InputHmmBase<>(N, new OpdfEnumFactory<>(FooEnum.class), inputs);
            for (Integer index : hmm.getIndexRegister().values()) {
                Assert.assertNotNull(index);
                int idx = index;
                AssertExtensions.assertLessThan(idx, hmm.nbSymbols());
                AssertExtensions.assertGreaterThanOrEqual(idx, 0x00);
            }
            hmm.splitInput(FooEnum.Foobar, splitres);
            for (Integer index : hmm.getIndexRegister().values()) {
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
            InputHmmBase<ObservationEnum<FooEnum>, FooEnum> hmm = new InputHmmBase<>(N, new OpdfEnumFactory<>(FooEnum.class), inputs);
            InputHmmBase<ObservationEnum<FooEnum>, FooEnum> hmm2 = hmm.clone();
            Assert.assertEquals(inputs.length, hmm2.nbSymbols());
            for (Integer index : hmm.getIndexRegister().values()) {
                Assert.assertNotNull(index);
                int idx = index;
                AssertExtensions.assertLessThan(idx, hmm.nbSymbols());
                AssertExtensions.assertGreaterThanOrEqual(idx, 0x00);
            }
            hmm2.mergeInput(FooEnum.Foobar, tomerge);
            for (Integer index : hmm.getIndexRegister().values()) {
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
        InputHmmBase<ObservationEnum<TrisEnum>, TrisEnum> hmm = new InputHmmBase<>(m, new OpdfEnumFactory<>(TrisEnum.class), ti);
        for (int i = 0x00; i < m; i++) {
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
