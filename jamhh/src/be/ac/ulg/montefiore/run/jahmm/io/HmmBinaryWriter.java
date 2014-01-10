/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package be.ac.ulg.montefiore.run.jahmm.io;

import be.ac.ulg.montefiore.run.jahmm.Hmm;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * This class can write a Hidden Markov Models to a byte stream.
 * <p>
 * The HMM objects are simply serialized. HMMs could thus be unreadable using a
 * different release of this library.
 */
public class HmmBinaryWriter {

    /**
     * Writes a HMM to byte stream.
     *
     * @param stream Holds the byte stream the HMM is written to.
     * @param hmm
     * @throws java.io.IOException
     */
    static public void write(OutputStream stream, Hmm<?> hmm)
            throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(stream);
        oos.writeObject(hmm);
    }

    private HmmBinaryWriter() {
    }
}
