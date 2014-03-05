package jahmm.jadetree.foo;

import jahmm.jadetree.objectattributes.ObjectAttributeAnnotation;
import java.util.logging.Logger;

/**
 *
 * @author kommusoft
 */
public class TestObject1 {

    private static final Logger LOG = Logger.getLogger(TestObject1.class.getName());

    private final int year;

    public TestObject1(int year) {
        this.year = year;
    }

    @ObjectAttributeAnnotation(name = "div2")
    public boolean Div2() {
        return (year % 2) == 0;
    }

    @ObjectAttributeAnnotation(name = "div4")
    public boolean Div4() {
        return (year % 4) == 0;
    }

    @ObjectAttributeAnnotation(name = "div8")
    public boolean Div8() {
        return (year % 8) == 0;
    }

    @ObjectAttributeAnnotation(name = "div16")
    public boolean Div16() {
        return (year % 16) == 0;
    }

    @ObjectAttributeAnnotation(name = "div100")
    public boolean Div100() {
        return (year % 100) == 0;
    }

    @ObjectAttributeAnnotation(name = "div200")
    public boolean Div200() {
        return (year % 200) == 0;
    }

    @ObjectAttributeAnnotation(name = "div500")
    public boolean Div500() {
        return (year % 500) == 0;
    }

    @ObjectAttributeAnnotation(name = "div1000")
    public boolean Div1000() {
        return (year % 1000) == 0;
    }

    @ObjectAttributeAnnotation(name = "leap")
    public boolean isLeapYear() {
        return this.Div4() && (this.Div1000() || !this.Div100());
    }

}
