package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;
import javax.management.AttributeList;

/**
 *
 * @author kommusoft
 */
public class CollectionFactoryMethods {

    private CollectionFactoryMethods() {
    }

    public static <T> FactoryMethod<ArrayBlockingQueue<T>> arrayBlockingQueueFactory(int capacity) {
        return new FactoryMethod<ArrayBlockingQueue<T>>() {

            private int capacity;

            @Override
            public ArrayBlockingQueue<T> generate() {
                return new ArrayBlockingQueue<>(capacity);
            }

            private FactoryMethod<ArrayBlockingQueue<T>> initialize(int capacity) {
                this.capacity = capacity;
                return this;
            }

        }.initialize(capacity);
    }

    public static <T> FactoryMethod<ArrayBlockingQueue<T>> arrayBlockingQueueFactory(int capacity, boolean fair) {
        return new FactoryMethod<ArrayBlockingQueue<T>>() {

            private int capacity;
            private boolean fair;

            @Override
            public ArrayBlockingQueue<T> generate() {
                return new ArrayBlockingQueue<>(capacity, fair);
            }

            private FactoryMethod<ArrayBlockingQueue<T>> initialize(int capacity, boolean fair) {
                this.capacity = capacity;
                this.fair = fair;
                return this;
            }

        }.initialize(capacity, fair);
    }

    public static <T> FactoryMethod<ArrayBlockingQueue<T>> arrayBlockingQueueFactory(int capacity, boolean fair, Collection<? extends T> c) {
        return new FactoryMethod<ArrayBlockingQueue<T>>() {

            private int capacity;
            private boolean fair;
            private Collection<? extends T> c;

            @Override
            public ArrayBlockingQueue<T> generate() {
                return new ArrayBlockingQueue<>(capacity, fair, c);
            }

            private FactoryMethod<ArrayBlockingQueue<T>> initialize(int capacity, boolean fair, Collection<? extends T> c) {
                this.capacity = capacity;
                this.fair = fair;
                this.c = c;
                return this;
            }

        }.initialize(capacity, fair, c);
    }

    public static <T> FactoryMethod<ArrayList<T>> arrayListFactory() {
        return new FactoryMethod<ArrayList<T>>() {

            @Override
            public ArrayList<T> generate() {
                return new ArrayList<>();
            }

        };
    }

    public static <T> FactoryMethod<ArrayList<T>> arrayListFactory(Collection<? extends T> c) {
        return new FactoryMethod<ArrayList<T>>() {

            private Collection<? extends T> c;

            @Override
            public ArrayList<T> generate() {
                return new ArrayList<>(c);
            }

            private FactoryMethod<ArrayList<T>> initialize(Collection<? extends T> c) {
                this.c = c;
                return this;
            }

        }.initialize(c);
    }

    public static <T> FactoryMethod<ArrayList<T>> arrayListFactory(int initialCapacity) {
        return new FactoryMethod<ArrayList<T>>() {

            private int initialCapacity;

            @Override
            public ArrayList<T> generate() {
                return new ArrayList<>(initialCapacity);
            }

            private FactoryMethod<ArrayList<T>> initialize(int initialCapacity) {
                this.initialCapacity = initialCapacity;
                return this;
            }

        }.initialize(initialCapacity);
    }

    public static FactoryMethod<AttributeList> attributeListFactory() {
        return new FactoryMethod<AttributeList>() {

            @Override
            public AttributeList generate() {
                return new AttributeList();
            }

        };
    }

    public static FactoryMethod<AttributeList> attributeListFactory(AttributeList list) {
        return new FactoryMethod<AttributeList>() {

            private AttributeList list;

            @Override
            public AttributeList generate() {
                return new AttributeList(list);
            }

            private FactoryMethod<AttributeList> initialize(AttributeList c) {
                this.list = c;
                return this;
            }

        }.initialize(list);
    }

    public static FactoryMethod<AttributeList> attributeListFactory(int initialCapacity) {
        return new FactoryMethod<AttributeList>() {

            private int initialCapacity;

            @Override
            public AttributeList generate() {
                return new AttributeList(initialCapacity);
            }

            private FactoryMethod<AttributeList> initialize(int initialCapacity) {
                this.initialCapacity = initialCapacity;
                return this;
            }

        }.initialize(initialCapacity);
    }

}
