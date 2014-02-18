package jahmm.draw;

public abstract class DiGraphDrawerDotBase<TInput> extends StructuredDrawerDotBase<TInput> {

    @Override
    protected String beginning() {
        return "digraph G {";
    }
}
