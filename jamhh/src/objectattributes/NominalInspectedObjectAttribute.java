package objectattributes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

/**
 *
 * @author kommusoft
 */
public class NominalInspectedObjectAttribute<TSource,TTarget> extends NominalObjectAttribute<TSource,TTarget> {
    
    private final String name;
    private final Method method;

    NominalInspectedObjectAttribute(Method method, String name) {
        this.name = name;
        this.method = method;
    }

    @Override
    public Set<TTarget> getPossibleValues() {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public TTarget getAttribute(TSource source) throws IllegalAccessException, InvocationTargetException {
        return (TTarget) this.method.invoke(source);
    }

    @Override
    public String getName() {
        return this.name;
    }
    
}
