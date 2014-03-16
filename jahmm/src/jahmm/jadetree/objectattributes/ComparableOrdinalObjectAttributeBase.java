package jahmm.jadetree.objectattributes;

import java.util.logging.Logger;

public class ComparableOrdinalObjectAttributeBase<TSource extends Comparable<TSource>> extends OrdinalObjectAttributeBase<TSource, TSource> {

    private static final Logger LOG = Logger.getLogger(ComparableOrdinalObjectAttributeBase.class.getName());

    private final String name;

    public ComparableOrdinalObjectAttributeBase(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public TSource evaluate(TSource x) {
        return x;
    }

    @Override
    public int compareWith(TSource source, TSource target) {
        return compare(source, target);
    }

    @Override
    public int compare(TSource t, TSource t1) {
        if (t != null && t1 != null) {
            return t.compareTo(t1);
        } else {
            return 0x00;
        }
    }

}
