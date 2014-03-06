package jahmm.jadetree.foo;

import jahmm.jadetree.objectattributes.ObjectAttributeAnnotation;
import java.util.logging.Logger;

/**
 *
 * @author kommusoft
 */
public class TestSquareEquation {

    private static final Logger LOG = Logger.getLogger(TestSquareEquation.class.getName());

    private final double a, b, c;

    public TestSquareEquation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * @return the a
     */
    @ObjectAttributeAnnotation(name = "a")
    public double getA() {
        return a;
    }

    /**
     * @return the b
     */
    @ObjectAttributeAnnotation(name = "b")
    public double getB() {
        return b;
    }

    /**
     * @return the c
     */
    @ObjectAttributeAnnotation(name = "c")
    public double getC() {
        return c;
    }

    /**
     * @return the c
     */
    @ObjectAttributeAnnotation(name = "d2")
    public double getD2() {
        return b * b - 4.0d * a * c;
    }

    /**
     * @return sol2
     */
    @ObjectAttributeAnnotation(name = "sol2")
    public boolean isSol2() {
        return this.getD2() > 0.0d;
    }

    /**
     * @return sol1
     */
    @ObjectAttributeAnnotation(name = "sol1")
    public boolean isSol1() {
        return this.getD2() == 0.0d;
    }

    /**
     * @return sol0
     */
    @ObjectAttributeAnnotation(name = "sol0")
    public boolean isSol0() {
        return this.getD2() < 0.0d;
    }

    /**
     * @return the c
     */
    @ObjectAttributeAnnotation(name = "x1")
    public float getX1() {
        if (this.isSol2()) {
            return (float) (0.5d * (-b - Math.sqrt(this.getD2())) / a);
        } else if (this.isSol1()) {
            return (float) (-0.5d * b / a);
        } else {
            return Float.NaN;
        }
    }

    /**
     * @return the c
     */
    @ObjectAttributeAnnotation(name = "x2")
    public float getX2() {
        if (this.isSol2()) {
            return (float) (0.5d * (-b + Math.sqrt(this.getD2())) / a);
        } else {
            return Float.NaN;
        }
    }

}
