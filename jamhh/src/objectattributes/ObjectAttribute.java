package objectattributes;

import java.lang.reflect.InvocationTargetException;
import utils.Name;

/**
 *
 * @author kommusoft
 * @param <TSource> The type of the source.
 * @param <TTarget> The type of the attribute.
 */
public interface ObjectAttribute<TSource, TTarget> extends Name {

    TTarget getAttribute(TSource source) throws IllegalAccessException, InvocationTargetException;

    ObjectAttributeType getType();

}