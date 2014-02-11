package utils;

/**
 *
 * @author kommusoft
 */
public class HolderBase<T> implements Holder<T> {

    private T data;
    
    public HolderBase () {
        this(null);
    }
    
    public HolderBase (T data) {
        this.data = data;
    }
    
    @Override
    public T getData() {
        return this.data;
    }

    @Override
    public T setData(T data) {
        T temp = this.data;
        this.data = data;
        return temp;
    }
    
}
