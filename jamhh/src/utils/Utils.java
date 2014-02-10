package utils;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author kommusoft
 */
public class Utils {
    
    private static Set<Boolean> booleanSet;
    
    private Utils () {}
    
    public static Set<Boolean> getBooleanSet () {
        if(booleanSet == null) {
            booleanSet = new HashSet<Boolean>();
            booleanSet.add(Boolean.TRUE);
            booleanSet.add(Boolean.FALSE);
        }
        return booleanSet;
    }
    
    public static <T> boolean isNominal (Class<T> classdef) {
        return (classdef.isEnum() || classdef == char.class || classdef == boolean.class);
    }
    
    public static <T> Set<T> getNominalSet (Class<T> classdef) {
        if(classdef == boolean.class) {
            return (Set<T>) getBooleanSet();
        }
        else {
            return null;
        }
    }
    
}
