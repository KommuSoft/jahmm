/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package jahmm.io;

import jahmm.observables.OpdfGaussianMixture;
import java.io.IOException;
import java.io.Writer;

/**
 * This class implements a {@link OpdfGaussianMixture} writer. It is compatible
 * with the {@link OpdfGaussianMixtureReader} class.
 */
public class OpdfGaussianMixtureWriter
        extends OpdfWriter<OpdfGaussianMixture> {

    @Override
    public void write(Writer writer, OpdfGaussianMixture opdf)
            throws IOException {
        writer.write("GaussianMixtureOPDF [ ");

        write(writer, opdf.means());
        writer.write(" ");
        write(writer, opdf.variances());
        writer.write(" ");
        write(writer, opdf.proportions());

        writer.write(" ]");
    }
}
