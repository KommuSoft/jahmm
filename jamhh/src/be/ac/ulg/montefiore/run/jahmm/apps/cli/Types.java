/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package be.ac.ulg.montefiore.run.jahmm.apps.cli;

import be.ac.ulg.montefiore.run.jahmm.Hmm;
import be.ac.ulg.montefiore.run.jahmm.ObservationInteger;
import be.ac.ulg.montefiore.run.jahmm.ObservationReal;
import be.ac.ulg.montefiore.run.jahmm.ObservationVector;
import be.ac.ulg.montefiore.run.jahmm.Opdf;
import be.ac.ulg.montefiore.run.jahmm.OpdfFactory;
import be.ac.ulg.montefiore.run.jahmm.OpdfGaussianFactory;
import be.ac.ulg.montefiore.run.jahmm.OpdfGaussianMixtureFactory;
import be.ac.ulg.montefiore.run.jahmm.OpdfIntegerFactory;
import be.ac.ulg.montefiore.run.jahmm.OpdfMultiGaussianFactory;
import be.ac.ulg.montefiore.run.jahmm.apps.cli.CommandLineArguments.Arguments;
import be.ac.ulg.montefiore.run.jahmm.io.FileFormatException;
import be.ac.ulg.montefiore.run.jahmm.io.ObservationIntegerReader;
import be.ac.ulg.montefiore.run.jahmm.io.ObservationIntegerWriter;
import be.ac.ulg.montefiore.run.jahmm.io.ObservationReader;
import be.ac.ulg.montefiore.run.jahmm.io.ObservationRealReader;
import be.ac.ulg.montefiore.run.jahmm.io.ObservationRealWriter;
import be.ac.ulg.montefiore.run.jahmm.io.ObservationSequencesReader;
import be.ac.ulg.montefiore.run.jahmm.io.ObservationVectorReader;
import be.ac.ulg.montefiore.run.jahmm.io.ObservationVectorWriter;
import be.ac.ulg.montefiore.run.jahmm.io.ObservationWriter;
import be.ac.ulg.montefiore.run.jahmm.io.OpdfGaussianMixtureReader;
import be.ac.ulg.montefiore.run.jahmm.io.OpdfGaussianMixtureWriter;
import be.ac.ulg.montefiore.run.jahmm.io.OpdfGaussianReader;
import be.ac.ulg.montefiore.run.jahmm.io.OpdfGaussianWriter;
import be.ac.ulg.montefiore.run.jahmm.io.OpdfIntegerReader;
import be.ac.ulg.montefiore.run.jahmm.io.OpdfIntegerWriter;
import be.ac.ulg.montefiore.run.jahmm.io.OpdfMultiGaussianReader;
import be.ac.ulg.montefiore.run.jahmm.io.OpdfMultiGaussianWriter;
import be.ac.ulg.montefiore.run.jahmm.io.OpdfReader;
import be.ac.ulg.montefiore.run.jahmm.io.OpdfWriter;
import be.ac.ulg.montefiore.run.jahmm.toolbox.MarkovGenerator;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.logging.Logger;

/**
 * A repository of all the observation types and opdfs and the related readers,
 * writers, factories,...
 */
class Types {

    public static RelatedObjs<?> relatedObjs()
            throws WrongArgumentsException {
        String opdf = Arguments.OPDF.get();
        switch (opdf) {
            case "integer":
                return new IntegerRelatedObjects();
            case "multi_gaussian":
                return new VectorRelatedObjects();
            case "gaussian":
            case "gaussian_mixture":
                return new RealRelatedObjects(opdf);
        }

        throw new AssertionError("Unknown observation type");
    }
<<<<<<< HEAD

    private Types() {
    }
    private static final Logger LOG = Logger.getLogger(Types.class.getName());
=======
>>>>>>> parent of e8b9e16... refactorings
}

