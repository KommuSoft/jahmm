package jahmm.jadetree.draw;

import jahmm.jadetree.DecisionNode;
import java.util.logging.Logger;
import jutils.designpatterns.draw.CompositePatternDotDrawer;

/**
 *
 * @author kommusoft
 * @param <TSource> The type of elements in the decisionnode.
 */
public class DecisionNodeDotDrawer<TSource> extends CompositePatternDotDrawer<DecisionNode<TSource>> {

    private static final Logger LOG = Logger.getLogger(DecisionNodeDotDrawer.class.getName());

}
