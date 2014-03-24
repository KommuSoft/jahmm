/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package jahmm.draw;

import jahmm.RegularHmm;
import java.io.IOException;
import java.io.Writer;
import java.text.NumberFormat;
import java.util.logging.Logger;
import jutils.draw.DotGraphDrawerBase;
import jutlis.tuples.Tuple2;
import jutlis.tuples.Tuple2Base;

/**
 * An HMM to <i>dot</i> file converter. See
 * <url>http://www.research.att.com/sw/tools/graphviz/</url>
 * for more information on the <i>dot</i> tool.
 * <p>
 * The command <tt>dot -Tps -o &lt;outputfile&gt; &lt;inputfile&gt;</tt>
 * should produce a Postscript file describing an HMM.
 */
public class HmmDotDrawer<THmm extends RegularHmm<?, THmm>> extends DotGraphDrawerBase<THmm> {

    public static final HmmDotDrawer Instance = new HmmDotDrawer();

    private static final Logger LOG = Logger.getLogger(HmmDotDrawer.class.getName());

    private static final String INITIAL_TOKEN = "initial";
    private static final Tuple2<String, String> TRIANGLE_SHAPE = new Tuple2Base<>("shape", "triangle");
    private static final Tuple2<String, String> BOX_SHAPE = new Tuple2Base<>("shape", "box");
    private static final Tuple2<String, String> DCIRCLE_SHAPE = new Tuple2Base<>("shape", "doublecircle");

    protected double minimumAij = 0.01;
    protected double minimumPi = 0.01;
    protected NumberFormat probabilityFormat;

    /**
     * This class converts an HMM to a dot file.
     */
    HmmDotDrawer() {
        probabilityFormat = NumberFormat.getInstance();
        probabilityFormat.setMaximumFractionDigits(2);
    }

    @Override
    protected void innerWrite(THmm input, Writer streamWriter) throws IOException {
        this.nodeStatement(streamWriter, INITIAL_TOKEN, BOX_SHAPE);
        this.writeTransitions(streamWriter, input);
        this.writeStates(streamWriter, input);
    }

    protected void writeTransitions(Writer streamWriter, THmm hmm) throws IOException {
        Tuple2<String, String> labelTuple = new Tuple2Base<>("label", "");

        for (int i = 0; i < hmm.nbStates(); i++) {
            for (int j = 0; j < hmm.nbStates(); j++) {
                if (hmm.getAij(i, j) >= minimumAij) {
                    labelTuple.setItem2(probabilityFormat.format(hmm.getAij(i, j)));
                    this.edgeStatement(streamWriter, i, j, labelTuple);
                }
            }
        }
    }

    protected void writeStates(Writer streamWriter, THmm hmm) throws IOException {
        Tuple2<String, String> labelTuple = new Tuple2Base<>("label", "");
        for (int i = 0; i < hmm.nbStates(); i++) {
            labelTuple.setItem2("\"" + i + " - " + opdfLabel(hmm, i) + "\"");
            this.nodeStatement(streamWriter, i, DCIRCLE_SHAPE, labelTuple);
            if (hmm.getPi(i) >= minimumPi) {
                labelTuple.setItem2("\"" + probabilityFormat.format(hmm.getPi(i)) + "\"");
                this.edgeStatement(streamWriter, INITIAL_TOKEN, i, labelTuple);
            }
        }
    }

    protected String opdfLabel(THmm hmm, int stateNb) {
        return "[ " + hmm.getOpdf(stateNb).toString() + " ]";
    }
}
