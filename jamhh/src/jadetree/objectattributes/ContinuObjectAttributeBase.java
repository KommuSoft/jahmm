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

public abstract class ContinuObjectAttributeBase<TSource, TTarget> extends OrdinalObjectAttributeBase<TSource,TTarget> implements ContinuObjectAttribute<TSource, TTarget> {

    @Override
    public TTarget getBetween(TSource source1, TSource source2) {
        return this.getMiddle(source1, source2);
    }

}
