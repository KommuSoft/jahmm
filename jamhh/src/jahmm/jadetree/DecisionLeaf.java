package jahmm.jadetree;

import jahmm.jadetree.objectattributes.ObjectAttribute;
import java.util.ArrayList;
import java.util.List;
import jutils.designpatterns.CompositeLeaf;
import jutlis.tuples.HolderBase;

/**
 *
 * @author kommusoft
 * @param <TSource>
 */
public class DecisionLeaf<TSource> extends DecisionNodeBase<TSource> implements CompositeLeaf<DecisionNode<TSource>> {

    private final List<TSource> memory;
    private double score = Double.NaN;
    private int splitIndex = 0x00;
    private final HolderBase<Object> splitData = new HolderBase<>();

    public DecisionLeaf(DecisionNode<TSource> parent) {
        this(parent, new ArrayList<TSource>());
    }

    public DecisionLeaf(DecisionNode<TSource> parent, List<TSource> memory) {
        super(parent);
        this.memory = memory;
    }

    public boolean isDirty() {
        return Double.isNaN(this.score);
    }

    @Override
    public void makeDirty() {
        this.score = Double.NaN;
        splitData.setData(null);
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public double expandScore() {
        if (this.isDirty()) {
            this.score = this.calculateScore();
        }
        return this.score;
    }

    public DecisionNodeBase<TSource> expand() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insert(TSource source) {
        this.makeDirty();
        memory.add(source);
    }

    double calculateScore() {
        double maxScore = Double.NEGATIVE_INFINITY;
        int maxIndex = 0x00, i = 0x00;
        HolderBase<Object> curData = new HolderBase<>();
        ObjectAttribute<TSource, Object> target = this.getTree().getTargetAttribute();
        for (ObjectAttribute<TSource, ?> oa : this.getTree().getSourceAttributes()) {
            double sc = oa.calculateScore(this.memory, target, curData);
            if (sc > maxScore) {
                maxScore = sc;
                maxIndex = i;
                this.splitData.copyFrom(curData);
            }
            i++;
        }
        this.splitIndex = maxIndex;
        return maxScore;
    }

    @Override
    public DecisionLeaf<TSource> getMaximumLeaf() {
        return this;
    }

    @Override
    public void replaceChild(DecisionRealNode<TSource> was, DecisionRealNode<TSource> now) {
    }

}
