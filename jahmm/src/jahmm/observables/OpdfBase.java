package jahmm.observables;

import jahmm.Hmm;
import java.io.IOException;
import java.io.Writer;
import jutils.draw.DotDrawer;
import jutlis.tuples.Tuple2;
import jutlis.tuples.Tuple2Base;

public abstract class OpdfBase<O extends Observation> implements Opdf<O> {

    @Override
    public void dotDrawNode(DotDrawer<? extends Hmm> drawer, Writer writer, String prefix) throws IOException {
        Tuple2<String, String> shapeTuple = new Tuple2Base<>("shape", "triangle");
        Tuple2<String, String> labelTuple = new Tuple2Base<>("shape", String.format("\"%s\"", this.toString()));
        drawer.nodeStatement(writer, prefix, shapeTuple, labelTuple);
    }

    @Override
    public void dotDrawEdge(DotDrawer<? extends Hmm> drawer, Writer writer, String prefix, String source) throws IOException {
        drawer.edgeStatement(writer, source, prefix);
    }

    @Override
    public OpdfBase clone() throws CloneNotSupportedException {
        return null;
    }

}
