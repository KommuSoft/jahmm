package utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author kommusoft
 */
public class Utils {
    
    public static final BooleanSet BOOLEAN_SET = new BooleanSet();

    private Utils() {
    }

    public static Set<Boolean> getBooleanSet() {
        /*if(booleanSet == null) {
         booleanSet = new HashSet<Boolean>();
         booleanSet.add(Boolean.TRUE);
         booleanSet.add(Boolean.FALSE);
         }
         return booleanSet;*/
        return null;
    }

    public static <T> boolean isNominal(Class<T> classdef) {
        return (classdef.isEnum() || classdef == char.class || classdef == boolean.class);
    }

    @SuppressWarnings("unchecked")
    public static <T> Set<T> getNominalSet(Class<T> classdef) {
        if (classdef == boolean.class) {
            return (Set<T>) getBooleanSet();
        } else {
            return null;
        }
    }

    public static class BooleanSet implements Set<Boolean> {
        
        private BooleanSet () {}

        @Override
        public int size() {
            return 0x02;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return o.equals(Boolean.FALSE) || o.equals(Boolean.TRUE);
        }

        @Override
        public Iterator<Boolean> iterator() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Object[] toArray() {
            return new Object[]{Boolean.FALSE, Boolean.TRUE};
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T> T[] toArray(T[] ts) {
            return (T[]) new Boolean[]{Boolean.FALSE, Boolean.TRUE};
        }

        @Override
        public boolean add(Boolean e) {
            throw new UnsupportedOperationException("Immutable.");
        }

        @Override
        public boolean remove(Object o) {
            throw new UnsupportedOperationException("Immutable.");
        }

        @Override
        public boolean containsAll(Collection<?> clctn) {
            for (Object val : clctn) {
                if (!this.contains(val)) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public boolean addAll(Collection<? extends Boolean> clctn) {
            throw new UnsupportedOperationException("Immutable.");
        }

        @Override
        public boolean retainAll(Collection<?> clctn) {
            throw new UnsupportedOperationException("Immutable.");
        }

        @Override
        public boolean removeAll(Collection<?> clctn) {
            throw new UnsupportedOperationException("Immutable.");
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException("Immutable.");
        }

    }

}
