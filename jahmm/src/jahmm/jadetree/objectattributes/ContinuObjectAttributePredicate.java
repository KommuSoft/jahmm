package jahmm.jadetree.objectattributes;

import java.util.logging.Logger;
import jutlis.algebra.PredicateBase;

/**
 *
 * @author kommusoft
 * @param <TSource>
 * @param <TTarget>
 */
public class ContinuObjectAttributePredicate<TSource, TTarget> extends PredicateBase<TSource> {

    private static final Logger LOG = Logger.getLogger(ContinuObjectAttributePredicate.class.getName());

    private final ContinuObjectAttribute<TSource, TTarget> coa;
    private final TTarget decidePoint;

    public ContinuObjectAttributePredicate(ContinuObjectAttribute<TSource, TTarget> coa, TTarget decidePoint) {
        this.coa = coa;
        this.decidePoint = decidePoint;
    }

    @Override
    public Boolean evaluate(TSource x) {
        return coa.compareWith(x, decidePoint) <= 0x00;
    }

}
