/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jadetree;

import java.util.logging.Logger;
import jutlis.algebra.Function;
import org.junit.Test;

/**
 *
 * @author kommusoft
 */
public class DecisionTreeUtilsTest {

    private static final Logger LOG = Logger.getLogger(DecisionTreeUtilsTest.class.getName());

    public DecisionTreeUtilsTest() {
    }

    @Test
    public void testCalculateEntropy_4args() {
        double expResult = 0.0;
        double result = DecisionTreeUtils.calculateEntropy(null);
        /*
         assertEquals(expResult, result, 0.0);
         // TODO review the generated test code and remove the default call to fail.
         fail("The test case is a prototype.");//*/
    }

    @Test
    public void testCalculateEntropy_Iterable_Function() {
        double expResult = 0.0;
        /*double result = DecisionTreeUtils.calculateEntropy(null);
         assertEquals(expResult, result, 0.0);
         // TODO review the generated test code and remove the default call to fail.
         fail("The test case is a prototype.");//*/
    }

    @Test
    public void testCalculateEntropyFlipIndex() {
        int expResult = 0;
        /*int result = DecisionTreeUtils.calculateEntropyFlipIndex(null);
         assertEquals(expResult, result);
         // TODO review the generated test code and remove the default call to fail.
         fail("The test case is a prototype.");//*/
    }

    @Test
    public void testCalculateEntropyPartition() {
        double expResult = 0.0;
        /*double result = DecisionTreeUtils.calculateEntropyPartition(null);
         assertEquals(expResult, result, 0.0);
         // TODO review the generated test code and remove the default call to fail.
         fail("The test case is a prototype.");//*/
    }

    private class Foo implements Function<Foo, Integer> {

        private int value;

        public Foo(int value) {
            this.value = value;
        }

        @Override
        public Integer evaluate(Foo x) {
            return x.value;
        }

    }

}
