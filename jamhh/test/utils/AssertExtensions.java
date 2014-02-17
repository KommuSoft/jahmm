package utils;

import java.util.logging.Logger;
import static junit.framework.Assert.fail;
import org.junit.Assert;

/**
 *
 * @author kommusoft
 */
public class AssertExtensions {

    private static double EPSILON = 1e-06d;
    private static final Logger LOG = Logger.getLogger(AssertExtensions.class.getName());

    /**
     * @return the EPSILON
     */
    public static double getEpsilon() {
        return EPSILON;
    }

    /**
     * @param epsilon the epsilon to set
     */
    public static void setEpsilon(double epsilon) {
        EPSILON = epsilon;
    }

    public static void assertEquals(double expected, double result, double delta) {
        Assert.assertEquals(expected, result, delta);
    }

    public static void assertEquals(double expected, double result) {
        Assert.assertEquals(expected, result, EPSILON);
    }

    public static void assertEquals(String message, double expected, double result, double delta) {
        Assert.assertEquals(message, expected, result, delta);
    }

    public static void assertEquals(String message, double expected, double result) {
        Assert.assertEquals(message, expected, result, EPSILON);
    }

    public static <T> void assertTypeof(Class<T> expectedType, Object object) {
        if (expectedType != null && !expectedType.isInstance(object)) {
            Class<?> realType = null;
            if (object != null) {
                realType = object.getClass();
            }
            fail(String.format("Expected the object to be of type %s, but was %s.", expectedType, realType));
        }
    }

    public static <T> void assertTypeof(String message, Class<T> expectedType, Object object) {
        if (expectedType != null && !expectedType.isInstance(object)) {
            fail(message);
        }
    }

    private AssertExtensions() {
    }

}
