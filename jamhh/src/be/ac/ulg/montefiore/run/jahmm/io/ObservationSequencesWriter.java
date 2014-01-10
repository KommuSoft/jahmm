/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package be.ac.ulg.montefiore.run.jahmm.io;

import be.ac.ulg.montefiore.run.jahmm.Observation;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.logging.Logger;

/**
 * This class can write a set of observation sequences to a
 * {@link java.io.Writer Writer}.
 * <p>
 * The sequences written using this file can be read using the
 * {@link ObservationSequencesReader ObservationSequencesReader} class.
 */
public class ObservationSequencesWriter {

    /**
     * Writes a set of sequences to file.
     *
     * @param writer The writer to write to. It should use the "US-ASCII"
     * character set.
     * @param ow The observation writer used to generate the observations.
     * @param sequences The set of observation sequences.
     * @throws java.io.IOException
     */
    static public <O extends Observation> void
            write(Writer writer, ObservationWriter<? super O> ow,
                    List<? extends List<O>> sequences)
            throws IOException {
        for (List<O> s : sequences) {
            write(s, ow, writer);
        }
    }

    /* 
     * Writes the sequence 'sequence' to the writer 'writer' using the
     * observation writer 'ow'.
     */
    static <O extends Observation> void
            write(List<O> sequence, ObservationWriter<? super O> ow, Writer writer)
            throws IOException {
        for (O o : sequence) {
            ow.write(o, writer);
        }

        writer.write("\n");
    }
<<<<<<< HEAD

    private ObservationSequencesWriter() {
    }
    private static final Logger LOG = Logger.getLogger(ObservationSequencesWriter.class.getName());
=======
>>>>>>> parent of e8b9e16... refactorings
}
