/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package jahmm;

import jahmm.observables.ObservationInteger;
import jahmm.observables.OpdfIntegerFactory;
import jahmm.draw.InvariantHmmDotDrawer;
import java.io.IOException;
import junit.framework.TestCase;

/**
 *
 * @author kommusoft
 */
public class GenerateTest
        extends TestCase {

    /**
     *
     */
    public final static String outputDir = "";

    private Hmm<ObservationInteger> hmm;

    @Override
    protected void setUp() {
        hmm = new Hmm<>(4, new OpdfIntegerFactory(2));
    }

    /**
     *
     */
    public void testDotGenerator() {
        try {
            InvariantHmmDotDrawer.Instance.write(hmm, outputDir + "hmm-generate.dot");
        } catch (IOException e) {
            assertTrue("Writing file triggered an exception: " + e, false);
        }
    }
}
