package objectattributes;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Logger;

/**
 *
 * @author kommusoft
 */
public class ObjectAttributeInspector {
    private static final Logger LOG = Logger.getLogger(ObjectAttributeInspector.class.getName());

    public static<T> Collection<ObjectAttribute<T,?>> inspect(Class<T> toinspect) {
        LinkedList<ObjectAttribute<T,?>> ll = new LinkedList<>();
        for (Method method : toinspect.getMethods()) {
            if (method.getParameterTypes().length == 0x00) {
                ObjectAttributeAnnotation oaa = method.getAnnotation(ObjectAttributeAnnotation.class);
                if (oaa != null) {
                    ll.add(null);
                }
            }
        }
        return ll;
    }

    private ObjectAttributeInspector() {
    }

}
