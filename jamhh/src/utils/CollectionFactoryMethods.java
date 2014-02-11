package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;
import javax.management.AttributeList;

/**
 *
 * @author kommusoft
 */
public class CollectionFactoryMethods {

    private static final Logger LOG = Logger.getLogger(CollectionFactoryMethods.class.getName());

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

    public static <T> FactoryMethod<ConcurrentLinkedQueue<T>> concurrentLinkedQueueFactory() {
        return new FactoryMethod<ConcurrentLinkedQueue<T>>() {

            @Override
            public ConcurrentLinkedQueue<T> generate() {
                return new ConcurrentLinkedQueue<>();
            }

        };
    }

    public static <T> FactoryMethod<ConcurrentLinkedQueue<T>> concurrentLinkedQueueFactory(Collection<? extends T> c) {
        return new FactoryMethod<ConcurrentLinkedQueue<T>>() {

            private Collection<? extends T> c;

            @Override
            public ConcurrentLinkedQueue<T> generate() {
                return new ConcurrentLinkedQueue<>(c);
            }

            private FactoryMethod<ConcurrentLinkedQueue<T>> initialize(Collection<? extends T> c) {
                this.c = c;
                return this;
            }

        }.initialize(c);
    }

    public static <T> FactoryMethod<CopyOnWriteArrayList<T>> copyOnWriteArrayListFactory() {
        return new FactoryMethod<CopyOnWriteArrayList<T>>() {

            @Override
            public CopyOnWriteArrayList<T> generate() {
                return new CopyOnWriteArrayList<>();
            }

        };
    }

    public static <T> FactoryMethod<CopyOnWriteArrayList<T>> copyOnWriteArrayListFactory(Collection<? extends T> c) {
        return new FactoryMethod<CopyOnWriteArrayList<T>>() {

            private Collection<? extends T> c;

            @Override
            public CopyOnWriteArrayList<T> generate() {
                return new CopyOnWriteArrayList<>(c);
            }

            private FactoryMethod<CopyOnWriteArrayList<T>> initialize(Collection<? extends T> c) {
                this.c = c;
                return this;
            }

        }.initialize(c);
    }

    public static <T> FactoryMethod<CopyOnWriteArrayList<T>> copyOnWriteArrayListFactory(T... toCopyIn) {
        return new FactoryMethod<CopyOnWriteArrayList<T>>() {

            private T[] toCopyIn;

            @Override
            public CopyOnWriteArrayList<T> generate() {
                return new CopyOnWriteArrayList<>(toCopyIn);
            }

            private FactoryMethod<CopyOnWriteArrayList<T>> initialize(T[] toCopyIn) {
                this.toCopyIn = toCopyIn;
                return this;
            }

        }.initialize(toCopyIn);
    }

    public static <T> FactoryMethod<CopyOnWriteArraySet<T>> copyOnWriteArraySetFactory() {
        return new FactoryMethod<CopyOnWriteArraySet<T>>() {

            @Override
            public CopyOnWriteArraySet<T> generate() {
                return new CopyOnWriteArraySet<>();
            }

        };
    }

    public static <T> FactoryMethod<CopyOnWriteArraySet<T>> copyOnWriteArraySetFactory(Collection<? extends T> c) {
        return new FactoryMethod<CopyOnWriteArraySet<T>>() {

            private Collection<? extends T> c;

            @Override
            public CopyOnWriteArraySet<T> generate() {
                return new CopyOnWriteArraySet<>(c);
            }

            private FactoryMethod<CopyOnWriteArraySet<T>> initialize(Collection<? extends T> c) {
                this.c = c;
                return this;
            }

        }.initialize(c);
    }

    public static <T extends Delayed> FactoryMethod<DelayQueue<T>> delayQueueFactory() {
        return new FactoryMethod<DelayQueue<T>>() {

            @Override
            public DelayQueue<T> generate() {
                return new DelayQueue<>();
            }

        };
    }

    public static <T extends Delayed> FactoryMethod<DelayQueue<T>> delayQueueFactory(Collection<? extends T> c) {
        return new FactoryMethod<DelayQueue<T>>() {

            private Collection<? extends T> c;

            @Override
            public DelayQueue<T> generate() {
                return new DelayQueue<>(c);
            }

            private FactoryMethod<DelayQueue<T>> initialize(Collection<? extends T> c) {
                this.c = c;
                return this;
            }

        }.initialize(c);
    }

    public static <T> FactoryMethod<HashSet<T>> hashSetFactory() {
        return new FactoryMethod<HashSet<T>>() {

            @Override
            public HashSet<T> generate() {
                return new HashSet<>();
            }

        };
    }

    public static <T> FactoryMethod<HashSet<T>> hashSetFactory(Collection<? extends T> c) {
        return new FactoryMethod<HashSet<T>>() {

            private Collection<? extends T> c;

            @Override
            public HashSet<T> generate() {
                return new HashSet<>(c);
            }

            private FactoryMethod<HashSet<T>> initialize(Collection<? extends T> c) {
                this.c = c;
                return this;
            }

        }.initialize(c);
    }

    public static <T> FactoryMethod<HashSet<T>> hashSetFactory(int initialCapacity) {
        return new FactoryMethod<HashSet<T>>() {

            private int initialCapacity;

            @Override
            public HashSet<T> generate() {
                return new HashSet<>(initialCapacity);
            }

            private FactoryMethod<HashSet<T>> initialize(int initialCapacity) {
                this.initialCapacity = initialCapacity;
                return this;
            }

        }.initialize(initialCapacity);
    }

    public static <T> FactoryMethod<HashSet<T>> hashSetFactory(int initialCapacity, float loadFactor) {
        return new FactoryMethod<HashSet<T>>() {

            private int initialCapacity;
            private float loadFactor;

            @Override
            public HashSet<T> generate() {
                return new HashSet<>(initialCapacity, loadFactor);
            }

            private FactoryMethod<HashSet<T>> initialize(int initialCapacity, float loadFactor) {
                this.initialCapacity = initialCapacity;
                this.loadFactor = loadFactor;
                return this;
            }

        }.initialize(initialCapacity, loadFactor);
    }

    public static <T> FactoryMethod<LinkedBlockingQueue<T>> linkedBlockingQueueFactory() {
        return new FactoryMethod<LinkedBlockingQueue<T>>() {

            @Override
            public LinkedBlockingQueue<T> generate() {
                return new LinkedBlockingQueue<>();
            }

        };
    }

    public static <T> FactoryMethod<LinkedBlockingQueue<T>> linkedBlockingQueueFactory(Collection<? extends T> c) {
        return new FactoryMethod<LinkedBlockingQueue<T>>() {

            private Collection<? extends T> c;

            @Override
            public LinkedBlockingQueue<T> generate() {
                return new LinkedBlockingQueue<>(c);
            }

            private FactoryMethod<LinkedBlockingQueue<T>> initialize(Collection<? extends T> c) {
                this.c = c;
                return this;
            }

        }.initialize(c);
    }

    public static <T> FactoryMethod<LinkedBlockingQueue<T>> linkedBlockingQueueFactory(int capacity) {
        return new FactoryMethod<LinkedBlockingQueue<T>>() {

            private int capacity;

            @Override
            public LinkedBlockingQueue<T> generate() {
                return new LinkedBlockingQueue<>(capacity);
            }

            private FactoryMethod<LinkedBlockingQueue<T>> initialize(int capacity) {
                this.capacity = capacity;
                return this;
            }

        }.initialize(capacity);
    }

    private CollectionFactoryMethods() {
    }

}
