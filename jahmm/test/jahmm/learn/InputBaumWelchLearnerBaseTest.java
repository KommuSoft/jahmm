package jahmm.learn;

import jahmm.InputHmm;
import jahmm.InputHmmBase;
import jahmm.RegularHmm;
import jahmm.RegularHmmBase;
import jahmm.observables.ObservationInteger;
import jahmm.observables.OpdfIntegerFactory;
import jahmm.toolbox.KullbackLeiblerDistanceCalculator;
import jahmm.toolbox.RegularMarkovGeneratorBase;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author kommusoft
 */
public class InputBaumWelchLearnerBaseTest extends TestCase {

    final static private double DELTA = 5.E-3;

    private RegularHmm<ObservationInteger> hmm;
    private InputHmm<ObservationInteger, Integer> ihmm;
    private List<List<ObservationInteger>> sequences;
    private List<List<ObservationInteger>> isequences;
    private KullbackLeiblerDistanceCalculator klc;

    @Override
    protected void setUp() {
        hmm = new RegularHmmBase<>(3, new OpdfIntegerFactory(10));
        hmm.getOpdf(0).fit(new ObservationInteger(1), new ObservationInteger(2));

        ihmm = new InputHmmBase<>(3, new OpdfIntegerFactory(10), (Integer) 0);
        ihmm.getOpdf(0, 0).fit(new ObservationInteger(1), new ObservationInteger(2));

        RegularMarkovGeneratorBase<ObservationInteger> mg = new RegularMarkovGeneratorBase<>(hmm);

        sequences = new ArrayList<>();
        isequences = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            sequences.add(mg.observationSequence(100));
        }

        klc = new KullbackLeiblerDistanceCalculator();
    }

    /**
     *
     */
    @Test
    public void testThinBaumWelch() {
        //TODO: input instead of regular.
        /* Model sequences using BW algorithm */
        /*RegularBaumWelchLearnerBase<ObservationInteger> bwl = new RegularBaumWelchLearnerBase<>();
        RegularHmm<ObservationInteger> bwHmm = bwl.learn(hmm, sequences);
        InputBaumWelchLearnerBase<ObservationInteger, Integer> ibwl = new InputBaumWelchLearnerBase<>();
        InputHmm<ObservationInteger, Integer> ibwHmm = ibwl.learn(ihmm, isequences);

        assertEquals(0., klc.distance(bwHmm, hmm), DELTA);

        /* Model sequences using the scaled BW algorithm */
        /*RegularBaumWelchScaledLearnerBase<ObservationInteger> bwsl = new RegularBaumWelchScaledLearnerBase<>();
        bwHmm = bwsl.learn(hmm, sequences);

        assertEquals(0., klc.distance(bwHmm, hmm), DELTA);*/
    }

}
