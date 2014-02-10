package utils;

import static junit.framework.Assert.fail;

/**
 *
 * @author kommusoft
 */
public class AssertExtensions {
    
    private AssertExtensions () {
    }
    
    public static<T> void assertTypeof(Class<T> expectedType, Object object) {
        if(expectedType != null && !expectedType.isInstance(object)) {
            Class<?> realType = null;
            if(object != null) {
                realType = object.getClass();
            }
            fail(String.format("Expected the object to be of type %s, but was %s.",expectedType,realType));
        }
    }
    
    public static<T> void assertTypeof(String message, Class<T> expectedType, Object object) {
        if(expectedType != null && !expectedType.isInstance(object)) {
            fail(message);
        }
    }
    
}
