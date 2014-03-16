package jahmm.jadetree.objectattributes;

import java.util.logging.Logger;

public class InternalOrdinalObjectAttributeBase<TSource extends Comparable<TSource>> extends OrdinalObjectAttributeBase<TSource, TSource> {

    private static final Logger LOG = Logger.getLogger(InternalOrdinalObjectAttributeBase.class.getName());

    private final String name;

    public InternalOrdinalObjectAttributeBase(String name) {
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
            return -0x01;
        }
    }

}
