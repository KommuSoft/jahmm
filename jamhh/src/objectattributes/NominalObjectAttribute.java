package objectattributes;

import java.util.Set;

/**
 *
 * @author kommusoft
 */
public abstract class NominalObjectAttribute<TSource,TTarget> implements ObjectAttribute<TSource,TTarget> {

    @Override
    public ObjectAttributeType getType() {
        return ObjectAttributeType.Nominal;
    }
    
    public abstract Set<TTarget> getPossibleValues ();
    
}
