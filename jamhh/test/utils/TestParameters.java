package utils;

import java.util.logging.Logger;

/**
 *
 * @author kommusoft
 */
public final class TestParameters {

    public static final double EPSILON = 1e-06d;
    public static final double NUMBER_OF_TESTS = 0x1000;
    private static final Logger LOG = Logger.getLogger(TestParameters.class.getName());

    private TestParameters() {
    }

}
