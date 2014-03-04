package jahmm.jadetree.objectattributes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kommusoft
 * @param <TSource>
 * @param <TTarget>
 */
public class OrdinalInspectedObjectAttribute<TSource, TTarget> extends OrdinalObjectAttributeBase<TSource, TTarget> {

    private static final Logger LOG = Logger.getLogger(OrdinalInspectedObjectAttribute.class.getName());

    private final String name;
    private final Method method;

    public OrdinalInspectedObjectAttribute(Method method, String name, Class<? extends TTarget> result) {
        if (!Comparable.class.isAssignableFrom(result) || method.getReturnType() != result) {
            throw new IllegalArgumentException("The resulting class must be comparable and must be the result of the given method.");
        }
        this.name = name;
        this.method = method;
    }

    @Override
    public int compareWith(TSource source, TTarget target) {
        return ((Comparable<TTarget>) this.evaluate(source)).compareTo(target);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    @SuppressWarnings("unchecked")
    public TTarget evaluate(TSource x) {
        try {
            return (TTarget) this.method.invoke(x, new Object[0x00]);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(OrdinalInspectedObjectAttribute.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int compare(TSource t1, TSource t2) {
        @SuppressWarnings("unchecked")
        Comparable<TTarget> r1 = (Comparable<TTarget>) this.evaluate(t1);
        TTarget r2 = this.evaluate(t2);
        return r1.compareTo(r2);
    }

}
