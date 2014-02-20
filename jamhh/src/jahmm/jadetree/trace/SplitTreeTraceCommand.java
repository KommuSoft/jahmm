package jahmm.jadetree.trace;

import java.util.logging.Logger;
import jutlis.tuples.Tuple2Base;

/**
 *
 * @author kommusoft
 */
public class SplitTreeTraceCommand extends Tuple2Base<Long, Long[]> implements TreeTraceCommand {

    private static final Logger LOG = Logger.getLogger(SplitTreeTraceCommand.class.getName());

    public SplitTreeTraceCommand(Long source, Long[] branches) {
        super(source, branches);
    }

    public Long getSource() {
        return this.getItem1();
    }

    public Long[] getBranches() {
        return this.getItem2();
    }

    @Override
    public void process(TreeTrace trace) {
        trace.dieBranch(this.getSource());
        for (Long id : this.getBranches()) {
            trace.createBranch(id, this.getSource());
        }
    }

}
