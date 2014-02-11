package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;

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
                return new ArrayBlockingQueue<T>(capacity, fair, c);
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
                return new ArrayBlockingQueue<T>(capacity, fair, c);
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
                return new ArrayBlockingQueue<T>(capacity, fair, c);
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
                return new ArrayList<T>();
            }
            
        };
    }
    
}
