package objectattributes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import utils.Utils;

/**
 *
 * @author kommusoft
 * @param <TSource>
 * @param <TTarget>
 */
public class NominalInspectedObjectAttribute<TSource, TTarget> extends NominalObjectAttributeBase<TSource, TTarget> {

    private final String name;
    private final Method method;
    private final Class<?> resultclass;

    NominalInspectedObjectAttribute(Method method, String name, Class<?> resultclass) {
        this.name = name;
        this.method = method;
        this.resultclass = resultclass;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<TTarget> getPossibleValues() {
        return (Set<TTarget>) Utils.getNominalSet(this.resultclass);
    }

    @Override
    @SuppressWarnings("unchecked")
    public TTarget evaluate(TSource source) {
        try {
            return (TTarget) this.method.invoke(source);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            return null;
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

}
