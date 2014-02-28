package jahmm.io;

import java.text.NumberFormat;

/**
 *
 * @author kommusoft
 */
public interface INumberFormatString {

    /**
     * Formats this object using a number formatter.
     *
     * @param numberFormat A number formatter.
     * @return A string describing this object according to the given number
     * format.
     */
    public String toString(NumberFormat numberFormat);

}
