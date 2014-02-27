package jahmm.jadetree.trace;

import java.util.logging.Logger;
import jutlis.tuples.Tuple2Base;

/**
 *
 * @author kommusoft
 */
public class MergeTreeTraceCommand extends Tuple2Base<Long[], Long> implements TreeTraceCommand {

    private static final Logger LOG = Logger.getLogger(MergeTreeTraceCommand.class.getName());

    public MergeTreeTraceCommand(Long[] branches, Long target) {
        super(branches, target);
    }

    public Long[] getBranches() {
        return this.getItem1();
    }

    public Long getTraget() {
        return this.getItem2();
    }

    @Override
    public void process(TreeTrace trace) {
        for (Long id : this.getBranches()) {
            trace.dieBranch(id);
        }
        trace.createBranch(this.getTraget(), this.getBranches());
    }

}
