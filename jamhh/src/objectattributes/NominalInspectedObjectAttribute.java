package objectattributes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import utils.Utils;

/**
 *
 * @author kommusoft
 * @param <TSource>
 * @param <TTarget>
 */
public class NominalInspectedObjectAttribute<TSource, TTarget> implements NominalObjectAttribute<TSource, TTarget> {

    private final String name;
    private final Method method;
    private final Class<?> resultclass;

    NominalInspectedObjectAttribute(Method method, String name, Class<?> resultclass) {
        this.name = name;
        this.method = method;
        this.resultclass = resultclass;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<TTarget> getPossibleValues() {
        return (Set<TTarget>) Utils.getNominalSet(this.resultclass);
    }

    @Override
    @SuppressWarnings("unchecked")
    public TTarget getAttribute(TSource source) throws IllegalAccessException, InvocationTargetException {
        return (TTarget) this.method.invoke(source);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double calculateScore(List<? extends TSource> source) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createDecisionNode(List<? extends TSource> source) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public double calculateEntropy (Iterable<? extends TSource> sources) throws IllegalAccessException, InvocationTargetException {
        final HashMap<TTarget,Integer> frequency = new HashMap<>();
        final double log2 = 1.0d/Math.log(2.0d);
        int total = 0x00;
        for(TSource s : sources) {
            TTarget target = this.getAttribute(s);
            Utils.incrementKey(frequency, target);
            total++;
        }
        double entropy = 0.0d;
        for(Integer fi : frequency.values()) {
            double p = (double) fi/total;
            entropy -= p*log2*Math.log(p);
        }
        return entropy;
    }

}
