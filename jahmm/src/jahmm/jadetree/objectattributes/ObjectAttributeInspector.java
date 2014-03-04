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
            if (method.getParameterTypes().length == 0x00) {
                ObjectAttributeAnnotation oaa = method.getAnnotation(ObjectAttributeAnnotation.class);
                if (oaa != null) {
                    ll.add(generateObjectAttribute(toinspect, method, oaa));
                }
            }
        }
        return ll;
    }

    public static <T> ObjectAttribute<T, ? extends Object> generateObjectAttribute(Class<T> classdef, Method method, ObjectAttributeAnnotation oaa) {
        Class<?> result = method.getReturnType();
        String name = oaa.name();
        if (TypeUtils.isNominal(result)) {
            return new NominalInspectedObjectAttribute<>(method, name, result);
        } else if (result == double.class) {
            return new DoubleInspectedContinuObjectAttribute<>(method, name, result);
        } else if (result == float.class) {
            return new FloatInspectedContinuObjectAttribute<>(method, name, result);
        } else if (result.isAssignableFrom(Comparable.class)) {
            return new OrdinalInspectedObjectAttribute<>(method, name, result);
        }
        return null;
    }

    private ObjectAttributeInspector() {
    }

}
