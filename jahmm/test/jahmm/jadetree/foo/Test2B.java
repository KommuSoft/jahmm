package jahmm.jadetree.foo;

import jahmm.jadetree.objectattributes.ObjectAttributeAnnotation;
import java.util.logging.Logger;
import jutils.probability.ProbabilityUtils;

/**
 *
 * @author kommusoft
 */
public class Test2B {

    private static final Logger LOG = Logger.getLogger(Test2B.class.getName());

    private final boolean bool1;
    private final boolean bool2;

    public Test2B(boolean bool1, boolean bool2) {
        this.bool1 = bool1;
        this.bool2 = bool2;
    }

    public Test2B() {
        this(ProbabilityUtils.nextBoolean(), ProbabilityUtils.nextBoolean());
    }

    /**
     * @return the bool1
     */
    @ObjectAttributeAnnotation(name = "bool1")
    public boolean isBool1() {
        return bool1;
    }

    /**
     * @return the bool2
     */
    @ObjectAttributeAnnotation(name = "bool2")
    public boolean isBool2() {
        return bool2;
    }

}