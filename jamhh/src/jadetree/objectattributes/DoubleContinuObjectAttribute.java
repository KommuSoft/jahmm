package jadetree.objectattributes;

import jadetree.DecisionNode;
import jadetree.DecisionTreeUtils;
import java.util.Collections;
import java.util.List;
import jutlis.algebra.Function;
import jutlis.tuples.Holder;
import jutlis.tuples.Tuple2;
import jutlis.tuples.Tuple2Base;

/**
 *
 * @author kommusoft
 * @param <TSource>
 */
public abstract class DoubleContinuObjectAttribute<TSource> extends ContinuObjectAttributeBase<TSource, Double> {

    @Override
    public Double getBetween(TSource source1, TSource source2) {
        return 0.5d * (this.evaluate(source1) + this.evaluate(source2));
    }

    @Override
    public int compareWith(TSource source, Double target) {
        return this.evaluate(source).compareTo(target);
    }

    @Override
    public DecisionNode<TSource> createDecisionNode(List<? extends TSource> source, Function<? super TSource, ? extends Object> target, Holder<Object> state) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compare(TSource source1, TSource source2) {
        return this.evaluate(source1).compareTo(this.evaluate(source2));
    }

}
