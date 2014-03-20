package jahmm.observables;

import java.text.NumberFormat;

/**
 *
 * @author kommusoft
 * @param <TType> The type of the observation.
 */
public abstract class ObservationBase<TType> implements TypedObservation<TType> {

    @Override
    public String toString() {
        return this.toString(NumberFormat.getInstance());
    }

}
