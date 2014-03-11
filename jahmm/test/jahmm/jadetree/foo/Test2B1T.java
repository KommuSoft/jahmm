package jahmm.jadetree.foo;

import jahmm.jadetree.objectattributes.ObjectAttributeAnnotation;
import java.util.logging.Logger;
import jutils.probability.ProbabilityUtils;

/**
 *
 * @author kommusoft
 */
public class Test2B1T {

    private static final Logger LOG = Logger.getLogger(Test2B1T.class.getName());

    private final boolean bool1;
    private final boolean bool2;
    private TrisEnum trival;

    public Test2B1T(boolean bool1, boolean bool2, TrisEnum tris) {
        this.bool1 = bool1;
        this.bool2 = bool2;
        this.trival = tris;
    }

    public Test2B1T() {
        this(0.5d);
    }

    public Test2B1T(TrisEnum tris) {
        this(0.5d, tris);
    }

    public Test2B1T(double probability) {
        this(probability, TrisEnum.Odin);
    }

    public Test2B1T(double probability, TrisEnum tris) {
        this(ProbabilityUtils.nextBoolean(probability), ProbabilityUtils.nextBoolean(probability), tris);
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

    /**
     * @return the bool2
     */
    @ObjectAttributeAnnotation(name = "tris")
    public TrisEnum getTris() {
        return trival;
    }

    @Override
    public String toString() {
        return ((Boolean) bool1).toString().charAt(0x00) + "/" + ((Boolean) bool2).toString().charAt(0x00) + "/" + this.trival.toString().charAt(0x00);
    }

}
