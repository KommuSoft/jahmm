/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jahmm.jadetree.foo;

import jahmm.jadetree.objectattributes.ObjectAttributeAnnotation;

/**
 *
 * @author kommusoft
 */
public class FooIntDouble {
    
    private final int integer;
    private final double doubl;

    public FooIntDouble(int integer, double doubl) {
        this.integer = integer;
        this.doubl = doubl;
    }

    /**
     * @return the integer
     */
    @ObjectAttributeAnnotation(name="integer")
    public int getInteger() {
        return integer;
    }

    /**
     * @return the doubl
     */
    @ObjectAttributeAnnotation(name="double")
    public double getDouble() {
        return doubl;
    }
    
}
