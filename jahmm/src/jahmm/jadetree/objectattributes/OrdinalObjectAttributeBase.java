package jahmm.jadetree.objectattributes;

import jahmm.jadetree.DecisionInode;
import jahmm.jadetree.DecisionRealNode;
import jahmm.jadetree.DecisionTreeUtils;
import jahmm.jadetree.OrdinalTestDecisionNode;
import java.util.Collections;
import java.util.List;
import jutlis.algebra.Function;
import jutlis.tuples.Holder;
import jutlis.tuples.Tuple2;
import jutlis.tuples.Tuple2Base;

/**
 *
 * @author kommusoft
 * @param <TSource> The domain of the function.
 * @param <TTarget> The range of the function.
 */
public abstract class OrdinalObjectAttributeBase<TSource, TTarget> extends ObjectAttributeBase<TSource, TTarget> implements OrdinalObjectAttribute<TSource, TTarget> {

    @Override
    public TTarget getBetween(TSource source1, TSource source2) {
        if (this.compare(source1, source2) <= 0x00) {
            return this.evaluate(source1);
        } else {
            return this.evaluate(source2);
        }
    }

    @Override
    public double calculateScore(Function<TSource, ? extends Object> function, Holder<Object> state, List<TSource> source) {
        Collections.sort(source, this);
        Tuple2<Integer, Double> te = new Tuple2Base<>();
        int split = DecisionTreeUtils.calculateEntropyFlipIndex(source, function, te);
        if (state != null) {
            this.getBetween(source.get(split), source.get(split + 0x01));
            state.setData(split);
        }
        return te.getItem2();
    }

    @Override
    public DecisionRealNode<TSource> createDecisionNode(DecisionInode<TSource> parent, List<TSource> source, Function<TSource, ? extends Object> function, Holder<Object> state) {
        @SuppressWarnings("unchecked")
        TTarget obj = (TTarget) state.getData();
        OrdinalTestDecisionNode<TSource, TTarget> otdn = new OrdinalTestDecisionNode<>(parent, this, obj);
        return otdn;
    }

}
