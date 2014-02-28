package jahmm.jadetree;

import jahmm.jadetree.objectattributes.ObjectAttribute;

/**
 *
 * @author kommusoft
 * @param <TSource>
 * @param <TTarget>
 */
public abstract class AttributeDecisionNode<TSource, TTarget> extends DecisionRealInodeBase<TSource> {

    final ObjectAttribute<TSource, TTarget> objectAttribute;

    protected AttributeDecisionNode(final DecisionInode<TSource> parent, ObjectAttribute<TSource, TTarget> objectAttribute) {
        super(parent);
        this.objectAttribute = objectAttribute;
    }

    /**
     * @return the objectAttribute
     */
    protected ObjectAttribute<? super TSource, ? extends TTarget> getObjectAttribute() {
        return objectAttribute;
    }

    protected TTarget getObjectAttribute(TSource source) {
        return this.objectAttribute.evaluate(source);
    }

}
