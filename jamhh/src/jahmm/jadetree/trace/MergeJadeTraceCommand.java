package jahmm.jadetree.trace;

import java.util.logging.Logger;
import jutlis.tuples.Tuple2Base;

/**
 *
 * @author kommusoft
 */
public class MergeJadeTraceCommand extends Tuple2Base<Long[], Long> {

    private static final Logger LOG = Logger.getLogger(MergeJadeTraceCommand.class.getName());

    public MergeJadeTraceCommand(Long[] branches, Long target) {
        super(branches, target);
    }

    public Long[] getBranches() {
        return this.getItem1();
    }

    public Long getTraget() {
        return this.getItem2();
    }

}
