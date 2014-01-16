package be.ac.ulg.montefiore.run.jahmm;

import java.text.NumberFormat;
import jutils.tuples.Tuple2;

/**
 *
 * @author kommusoft
 */
public class InputObservationTuple<I,O extends Observation> extends Tuple2<I,Observation> implements Observation {

    public InputObservationTuple(I item1, Observation item2) {
        super(item1, item2);
    }

    @Override
    public String toString() {
        return this.toString(NumberFormat.getInstance());
    }
    
    @Override
    public String toString(NumberFormat numberFormat) {
        return String.format("< %s ; %s >", this.getItem1().toString(),this.getItem2().toString(numberFormat));
    }
    
}
