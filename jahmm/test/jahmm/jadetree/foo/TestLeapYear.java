package jahmm.jadetree.foo;

import jahmm.jadetree.objectattributes.ObjectAttributeAnnotation;
import java.util.logging.Logger;

/**
 *
 * @author kommusoft
 */
public class TestLeapYear {

    private static final Logger LOG = Logger.getLogger(TestLeapYear.class.getName());

    private final int year;

    public TestLeapYear(int year) {
        this.year = year;
    }

    @ObjectAttributeAnnotation(name = "year")
    public int year() {
        return year;
    }

    @ObjectAttributeAnnotation(name = "div2")
    public boolean div2() {
        return (year % 2) == 0;
    }

    @ObjectAttributeAnnotation(name = "div4")
    public boolean div4() {
        return (year % 4) == 0;
    }

    @ObjectAttributeAnnotation(name = "div8")
    public boolean div8() {
        return (year % 8) == 0;
    }

    @ObjectAttributeAnnotation(name = "div16")
    public boolean div16() {
        return (year % 16) == 0;
    }

    @ObjectAttributeAnnotation(name = "div100")
    public boolean div100() {
        return (year % 100) == 0;
    }

    @ObjectAttributeAnnotation(name = "div200")
    public boolean div200() {
        return (year % 200) == 0;
    }

    @ObjectAttributeAnnotation(name = "div500")
    public boolean div500() {
        return (year % 500) == 0;
    }

    @ObjectAttributeAnnotation(name = "div1000")
    public boolean div1000() {
        return (year % 1000) == 0;
    }

    @ObjectAttributeAnnotation(name = "leap")
    public boolean isLeapYear() {
        return this.div4() && (this.div1000() || !this.div100());
    }

}
