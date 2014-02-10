/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package objectattributes;

import java.util.Collection;
import java.util.Iterator;
import org.junit.Assert;
import org.junit.Test;
import utils.AssertExtensions;

/**
 *
 * @author kommusoft
 */
public class ObjectAttributeInspectorTest {
    
    public ObjectAttributeInspectorTest() {
    }

    @Test
    public void testInspect() {
        Collection<ObjectAttribute<Foo1,Object>> resfoo1 = ObjectAttributeInspector.inspect(Foo1.class);
        Collection<ObjectAttribute<Foo2,Object>> resfoo2 = ObjectAttributeInspector.inspect(Foo2.class);
        Collection<ObjectAttribute<Foo3,Object>> resfoo3 = ObjectAttributeInspector.inspect(Foo3.class);
        Iterator<ObjectAttribute<Foo2,Object>> itfoo2 = resfoo2.iterator();
        Assert.assertEquals(0x00,resfoo1.size());
        Assert.assertEquals(0x01,resfoo2.size());
        Assert.assertTrue(itfoo2.hasNext());
        AssertExtensions.assertTypeof(NominalInspectedObjectAttribute.class, itfoo2.next());
        Assert.assertEquals(0x02,resfoo3.size());
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