class IntegerRelatedObjects
        implements RelatedObjs<ObservationInteger> {

    final int range;

    public IntegerRelatedObjects()
            throws WrongArgumentsException {
        range = Arguments.INTEGER_RANGE.getAsInt();
    }

    @Override
    public ObservationReader<ObservationInteger> observationReader() {
        return new ObservationIntegerReader(range);
    }

    @Override
    public ObservationWriter<ObservationInteger> observationWriter() {
        return new ObservationIntegerWriter();
    }

    @Override
    public OpdfFactory<? extends Opdf<ObservationInteger>> opdfFactory() {
        return new OpdfIntegerFactory(range);
    }

    @Override
    public OpdfReader<? extends Opdf<ObservationInteger>> opdfReader() {
        return new OpdfIntegerReader(range);
    }

    @Override
    public OpdfWriter<? extends Opdf<ObservationInteger>> opdfWriter() {
        return new OpdfIntegerWriter();
    }

    @Override
    public List<List<ObservationInteger>> readSequences(Reader reader)
            throws FileFormatException, IOException {
        return ObservationSequencesReader.readSequences(observationReader(),
                reader);
    }

    @Override
    public MarkovGenerator<ObservationInteger>
            generator(Hmm<ObservationInteger> hmm) {
        return new MarkovGenerator<>(hmm);
    }
    private static final Logger LOG = Logger.getLogger(IntegerRelatedObjects.class.getName());
}

class RealRelatedObjects
        implements RelatedObjs<ObservationReal> {

    public final String opdf;
    public final int nb;

    public RealRelatedObjects(String opdf)
            throws WrongArgumentsException {
        this.opdf = opdf;
        nb = Arguments.NB_GAUSSIANS.getAsInt();
    }

    @Override
    public ObservationRealReader observationReader() {
        return new ObservationRealReader();
    }

    @Override
    public ObservationRealWriter observationWriter() {
        return new ObservationRealWriter();
    }

    @Override
    public OpdfFactory<? extends Opdf<ObservationReal>> opdfFactory() {
        if (opdf.equals("gaussian")) {
            return new OpdfGaussianFactory();
        } else { // Gaussian mixture
            return new OpdfGaussianMixtureFactory(nb);
        }
    }

    @Override
    public OpdfReader<? extends Opdf<ObservationReal>> opdfReader() {
        if (opdf.equals("gaussian")) {
            return new OpdfGaussianReader();
        } else // Gaussian mixture
        {
            return new OpdfGaussianMixtureReader();
        }
    }

    @Override
    public OpdfWriter<? extends Opdf<ObservationReal>> opdfWriter() {
        if (opdf.equals("gaussian")) {
            return new OpdfGaussianWriter();
        } else // Gaussian mixture
        {
            return new OpdfGaussianMixtureWriter();
        }
    }

    @Override
    public List<List<ObservationReal>> readSequences(Reader reader)
            throws FileFormatException, IOException {
        return ObservationSequencesReader.readSequences(observationReader(),
                reader);
    }

    @Override
    public MarkovGenerator<ObservationReal>
            generator(Hmm<ObservationReal> hmm) {
        return new MarkovGenerator<>(hmm);
    }
    private static final Logger LOG = Logger.getLogger(RealRelatedObjects.class.getName());
}

class VectorRelatedObjects
        implements RelatedObjs<ObservationVector> {

    final int dimension;

    public VectorRelatedObjects()
            throws WrongArgumentsException {
        dimension = Arguments.VECTOR_DIMENSION.getAsInt();
    }

    @Override
    public ObservationVectorReader observationReader() {
        return new ObservationVectorReader(dimension);
    }

    @Override
    public ObservationVectorWriter observationWriter() {
        return new ObservationVectorWriter();
    }

    @Override
    public OpdfFactory<? extends Opdf<ObservationVector>> opdfFactory() {
        return new OpdfMultiGaussianFactory(dimension);
    }

    @Override
    public OpdfReader<? extends Opdf<ObservationVector>> opdfReader() {
        return new OpdfMultiGaussianReader();
    }

    @Override
    public OpdfWriter<? extends Opdf<ObservationVector>> opdfWriter() {
        return new OpdfMultiGaussianWriter();
    }

    @Override
    public List<List<ObservationVector>> readSequences(Reader reader)
            throws FileFormatException, IOException {
        return ObservationSequencesReader.readSequences(observationReader(),
                reader);
    }

    @Override
    public MarkovGenerator<ObservationVector>
            generator(Hmm<ObservationVector> hmm) {
        return new MarkovGenerator<>(hmm);
    }
    private static final Logger LOG = Logger.getLogger(VectorRelatedObjects.class.getName());
}
