package jahmm.jadetree.trace;

import java.util.logging.Logger;
import jutils.Idable;
import jutlis.lists.ListArray;

/**
 *
 * @author kommusoft
 */
public class TreeBranch implements Idable {

    private static final Logger LOG = Logger.getLogger(TreeBranch.class.getName());

    private final long id;
    private final int birth;
    private int death = 0x00;
    private final Iterable<TreeBranch> sources;

    public TreeBranch(long id) {
        this(id, 0x00);
    }

    public TreeBranch(long id, int birth) {
        this(id, birth, (Iterable<TreeBranch>) null);
    }

    public TreeBranch(long id, int birth, Iterable<TreeBranch> sources) {
        this.id = id;
        this.birth = birth;
        this.sources = sources;
    }

    public TreeBranch(long id, int birth, TreeBranch... sources) {
        this(id, birth, new ListArray<>(sources));
    }

    public void die(int time) {
        if (!this.died()) {
            this.death = time;
        } else {
            throw new IllegalStateException("Branch already died.");
        }
    }

    public boolean died() {
        return death > birth;
    }

    /**
     * @return the birth
     */
    public int getBirth() {
        return birth;
    }

    /**
     * @return the death
     */
    public int getDeath() {
        return death;
    }

    /**
     * @return the sources
     */
    public Iterable<TreeBranch> getSources() {
        return sources;
    }

    @Override
    public long getId() {
        return this.id;
    }

}
