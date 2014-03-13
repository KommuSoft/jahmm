package jahmm.jadetree.foo;

import jahmm.jadetree.objectattributes.ObjectAttributeAnnotation;

/**
 *
 * @author kommusoft
 */
public class FooIntDouble implements Comparable<FooIntDouble> {

    private final int integer;
    private final double doubl;

    public FooIntDouble(int integer, double doubl) {
        this.integer = integer;
        this.doubl = doubl;
    }

    /**
     * @return the integer
     */
    @ObjectAttributeAnnotation(name = "integer")
    public int getInteger() {
        return integer;
    }

    /**
     * @return the doubl
     */
    @ObjectAttributeAnnotation(name = "double")
    public double getDouble() {
        return doubl;
    }

    @Override
    public int compareTo(FooIntDouble t) {
        return ((Double) doubl).compareTo(t.doubl);
    }

    @Override
    public String toString() {
        return String.format("%s/%s", this.integer, this.doubl);
    }

}
