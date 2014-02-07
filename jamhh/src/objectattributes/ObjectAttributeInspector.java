package objectattributes;

import java.lang.reflect.Method;
import java.util.LinkedList;

/**
 *
 * @author kommusoft
 */
public class ObjectAttributeInspector {
    
    private ObjectAttributeInspector () {}
    
    public static Iterable<ObjectAttribute> inspect (Class toinspect) {
        LinkedList<ObjectAttribute> ll = new LinkedList<>();
        for(Method method : toinspect.getMethods()) {
            ObjectAttributeAnnotation oaa = method.getAnnotation(ObjectAttributeAnnotation.class);
            if(oaa != null) {
                ll.add(null);
            }
        }
        return ll;
    }
    
}
