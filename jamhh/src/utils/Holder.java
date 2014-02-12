/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import objectattributes.ObjectAttributeAnnotation;

/**
 *
 * @author kommusoft
 * @param <T> The type of data the holder holds.
 */
public interface Holder<T> {

    @ObjectAttributeAnnotation(name = "data")
    public T getData();

    public T setData(T data);

    <T2 extends T> T copyFrom(Holder<T2> other);

}
