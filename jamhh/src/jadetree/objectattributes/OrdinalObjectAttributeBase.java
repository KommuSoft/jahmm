package jadetree.objectattributes;

import jadetree.DecisionNode;
import jadetree.DecisionNodeBase;
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
 */
public abstract class OrdinalObjectAttributeBase<TSource, TTarget> implements OrdinalObjectAttribute<TSource, TTarget> {

    @Override
    public TTarget getBetween(TSource source1, TSource source2) {
        if (this.compare(source1, source2) <= 0x00) {
            return this.evaluate(source1);
        } else {
            return this.evaluate(source2);
        }
    }

    @Override
    public int compareWith(TSource source, TTarget target) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double calculateScore(List<? extends TSource> list, Function<? super TSource, ? extends Object> target, Holder<Object> state) {
        Collections.sort(list, this);
        Tuple2<Integer, Double> te = new Tuple2Base<>();
        int split = DecisionTreeUtils.calculateEntropyFlipIndex(state, target, te);
        if (state != null) {
            this.getBetween(list.get(split), list.get(split + 0x01));
            state.setData(split);
        }
        return te.getItem2();
    }

    @Override
    public DecisionNodeBase<TSource> createDecisionNode(DecisionNode<TSource> parent, List<? extends TSource> source, Function<? super TSource, ? extends Object> function, Holder<Object> state) {
        //return new PredicateDecisionNode<>(parent,new );
        return null;
    }

}
