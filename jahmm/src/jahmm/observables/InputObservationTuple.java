package jahmm.observables;

import java.text.NumberFormat;
import jutlis.tuples.Tuple2Base;

/**
 *
 * @author kommusoft
 */
public class InputObservationTuple<TInput, TObservation extends Observation> extends Tuple2Base<TInput, TObservation> implements Observation {

    public InputObservationTuple(TInput item1, TObservation item2) {
        super(item1, item2);
    }

    public TInput getInput() {
        return this.getItem1();
    }

    public TObservation getObservation() {
        return this.getItem2();
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
