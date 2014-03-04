package jahmm.jadetree.objectattributes;

import java.lang.reflect.Method;
import java.util.logging.Logger;

/**
 *
 * @author kommusoft
 * @param <TSource>
 */
public class FloatInspectedContinuObjectAttribute<TSource> extends FloatContinuObjectAttributeBase<TSource> {

    private final String name;
    private final Method method;

    public FloatInspectedContinuObjectAttribute(Method method, String name, Class<?> result) {
        this.name = name;
        this.method = method;
    }
    private static final Logger LOG = Logger.getLogger(FloatInspectedContinuObjectAttribute.class.getName());

    @Override
    public String getName() {
        return this.getName();
    }

    @Override
    public Float evaluate(TSource x) {
        return (Float) this.evaluate(x);
    }

}
