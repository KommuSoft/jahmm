/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jahmm.draw;

import static jahmm.GenerateTest.outputDir;
import jahmm.Hmm;
import jahmm.ObservationInteger;
import jahmm.OpdfIntegerFactory;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kommusoft
 */
public class InvariantHmmDotDrawerTest {

    public InvariantHmmDotDrawerTest() {
    }

    @Test
    public void testWrite() throws IOException {
        Hmm<ObservationInteger> hmm = new Hmm<>(4, new OpdfIntegerFactory(2));
        String expected = "digraph {\n"
                + "\t0 -> 0 [label=0.25];\n"
                + "\t0 -> 1 [label=0.25];\n"
                + "\t0 -> 2 [label=0.25];\n"
                + "\t0 -> 3 [label=0.25];\n"
                + "\t1 -> 0 [label=0.25];\n"
                + "\t1 -> 1 [label=0.25];\n"
                + "\t1 -> 2 [label=0.25];\n"
                + "\t1 -> 3 [label=0.25];\n"
                + "\t2 -> 0 [label=0.25];\n"
                + "\t2 -> 1 [label=0.25];\n"
                + "\t2 -> 2 [label=0.25];\n"
                + "\t2 -> 3 [label=0.25];\n"
                + "\t3 -> 0 [label=0.25];\n"
                + "\t3 -> 1 [label=0.25];\n"
                + "\t3 -> 2 [label=0.25];\n"
                + "\t3 -> 3 [label=0.25];\n"
                + "\t0 [shape=doublecircle, label=\"0 - Pi= 0.25 - [ Integer distribution --- 0.5 0.5 ]\"];\n"
                + "\t1 [shape=doublecircle, label=\"1 - Pi= 0.25 - [ Integer distribution --- 0.5 0.5 ]\"];\n"
                + "\t2 [shape=doublecircle, label=\"2 - Pi= 0.25 - [ Integer distribution --- 0.5 0.5 ]\"];\n"
                + "\t3 [shape=doublecircle, label=\"3 - Pi= 0.25 - [ Integer distribution --- 0.5 0.5 ]\"];\n"
                + "}\n";
        String actual = InvariantHmmDotDrawer.Instance.write(hmm);
        Assert.assertEquals(expected, actual);
    }

}
