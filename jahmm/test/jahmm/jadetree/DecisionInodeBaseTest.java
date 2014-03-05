/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jahmm.jadetree;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kommusoft
 */
public class DecisionInodeBaseTest {
    
    public DecisionInodeBaseTest() {
    }

    /**
     * Test of getPartitionedStoredSources method, of class DecisionInodeBase.
     */
    @Test
    public void testGetPartitionedStoredSources() {
        System.out.println("getPartitionedStoredSources");
        DecisionInodeBase instance = null;
        Iterable<Iterable<TSource>> expResult = null;
        Iterable<Iterable<TSource>> result = instance.getPartitionedStoredSources();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStoredSources method, of class DecisionInodeBase.
     */
    @Test
    public void testGetStoredSources() {
        System.out.println("getStoredSources");
        DecisionInodeBase instance = null;
        Iterable expResult = null;
        Iterable result = instance.getStoredSources();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class DecisionInodeBaseImpl extends DecisionInodeBase {

        public DecisionInodeBaseImpl() {
            super(null);
        }
    }
    
}
