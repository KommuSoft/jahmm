package jahmm.observables;

import java.text.NumberFormat;
import jutlis.tuples.Tuple2Base;

/**
 *
 * @author kommusoft
 */
public class InputObservationTuple<I extends Enum<I>, O extends Observation> extends Tuple2Base<ObservationDiscrete<I>, Observation> implements Observation {

    public InputObservationTuple(ObservationDiscrete<I> item1, Observation item2) {
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
