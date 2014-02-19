/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package jahmm.draw;

import jahmm.Hmm;
import java.io.IOException;
import java.io.Writer;
import java.text.NumberFormat;
import java.util.logging.Logger;
import jutils.draw.StructuredDotDrawerBase;
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
class HmmDotDrawer<THMM extends Hmm<?>> extends StructuredDotDrawerBase<THMM> {

    private static final Logger LOG = Logger.getLogger(HmmDotDrawer.class.getName());

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
    protected void innerWrite(THMM input, Writer streamWriter) throws IOException {
        this.writeTransitions(streamWriter, input);
        this.writeStates(streamWriter, input);
    }

    protected void writeTransitions(Writer streamWriter, Hmm<?> hmm) throws IOException {
        String s = "";
        Tuple2<String, String> labelTuple = new Tuple2Base<>("label", "");

        for (int i = 0; i < hmm.nbStates(); i++) {
            for (int j = 0; j < hmm.nbStates(); j++) {
                if (hmm.getAij(i, j) >= minimumAij) {
                    labelTuple.setItem2(probabilityFormat.format(hmm.getAij(i, j)));
                    this.edgeStatement(streamWriter, i, j, labelTuple);
                }
            }
        }
        streamWriter.write(s);
    }

    protected void writeStates(Writer streamWriter, THMM hmm) throws IOException {
        Tuple2<String, String> shapeTuple = new Tuple2Base<>("shape", "doublecircle");
        Tuple2<String, String> labelTuple = new Tuple2Base<>("label", "");
        for (int i = 0; i < hmm.nbStates(); i++) {
            if (hmm.getPi(i) >= minimumPi) {
                labelTuple.setItem2("\"" + i + " - Pi= " + probabilityFormat.format(hmm.getPi(i)) + " - " + opdfLabel(hmm, i) + "\"");
            } else {
                labelTuple.setItem2("\"" + i + " - " + opdfLabel(hmm, i) + "\"");
            }
            this.nodeStatement(streamWriter, i, shapeTuple, labelTuple);
        }
    }

    protected String opdfLabel(THMM hmm, int stateNb) {
        return "[ " + hmm.getOpdf(stateNb).toString() + " ]";
    }
}
