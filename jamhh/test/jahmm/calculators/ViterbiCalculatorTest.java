package jahmm.calculators;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author kommusoft
 */
public class ViterbiCalculatorTest {

    public ViterbiCalculatorTest() {
    }

    /**
     * Test of lnProbability method, of class ViterbiCalculator.
     */
    @Test
    public void testLnProbability() {
        System.out.println("lnProbability");
        ViterbiCalculator instance = null;
        double expResult = 0.0;
        double result = instance.lnProbability();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stateSequence method, of class ViterbiCalculator.
     */
    @Test
    public void testStateSequence() {
        System.out.println("stateSequence");
        ViterbiCalculator instance = null;
        int[] expResult = null;
        int[] result = instance.stateSequence();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
