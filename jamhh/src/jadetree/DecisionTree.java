package jadetree;

import java.util.ArrayList;
import objectattributes.ObjectAttribute;

/**
 *
 * @author kommusoft
 * @param <TSource> The source of the types to classify.
 */
public class DecisionTree<TSource> {
    
    private final ArrayList<ObjectAttribute<? super TSource,?>> sourceattributes = new ArrayList<>();
    private final ArrayList<ObjectAttribute<? super TSource,?>> targetattributes = new ArrayList<>();
    
    
}