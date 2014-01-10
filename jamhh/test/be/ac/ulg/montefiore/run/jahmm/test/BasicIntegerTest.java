/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package be.ac.ulg.montefiore.run.jahmm.test;

import be.ac.ulg.montefiore.run.jahmm.ForwardBackwardCalculator;
import be.ac.ulg.montefiore.run.jahmm.ForwardBackwardScaledCalculator;
import be.ac.ulg.montefiore.run.jahmm.Hmm;
import be.ac.ulg.montefiore.run.jahmm.KMeansCalculator;
import be.ac.ulg.montefiore.run.jahmm.ObservationInteger;
import be.ac.ulg.montefiore.run.jahmm.OpdfInteger;
import be.ac.ulg.montefiore.run.jahmm.OpdfIntegerFactory;
import be.ac.ulg.montefiore.run.jahmm.ViterbiCalculator;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author kommusoft
 */
public class BasicIntegerTest
        extends TestCase {

    final static private double DELTA = 1.E-10;

    private Hmm<ObservationInteger> hmm;
    private List<ObservationInteger> sequence;
    private List<ObservationInteger> randomSequence;

    @Override
    protected void setUp() {
        hmm = new Hmm<>(5, new OpdfIntegerFactory(10));
        hmm.setOpdf(1, new OpdfInteger(6));

        sequence = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            sequence.add(new ObservationInteger(i));
        }

        randomSequence = new ArrayList<>();
        for (int i = 0; i < 30_000; i++) {
            randomSequence.
                    add(new ObservationInteger((int) (Math.random() * 10.)));
        }
    }

    /**
     *
     */
    public void testForwardBackward() {
        ForwardBackwardCalculator fbc
                = new ForwardBackwardCalculator(sequence, hmm);

        assertEquals(1.8697705349794245E-5, fbc.probability(), DELTA);

        ForwardBackwardScaledCalculator fbsc
                = new ForwardBackwardScaledCalculator(sequence, hmm);

        assertEquals(1.8697705349794245E-5, fbsc.probability(), DELTA);
    }

    /**
     *
     */
    public void testViterbi() {
        ViterbiCalculator vc = new ViterbiCalculator(sequence, hmm);

        assertEquals(4.1152263374485705E-8,
                Math.exp(vc.lnProbability()), DELTA);
    }

    /**
     *
     * @throws java.lang.CloneNotSupportedException
     */
    public void testKMeansCalculator() throws CloneNotSupportedException {
        int nbClusters = 20;

        KMeansCalculator<ObservationInteger> kmc = new KMeansCalculator<>(nbClusters, randomSequence);

        assertEquals("KMeans did not produce expected number of clusters",
                nbClusters, kmc.nbClusters());
    }
}