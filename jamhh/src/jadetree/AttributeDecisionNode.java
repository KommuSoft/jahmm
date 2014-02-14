/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jadetree;

import jadetree.objectattributes.ObjectAttribute;

/**
 *
 * @author kommusoft
 * @param <TSource>
 * @param <TAttributeType>
 */
public abstract class AttributeDecisionNode<TSource, TAttributeType> extends DecisionInode<TSource> {

    final ObjectAttribute<? super TSource, ? extends TAttributeType> objectAttribute;

    protected AttributeDecisionNode(final DecisionNode<TSource> parent, ObjectAttribute<? super TSource, ? extends TAttributeType> objectAttribute) {
        super(parent);
        this.objectAttribute = objectAttribute;
    }

    @Override
    public double expandScore() {
        return this.getMaximumLeaf().expandScore();
    }

    /**
     * @return the objectAttribute
     */
    protected ObjectAttribute<? super TSource, ? extends TAttributeType> getObjectAttribute() {
        return objectAttribute;
    }

    protected TAttributeType getObjectAttribute(TSource source) {
        return this.objectAttribute.evaluate(source);
    }

}
