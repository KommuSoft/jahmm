/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package be.ac.ulg.montefiore.run.jahmm.test;

import be.ac.ulg.montefiore.run.jahmm.Hmm;
import be.ac.ulg.montefiore.run.jahmm.ObservationInteger;
import be.ac.ulg.montefiore.run.jahmm.OpdfIntegerFactory;
import be.ac.ulg.montefiore.run.jahmm.draw.GenericHmmDrawerDot;
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

    protected void setUp() {
        hmm = new Hmm<ObservationInteger>(4, new OpdfIntegerFactory(2));
    }

    /**
     *
     */
    public void testDotGenerator() {
        GenericHmmDrawerDot hmmDrawer = new GenericHmmDrawerDot();

        try {
            hmmDrawer.write(hmm, outputDir + "hmm-generate.dot");
        } catch (IOException e) {
            assertTrue("Writing file triggered an exception: " + e, false);
        }
    }
}
