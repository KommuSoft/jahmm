/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package objectattributes;

import java.util.Collection;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author kommusoft
 */
public class ObjectAttributeInspectorTest {
    
    public ObjectAttributeInspectorTest() {
    }

    @Test
    public void testInspect() {
        Assert.assertEquals(0x00,ObjectAttributeInspector.inspect(Foo1.class).size());
        Assert.assertEquals(0x01,ObjectAttributeInspector.inspect(Foo2.class).size());
        Assert.assertEquals(0x02,ObjectAttributeInspector.inspect(Foo3.class).size());
    }
    
    private class Foo1 {
        
    }
    
    private class Foo2 {
        
        @ObjectAttributeAnnotation(name="bar1")
        public boolean value1 () {
            return true;
        }
        
        public boolean value2 (int someparameter) {
            return true;
        }
        
        public boolean value3 () {
            return true;
        }
        
    }
    
    private class Foo3 {
        
        private final boolean value1;
        
        public Foo3 (boolean value1) {
            this.value1 = value1;
        }
        
        @ObjectAttributeAnnotation(name="bar1")
        public boolean value1 () {
            return value1;
        }
        
        @ObjectAttributeAnnotation(name="bar2")
        public boolean value2 () {
            return true;
        }
        
        @ObjectAttributeAnnotation(name="bar3")
        public boolean value2 (int someparameter) {
            return true;
        }
        
    }
    
}
