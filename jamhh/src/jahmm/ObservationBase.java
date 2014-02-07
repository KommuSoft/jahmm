package jahmm;

import java.text.NumberFormat;

/**
 *
 * @author kommusoft
 */
public abstract class ObservationBase implements Observation {

    @Override
    public String toString() {
        return this.toString(NumberFormat.getInstance());
    }

}
