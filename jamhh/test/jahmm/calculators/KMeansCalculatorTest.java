package jahmm.calculators;

import java.util.Collection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author kommusoft
 */
public class KMeansCalculatorTest {

    public KMeansCalculatorTest() {
    }

    /**
     * Test of cluster method, of class KMeansCalculator.
     */
    @Test
    public void testCluster() {
        System.out.println("cluster");
        int index = 0;
        KMeansCalculator instance = null;
        Collection expResult = null;
        Collection result = instance.cluster(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
