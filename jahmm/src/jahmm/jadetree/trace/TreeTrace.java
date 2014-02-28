package jahmm.jadetree.trace;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import jutils.collections.CollectionUtils;
import jutlis.algebra.functions.FunctionMap;
import jutlis.lists.ListArray;

/**
 *
 * @author kommusoft
 */
public class TreeTrace {

    private static final Logger LOG = Logger.getLogger(TreeTrace.class.getName());

    private final HashMap<Long, TreeBranch> branches = new HashMap<>();
    private int time = 0x00;

    public TreeTrace() {

    }

    public void createBranch(long id, Long... origins) {
        List<Long> orgs = new ListArray<>(origins);
        if (!this.branches.containsKey(id) && branches.keySet().containsAll(orgs)) {
            Iterable<TreeBranch> branchmap = CollectionUtils.map(orgs, new FunctionMap<>(this.branches));
            TreeBranch branch = new TreeBranch(id, time, branchmap);
            this.branches.put(id, branch);
        }
    }

    public void dieBranch(long id) {
        this.getBranch(id).die(time);
    }

    public void advance(TreeTraceCommand command) {
        this.advanceTime();
        command.process(this);
    }

    private void advanceTime() {
        this.time++;
    }

    private TreeBranch getBranch(long id) {
        return this.branches.get(id);
    }

}
