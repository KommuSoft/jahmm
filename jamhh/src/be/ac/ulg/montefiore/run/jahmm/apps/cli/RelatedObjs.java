/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package be.ac.ulg.montefiore.run.jahmm.apps.cli;

import be.ac.ulg.montefiore.run.jahmm.CentroidFactory;
import be.ac.ulg.montefiore.run.jahmm.Hmm;
import be.ac.ulg.montefiore.run.jahmm.Observation;
import be.ac.ulg.montefiore.run.jahmm.Opdf;
import be.ac.ulg.montefiore.run.jahmm.OpdfFactory;
import be.ac.ulg.montefiore.run.jahmm.io.FileFormatException;
import be.ac.ulg.montefiore.run.jahmm.io.ObservationReader;
import be.ac.ulg.montefiore.run.jahmm.io.ObservationWriter;
import be.ac.ulg.montefiore.run.jahmm.io.OpdfReader;
import be.ac.ulg.montefiore.run.jahmm.io.OpdfWriter;
import be.ac.ulg.montefiore.run.jahmm.toolbox.MarkovGenerator;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * This class collects all the objects related to a specific observation type.
 * @param <O>
 */
public interface RelatedObjs<O extends Observation & CentroidFactory<O>> {

    /**
     *
     * @return
     */
    public ObservationReader<O> observationReader();

    /**
     *
     * @return
     */
    public ObservationWriter<O> observationWriter();

    /**
     *
     * @return
     */
    public OpdfFactory<? extends Opdf<O>> opdfFactory();

    /**
     *
     * @return
     */
    public OpdfReader<? extends Opdf<O>> opdfReader();

    /**
     *
     * @return
     */
    public OpdfWriter<? extends Opdf<O>> opdfWriter();

    /**
     *
     * @param reader
     * @return
     * @throws FileFormatException
     * @throws IOException
     */
    public List<List<O>> readSequences(Reader reader)
            throws FileFormatException, IOException;

    /**
     *
     * @param hmm
     * @return
     */
    public MarkovGenerator<O> generator(Hmm<O> hmm);
}
