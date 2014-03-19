package jahmm;

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
public class HmmBaseTest {

    public HmmBaseTest() {
    }

    /**
     * Test of generatePi method, of class HmmBase.
     */
    @Test
    public void testGeneratePi00() {
        try {
            HmmBase.generatePi(0x00);
            fail("Should throw an exception.");
        } catch (IllegalArgumentException t) {
        }
    }

    /**
     * Test of generatePi method, of class HmmBase.
     */
    @Test
    public void testGeneratePi01() {
        try {
            HmmBase.generatePi(-0x01);
            fail("Should throw an exception.");
        } catch (IllegalArgumentException t) {
        }
    }

    /**
     * Test of generatePi method, of class HmmBase.
     */
    @Test
    public void testGeneratePi02() {
        try {
            HmmBase.generatePi(-Integer.MIN_VALUE);
            fail("Should throw an exception.");
        } catch (IllegalArgumentException t) {
        }
    }

    /**
     * Test of generatePi method, of class HmmBase.
     */
    @Test
    public void testGeneratePi03() {
        double[] vals = HmmBase.generatePi(0x01);
        Assert.assertEquals(0x01, vals.length);
        AssertExtensions.assertEquals(1.0d, vals[0x00]);
    }

    /**
     * Test of generatePi method, of class HmmBase.
     */
    @Test
    public void testGeneratePi04() {
        for (int t = 0x00; t < TestParameters.NUMBER_OF_TESTS; t++) {
            int l = ProbabilityUtils.nextInt(TestParameters.TEST_SIZE) + 0x01;
            double[] vals = HmmBase.generatePi(l);
            Assert.assertEquals(l, vals.length);
            double total = 0.0d;
            for (int i = 0x00; i < l; i++) {
                total += vals[i];
                for (int j = i + 0x01; j < l; j++) {
                    AssertExtensions.assertEquals(vals[i], vals[j]);
                }
            }
            AssertExtensions.assertEquals(1.0d, total);
        }
    }

}
