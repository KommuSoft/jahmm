package jahmm.observables;

import jutils.Tagable;

/**
 *
 * @author kommusoft
 * @param <TType> The type of the observation.
 */
public interface TypedObservation<TType> extends Observation, Tagable<TType> {

}