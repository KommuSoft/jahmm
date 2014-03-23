package jahmm.learn;

import jahmm.RegularHmm;
import jahmm.RegularHmmBase;
import jahmm.observables.ObservationInteger;
import jahmm.observables.OpdfIntegerFactory;
import jahmm.toolbox.KullbackLeiblerDistanceCalculator;
import jahmm.toolbox.RegularMarkovGeneratorBase;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author kommusoft
 */
public class KMeansLearnerTest extends TestCase {
    
    final static private double DELTA = 5.E-3;

    private RegularHmm<ObservationInteger> hmm;
    private List<List<ObservationInteger>> sequences;
    private KullbackLeiblerDistanceCalculator klc;

    @Override
    protected void setUp() {
        hmm = new RegularHmmBase<>(3, new OpdfIntegerFactory(10));
        hmm.getOpdf(0).fit(new ObservationInteger(1), new ObservationInteger(2));

        RegularMarkovGeneratorBase<ObservationInteger> mg = new RegularMarkovGeneratorBase<>(hmm);

        sequences = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            sequences.add(mg.observationSequence(100));
        }

        klc = new KullbackLeiblerDistanceCalculator();
    }

    /**
     *
     * @throws java.lang.CloneNotSupportedException
     */
    public void testKMeans() throws CloneNotSupportedException {
        KMeansLearner<ObservationInteger> kml = new KMeansLearner<>(5, new OpdfIntegerFactory(10), sequences);
        assertEquals(0., klc.distance(kml.learn(), hmm), DELTA);
    }
    
}
