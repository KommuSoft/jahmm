/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package jahmm;

import jahmm.draw.HmmDotDrawer;
import jahmm.observables.ObservationInteger;
import jahmm.observables.OpdfIntegerFactory;
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

    private RegularHmmBase<ObservationInteger> hmm;

    @Override
    protected void setUp() {
        hmm = new RegularHmmBase<>(4, new OpdfIntegerFactory(2));
    }

    /**
     *
     */
    public void testDotGenerator() {
        try {
            HmmDotDrawer.Instance.write(hmm, outputDir + "hmm-generate.dot");
        } catch (IOException e) {
            assertTrue("Writing file triggered an exception: " + e, false);
        }
    }
}
