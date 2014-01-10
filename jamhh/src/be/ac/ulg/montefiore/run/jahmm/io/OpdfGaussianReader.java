/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package be.ac.ulg.montefiore.run.jahmm.io;

import be.ac.ulg.montefiore.run.jahmm.OpdfGaussian;
import static be.ac.ulg.montefiore.run.jahmm.io.HmmReader.readWords;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.logging.Logger;

/**
 * This class implements a {@link OpdfGaussian} reader. The syntax of the
 * distribution description is the following.
 * <p>
 * The description always begins with the keyword <tt>GaussianOPDF</tt>. The
 * next (resp. last) symbol is an opening (resp. closing) bracket. Between the
 * backets are two numbers separated by a space. The first is the distribution's
 * mean, the second the variance.
 * <p>
 * For example, reading <tt>GaussianOPDF [ .2 .3 ]</tt> returns a distribution
 * equivalent to <code>new OpdfGaussian(.2, .3)</code>.
 */
public class OpdfGaussianReader
        extends OpdfReader<OpdfGaussian> {

    @Override
    String keyword() {
        return "GaussianOPDF";
    }

    @Override
    public OpdfGaussian read(StreamTokenizer st)
            throws IOException, FileFormatException {
        readWords(st, keyword());

        double[] meanVariance = OpdfReader.read(st, 2);

        return new OpdfGaussian(meanVariance[0], meanVariance[1]);
    }
    private static final Logger LOG = Logger.getLogger(OpdfGaussianReader.class.getName());
}
