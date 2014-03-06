package jahmm.jadetree.objectattributes;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;
import jutils.collections.valuesets.BooleanSet;
import jutils.testing.AssertExtensions;
import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author kommusoft
 */
public class ObjectAttributeInspectorTest {

    public ObjectAttributeInspectorTest() {
    }

    @Test
    public void testInspect() throws IllegalAccessException, InvocationTargetException {
        Collection<ObjectAttribute<Foo1, ? extends Object>> resfoo1 = ObjectAttributeInspector.inspect(Foo1.class);
        Collection<ObjectAttribute<Foo2, ? extends Object>> resfoo2 = ObjectAttributeInspector.inspect(Foo2.class);
        Collection<ObjectAttribute<Foo3, ? extends Object>> resfoo3 = ObjectAttributeInspector.inspect(Foo3.class);
        Collection<ObjectAttribute<Foo4, ? extends Object>> resfoo4 = ObjectAttributeInspector.inspect(Foo4.class);
        Iterator<ObjectAttribute<Foo2, ? extends Object>> itfoo2;
        Iterator<ObjectAttribute<Foo4, ? extends Object>> itfoo4;
        ObjectAttribute<Foo2, ? extends Object> oa2;
        ObjectAttribute<Foo4, ? extends Object> oa4;
        itfoo2 = resfoo2.iterator();
        Foo2 foo2t = new Foo2(true);
        Foo2 foo2f = new Foo2(false);
        Foo3 foo3t = new Foo3(true);
        Foo3 foo3f = new Foo3(false);
        Assert.assertEquals(0x00, resfoo1.size());
        Assert.assertEquals(0x01, resfoo2.size());
        Assert.assertTrue(itfoo2.hasNext());
        oa2 = itfoo2.next();
        AssertExtensions.assertTypeof(NominalInspectedObjectAttribute.class, oa2);
        Assert.assertEquals("bar1", oa2.getName());
        Assert.assertEquals(BooleanSet.Instance, ((NominalObjectAttribute) oa2).getPossibleValues());
        Assert.assertEquals(Boolean.TRUE, oa2.evaluate(foo2t));
        Assert.assertEquals(Boolean.FALSE, oa2.evaluate(foo2f));
        Assert.assertEquals(0x02, resfoo3.size());
        Assert.assertEquals(0x04, resfoo4.size());
        itfoo4 = resfoo4.iterator();
        Foo4 foo415g2 = new Foo4(0.15, EnumFoo.Git, 0x02);
        Foo4 foo400h3 = new Foo4(0.00, EnumFoo.Hub, 0x03);
        Foo4 foo482b4 = new Foo4(0.82, EnumFoo.Bit, 0x04);
        Foo4 foo414B9 = new Foo4(0.41, EnumFoo.Bucket, 0x09);
        Foo4 foo4NNg8 = new Foo4(Double.NaN, EnumFoo.Git, 0x08);
        Foo4 foo4NIh7 = new Foo4(Double.NEGATIVE_INFINITY, EnumFoo.Hub, 0x07);
        for (int i = 0x00; i < 0x04; i++) {
            Assert.assertTrue(itfoo4.hasNext());
            oa4 = itfoo4.next();
            Class<?> cls = oa4.getClass();
            if (cls == NominalInspectedObjectAttribute.class) {
                AssertExtensions.assertTypeof(NominalInspectedObjectAttribute.class, oa4);
                Assert.assertEquals("bar3", oa4.getName());
                Assert.assertEquals(EnumFoo.Git, oa4.evaluate(foo415g2));
                Assert.assertEquals(EnumFoo.Hub, oa4.evaluate(foo400h3));
                Assert.assertEquals(EnumFoo.Bit, oa4.evaluate(foo482b4));
                Assert.assertEquals(EnumFoo.Bucket, oa4.evaluate(foo414B9));
                Assert.assertEquals(EnumFoo.Git, oa4.evaluate(foo4NNg8));
                Assert.assertEquals(EnumFoo.Hub, oa4.evaluate(foo4NIh7));
            } else if (cls == DoubleInspectedContinuObjectAttribute.class) {
                AssertExtensions.assertTypeof(DoubleInspectedContinuObjectAttribute.class, oa4);
                Assert.assertEquals("bar1", oa4.getName());
                Assert.assertEquals(0.15, oa4.evaluate(foo415g2));
                Assert.assertEquals(0.00, oa4.evaluate(foo400h3));
                Assert.assertEquals(0.82, oa4.evaluate(foo482b4));
                Assert.assertEquals(0.41, oa4.evaluate(foo414B9));
                Assert.assertEquals(Double.NaN, oa4.evaluate(foo4NNg8));
                Assert.assertEquals(Double.NEGATIVE_INFINITY, oa4.evaluate(foo4NIh7));
            } else if (cls == OrdinalInspectedObjectAttribute.class) {
                AssertExtensions.assertTypeof(OrdinalInspectedObjectAttribute.class, oa4);
                Assert.assertEquals("bar4", oa4.getName());
                Assert.assertEquals(0x02, oa4.evaluate(foo415g2));
                Assert.assertEquals(0x03, oa4.evaluate(foo400h3));
                Assert.assertEquals(0x04, oa4.evaluate(foo482b4));
                Assert.assertEquals(0x09, oa4.evaluate(foo414B9));
                Assert.assertEquals(0x08, oa4.evaluate(foo4NNg8));
                Assert.assertEquals(0x07, oa4.evaluate(foo4NIh7));
            } else {
                AssertExtensions.assertTypeof(FloatInspectedContinuObjectAttribute.class, oa4);
                Assert.assertEquals("bar2", oa4.getName());
                Assert.assertEquals(0.85f, oa4.evaluate(foo415g2));
                Assert.assertEquals(1.00f, oa4.evaluate(foo400h3));
                Assert.assertEquals(0.18f, oa4.evaluate(foo482b4));
                Assert.assertEquals(0.59f, oa4.evaluate(foo414B9));
                Assert.assertEquals(Float.NaN, oa4.evaluate(foo4NNg8));
                Assert.assertEquals(Float.POSITIVE_INFINITY, oa4.evaluate(foo4NIh7));
            }
        }
        Assert.assertFalse(itfoo4.hasNext());
    }

