package jadetree.objectattributes;

import jadetree.DecisionNode;
import jadetree.DecisionTreeUtils;
import java.util.Collections;
import java.util.List;
import jutlis.algebra.Function;
import jutlis.tuples.Holder;
import jutlis.tuples.Tuple2;
import jutlis.tuples.Tuple2Base;

public abstract class ContinuObjectAttributeBase<TSource, TTarget> implements ContinuObjectAttribute<TSource, TTarget> {

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
    public DecisionNode<TSource> createDecisionNode(List<? extends TSource> source, Function<? super TSource, ? extends Object> function, Holder<Object> state) {
        //return new PredicateDecisionNode<>();
        return null;
    }

}
