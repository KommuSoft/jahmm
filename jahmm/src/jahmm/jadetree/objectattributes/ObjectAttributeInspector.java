package jahmm.jadetree.objectattributes;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Logger;
import jutils.types.TypeUtils;

/**
 *
 * @author kommusoft
 */
public class ObjectAttributeInspector {

    private static final Logger LOG = Logger.getLogger(ObjectAttributeInspector.class.getName());

    public static <T> Collection<ObjectAttribute<T, ? extends Object>> inspect(Class<T> toinspect) {
        LinkedList<ObjectAttribute<T, ? extends Object>> ll = new LinkedList<>();
        for (Method method : toinspect.getMethods()) {
            ObjectAttribute<T, ? extends Object> oaa = (ObjectAttribute<T, ? extends Object>) inspect(method);
            if (oaa != null) {
                ll.add(oaa);
            }
        }
        return ll;
    }

    public static ObjectAttribute<? extends Object, ? extends Object> inspect(Method method) {
        if (method.getParameterTypes().length == 0x00) {
            ObjectAttributeAnnotation oaa = method.getAnnotation(ObjectAttributeAnnotation.class);
            if (oaa != null) {
                return generateObjectAttribute(method, oaa);
            }
        }
        return null;
    }

    private static ObjectAttribute<? extends Object, ? extends Object> generateObjectAttribute(Method method, ObjectAttributeAnnotation oaa) {
        Class<?> result = TypeUtils.getWrapper(method.getReturnType());
        String name = oaa.name();
        if (TypeUtils.isNominal(result)) {
            return new NominalInspectedObjectAttribute<>(method, name, result);
        } else if (result == Double.class) {
            return new DoubleInspectedContinuObjectAttribute<>(method, name, result);
        } else if (result == Float.class) {
            return new FloatInspectedContinuObjectAttribute<>(method, name, result);
        } else if (TypeUtils.isOrdinal(result)) {
            return new OrdinalInspectedObjectAttribute<>(method, name, result);
        } else {
            return null;
        }
    }

    private ObjectAttributeInspector() {
    }

}
