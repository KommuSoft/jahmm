package utils;

import java.util.Objects;
import java.util.logging.Logger;

/**
 *
 * @author kommusoft
 * @param <T> The type of data the holder holds.
 */
public class HolderBase<T> implements Holder<T> {

    private static final Logger LOG = Logger.getLogger(HolderBase.class.getName());

    public static <T1 extends T2, T2> T2 copyTo(Holder<T1> h1, Holder<T2> h2) {
        return h2.setData(h1.getData());
    }

    private T data;

    public HolderBase() {
        this(null);
    }

    public HolderBase(T data) {
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

    @Override
    public <T2 extends T> T copyFrom(Holder<T2> other) {
        return this.setData(other.getData());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.data);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HolderBase<?> other = (HolderBase<?>) obj;
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("< %s >", this.data);
    }

}
