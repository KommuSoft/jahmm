package jadetree;

import java.util.ArrayList;
import objectattributes.ObjectAttribute;
import utils.HolderBase;

/**
 *
 * @author kommusoft
 * @param <TSource>
 */
public class DecisionLeaf<TSource> extends DecisionNode<TSource> {

    public DecisionLeaf(DecisionTree<TSource> tree) {
        super(tree);
    }
    final ArrayList<TSource> memory = new ArrayList<>();
    double score = Double.NaN;
    int splitIndex = 0x00;
    final HolderBase<Object> splitData = new HolderBase<>();

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

    public DecisionNode<TSource> expand() {
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
        for (ObjectAttribute<? super TSource, ?> oa : this.getTree().getSourceAttributes()) {
            double sc = oa.calculateScore(this.memory, curData);
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

}
