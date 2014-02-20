package jahmm.jadetree.trace;

import java.util.logging.Logger;
import jutlis.tuples.Tuple2Base;

/**
 *
 * @author kommusoft
 */
public class SplitJadeTraceCommand extends Tuple2Base<Long, Long[]> {

    private static final Logger LOG = Logger.getLogger(SplitJadeTraceCommand.class.getName());

    public SplitJadeTraceCommand(Long source, Long[] branches) {
        super(source, branches);
    }

    public Long getSource() {
        return this.getItem1();
    }

    public Long[] getBranches() {
        return this.getItem2();
    }

}
