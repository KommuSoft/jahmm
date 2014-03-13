package jahmm.jadetree.objectattributes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kommusoft
 * @param <TSource>
 */
public class DoubleInspectedContinuObjectAttribute<TSource> extends DoubleContinuObjectAttributeBase<TSource> {

    private static final Logger LOG = Logger.getLogger(DoubleInspectedContinuObjectAttribute.class.getName());

    private final String name;
    private final Method method;

    public DoubleInspectedContinuObjectAttribute(Method method, String name, Class<?> result) {
        this.name = name;
        this.method = method;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Double evaluate(TSource x) {
        try {
            return (Double) this.method.invoke(x, new Object[0x00]);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(FloatInspectedContinuObjectAttribute.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
