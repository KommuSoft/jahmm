package jahmm.draw;

import jahmm.Hmm;
import jahmm.ObservationInteger;
import jahmm.OpdfIntegerFactory;
import java.io.IOException;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author kommusoft
 */
public class InvariantHmmDotDrawerTest {

    private static final Logger LOG = Logger.getLogger(InvariantHmmDotDrawerTest.class.getName());

    public InvariantHmmDotDrawerTest() {
    }

    @Test
    public void testWrite() throws IOException {
        Hmm<ObservationInteger> hmm = new Hmm<>(4, new OpdfIntegerFactory(2));
        String expected = "digraph {\n"
                + "	initial [shape=box];\n"
                + "	0 -> 0 [label=0.25];\n"
                + "	0 -> 1 [label=0.25];\n"
                + "	0 -> 2 [label=0.25];\n"
                + "	0 -> 3 [label=0.25];\n"
                + "	1 -> 0 [label=0.25];\n"
                + "	1 -> 1 [label=0.25];\n"
                + "	1 -> 2 [label=0.25];\n"
                + "	1 -> 3 [label=0.25];\n"
                + "	2 -> 0 [label=0.25];\n"
                + "	2 -> 1 [label=0.25];\n"
                + "	2 -> 2 [label=0.25];\n"
                + "	2 -> 3 [label=0.25];\n"
                + "	3 -> 0 [label=0.25];\n"
                + "	3 -> 1 [label=0.25];\n"
                + "	3 -> 2 [label=0.25];\n"
                + "	3 -> 3 [label=0.25];\n"
                + "	0 [shape=doublecircle, label=\"0 - [ Integer distribution --- 0.5 0.5 ]\"];\n"
                + "	initial -> 0 [label=\"0.25\"];\n"
                + "	1 [shape=doublecircle, label=\"1 - [ Integer distribution --- 0.5 0.5 ]\"];\n"
                + "	initial -> 1 [label=\"0.25\"];\n"
                + "	2 [shape=doublecircle, label=\"2 - [ Integer distribution --- 0.5 0.5 ]\"];\n"
                + "	initial -> 2 [label=\"0.25\"];\n"
                + "	3 [shape=doublecircle, label=\"3 - [ Integer distribution --- 0.5 0.5 ]\"];\n"
                + "	initial -> 3 [label=\"0.25\"];\n"
                + "}\n";
        String actual = InvariantHmmDotDrawer.Instance.write(hmm);
        Assert.assertEquals(expected, actual);
    }

}
