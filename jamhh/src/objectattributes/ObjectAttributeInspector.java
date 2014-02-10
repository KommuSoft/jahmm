package objectattributes;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Logger;
import utils.Utils;

/**
 *
 * @author kommusoft
 */
public class ObjectAttributeInspector {
    private static final Logger LOG = Logger.getLogger(ObjectAttributeInspector.class.getName());

    public static<T> Collection<ObjectAttribute<T,Object>> inspect(Class<T> toinspect) {
        LinkedList<ObjectAttribute<T,Object>> ll = new LinkedList<>();
        for (Method method : toinspect.getMethods()) {
            if (method.getParameterTypes().length == 0x00) {
                ObjectAttributeAnnotation oaa = method.getAnnotation(ObjectAttributeAnnotation.class);
                if (oaa != null) {
                    ll.add(generateObjectAttribute(toinspect,method,oaa));
                }
            }
        }
        return ll;
    }
    
    private  ObjectAttributeInspector () {
    }

    public static <T> ObjectAttribute<T,Object> generateObjectAttribute(Class<T> classdef, Method method, ObjectAttributeAnnotation oaa) {
        Class<?> result = method.getReturnType();
        if(Utils.isNominal(result)) {
            return new NominalInspectedObjectAttribute<T,Object>(method,oaa.name(),result);
        }
        return null;
    }

}
