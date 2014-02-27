package jahmm.observables;

import java.text.NumberFormat;
import jutlis.tuples.Tuple2Base;

/**
 *
 * @author kommusoft
 */
public class InputObservationTuple<TInt extends Enum<TInt>, TObs extends Observation> extends Tuple2Base<ObservationDiscrete<TInt>, TObs> implements Observation {

    public InputObservationTuple(ObservationDiscrete<TInt> item1, TObs item2) {
        super(item1, item2);
    }

    @Override
    public String toString() {
        return this.toString(NumberFormat.getInstance());
    }

    @Override
    public String toString(NumberFormat numberFormat) {
        return String.format("< %s ; %s >", this.getItem1().toString(), this.getItem2().toString(numberFormat));
    }

}
