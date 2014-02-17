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
 * @param <TTarget>
 */
public abstract class AttributeDecisionNode<TSource, TTarget> extends DecisionInode<TSource> {

    final ObjectAttribute<TSource, TTarget> objectAttribute;

    protected AttributeDecisionNode(final DecisionNode<TSource> parent, ObjectAttribute<TSource, TTarget> objectAttribute) {
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
    protected ObjectAttribute<? super TSource, ? extends TTarget> getObjectAttribute() {
        return objectAttribute;
    }

    protected TTarget getObjectAttribute(TSource source) {
        return this.objectAttribute.evaluate(source);
    }

}