    private class Foo1 {

        Foo1() {
        }

    }

    private class Foo2 {

        private final boolean value1;

        Foo2(boolean value1) {
            this.value1 = value1;
        }

        @ObjectAttributeAnnotation(name = "bar1")
        public boolean value1() {
            return value1;
        }

        public boolean value2(int someparameter) {
            return true;
        }

        public boolean value3() {
            return true;
        }

    }

    private class Foo3 {

        private final boolean value1;

        Foo3(boolean value1) {
            this.value1 = value1;
        }

        @ObjectAttributeAnnotation(name = "bar1")
        public boolean value1() {
            return value1;
        }

        @ObjectAttributeAnnotation(name = "bar2")
        public boolean value2() {
            return true;
        }

        @ObjectAttributeAnnotation(name = "bar3")
        public boolean value2(int someparameter) {
            return true;
        }

    }

    private class Foo4 {

        private final double value1;
        private final EnumFoo value3;
        private final int value4;

        Foo4(double value1, EnumFoo value3, int value4) {
            this.value1 = value1;
            this.value3 = value3;
            this.value4 = value4;
        }

        @ObjectAttributeAnnotation(name = "bar1")
        public double value1() {
            return value1;
        }

        @ObjectAttributeAnnotation(name = "bar2")
        public float value2() {
            return (float) (1.0d - value1);
        }

        @ObjectAttributeAnnotation(name = "bar3")
        public EnumFoo value3() {
            return this.value3;
        }

        @ObjectAttributeAnnotation(name = "bar4")
        public int value4() {
            return this.value4;
        }

    }

    private enum EnumFoo {

        Git,
        Hub,
        Bit,
        Bucket

    }

    /**
     * Test of inspect method, of class ObjectAttributeInspector.
     */
    @Test
    public void testInspect_Class() {
        
        fail("The test case is a prototype.");
    }

    /**
     * Test of inspect method, of class ObjectAttributeInspector.
     */
    @Test
    public void testInspect_Method() {
        fail("The test case is a prototype.");
    }

    /**
     * Test of inspect method, of class ObjectAttributeInspector.
     */
    @Test
    public void testInspect_Class_String() {
        fail("The test case is a prototype.");
    }

    /**
     * Test of inspect method, of class ObjectAttributeInspector.
     */
    @Test
    public void testInspect_Class_Iterable() {
        fail("The test case is a prototype.");
    }

    /**
     * Test of inspect method, of class ObjectAttributeInspector.
     */
    @Test
    public void testInspect_Class_StringArr() {
        fail("The test case is a prototype.");
    }

}
