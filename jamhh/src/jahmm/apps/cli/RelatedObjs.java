/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package jahmm.apps.cli;

import jahmm.RegularHmmBase;
import jahmm.io.FileFormatException;
import jahmm.io.ObservationReader;
import jahmm.io.ObservationWriter;
import jahmm.io.OpdfReader;
import jahmm.io.OpdfWriter;
import jahmm.observables.CentroidFactory;
import jahmm.observables.Observation;
import jahmm.observables.Opdf;
import jahmm.observables.OpdfFactory;
import jahmm.toolbox.MarkovGenerator;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * This class collects all the objects related to a specific observation type.
 *
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
    public MarkovGenerator<O> generator(RegularHmmBase<O> hmm);
}
