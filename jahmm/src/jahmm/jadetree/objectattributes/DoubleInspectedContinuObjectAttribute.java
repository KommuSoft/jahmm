package jahmm.jadetree.objectattributes;

import java.lang.reflect.Method;

/**
 *
 * @author kommusoft
 * @param <TSource>
 */
public class DoubleInspectedContinuObjectAttribute<TSource> extends DoubleContinuObjectAttributeBase<TSource> {

    private final String name;
    private final Method method;

    public DoubleInspectedContinuObjectAttribute(Method method, String name, Class<?> result) {
        this.name = name;
        this.method = method;
    }

    @Override
    public String getName() {
        return this.getName();
    }

    @Override
    public Double evaluate(TSource x) {
        return (Double) this.evaluate(x);
    }

}
