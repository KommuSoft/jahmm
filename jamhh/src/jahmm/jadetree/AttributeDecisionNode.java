/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jahmm.jadetree;

import jahmm.jadetree.objectattributes.ObjectAttribute;

/**
 *
 * @author kommusoft
 * @param <TSource>
 * @param <TTarget>
 */
public abstract class AttributeDecisionNode<TSource, TTarget> extends DecisionInodeBase<TSource> {

    final ObjectAttribute<TSource, TTarget> objectAttribute;

    protected AttributeDecisionNode(final DecisionInode<TSource> parent, ObjectAttribute<TSource, TTarget> objectAttribute) {
        super(parent);
        this.objectAttribute = objectAttribute;
    }

    @Override
    public double expandScore() {
        return this.getMaximumExpandLeaf().expandScore();
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
