package jahmm;

import jahmm.observables.Observation;

/**
 *
 * @author kommusoft
 * @param <TObs>
 */
public interface RegularHmm<TObs extends Observation> extends Hmm<TObs, TObs> {

}
