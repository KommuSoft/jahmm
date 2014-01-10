/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package be.ac.ulg.montefiore.run.jahmm.io;

import be.ac.ulg.montefiore.run.jahmm.OpdfGaussian;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class implements a {@link OpdfGaussian} writer. It is compatible with
 * the {@link OpdfGaussianReader} class.
 */
public class OpdfGaussianWriter
        extends OpdfWriter<OpdfGaussian> {

    @Override
    public void write(Writer writer, OpdfGaussian opdf)
            throws IOException {
        String s = "GaussianOPDF [";

        s += opdf.mean() + " " + opdf.variance();

        writer.write(s + "]\n");
    }
    private static final Logger LOG = Logger.getLogger(OpdfGaussianWriter.class.getName());
}
