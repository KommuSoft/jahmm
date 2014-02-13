package objectattributes;

import java.util.Collections;
import java.util.List;
import jutlis.tuples.Holder;

/**
 *
 * @author kommusoft
 */
public abstract class DoubleContinuObjectAttribute<TSource> implements ContinuObjectAttribute<TSource, Double> {

    @Override
    public Double getBetween(TSource source1, TSource source2) {
        return 0.5d * (this.evaluate(source1) + this.evaluate(source2));
    }

    @Override
    public int compareWith(TSource source, Double target) {
        return this.evaluate(source).compareTo(target);
    }

    @Override
    public double calculateScore(List<? extends TSource> list, Holder<Object> state) {
        Collections.sort(list, this);
        int n = list.size();
        for(int i = 0x01; i < n; i++) {
            
        }
        return Double.NaN;//TODO: implement.
    }

    @Override
    public void createDecisionNode(List<? extends TSource> source, Holder<Object> state) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compare(TSource source1, TSource source2) {
        return this.evaluate(source1).compareTo(this.evaluate(source2));
    }

}
