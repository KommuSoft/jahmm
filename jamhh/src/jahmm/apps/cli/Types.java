/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package jahmm.apps.cli;

import jahmm.Hmm;
import jahmm.ObservationInteger;
import jahmm.ObservationReal;
import jahmm.ObservationVector;
import jahmm.Opdf;
import jahmm.OpdfFactory;
import jahmm.OpdfGaussianFactory;
import jahmm.OpdfGaussianMixtureFactory;
import jahmm.OpdfIntegerFactory;
import jahmm.OpdfMultiGaussianFactory;
import jahmm.apps.cli.CommandLineArguments.Arguments;
import jahmm.io.FileFormatException;
import jahmm.io.ObservationIntegerReader;
import jahmm.io.ObservationIntegerWriter;
import jahmm.io.ObservationReader;
import jahmm.io.ObservationRealReader;
import jahmm.io.ObservationRealWriter;
import jahmm.io.ObservationSequencesReader;
import jahmm.io.ObservationVectorReader;
import jahmm.io.ObservationVectorWriter;
import jahmm.io.ObservationWriter;
import jahmm.io.OpdfGaussianMixtureReader;
import jahmm.io.OpdfGaussianMixtureWriter;
import jahmm.io.OpdfGaussianReader;
import jahmm.io.OpdfGaussianWriter;
import jahmm.io.OpdfIntegerReader;
import jahmm.io.OpdfIntegerWriter;
import jahmm.io.OpdfMultiGaussianReader;
import jahmm.io.OpdfMultiGaussianWriter;
import jahmm.io.OpdfReader;
import jahmm.io.OpdfWriter;
import jahmm.toolbox.MarkovGenerator;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * A repository of all the observation types and opdfs and the related readers,
 * writers, factories,...
 */
class Types {

    public static RelatedObjs<?> relatedObjs() throws WrongArgumentsException {
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

    private Types() {
    }
}

class IntegerRelatedObjects implements RelatedObjs<ObservationInteger> {

    final int range;

    IntegerRelatedObjects() throws WrongArgumentsException {
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
    public List<List<ObservationInteger>> readSequences(Reader reader) throws FileFormatException, IOException {
        return ObservationSequencesReader.readSequences(observationReader(), reader);
    }

    @Override
    public MarkovGenerator<ObservationInteger> generator(Hmm<ObservationInteger> hmm) {
        return new MarkovGenerator<>(hmm);
    }
}

class RealRelatedObjects implements RelatedObjs<ObservationReal> {

    public final String opdf;
    public final int nb;

    RealRelatedObjects(String opdf) throws WrongArgumentsException {
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
            return OpdfGaussianFactory.Instance;
        } else { // Gaussian mixture
            return new OpdfGaussianMixtureFactory(nb);
        }
    }

    @Override
    public OpdfReader<? extends Opdf<ObservationReal>> opdfReader() {
        if (opdf.equals("gaussian")) {
            return new OpdfGaussianReader();
        } else { // Gaussian mixture
            return new OpdfGaussianMixtureReader();
        }
    }

    @Override
    public OpdfWriter<? extends Opdf<ObservationReal>> opdfWriter() {
        if (opdf.equals("gaussian")) {
            return new OpdfGaussianWriter();
        } else { // Gaussian mixture
            return new OpdfGaussianMixtureWriter();
        }
    }

    @Override
    public List<List<ObservationReal>> readSequences(Reader reader) throws FileFormatException, IOException {
        return ObservationSequencesReader.readSequences(observationReader(), reader);
    }

    @Override
    public MarkovGenerator<ObservationReal> generator(Hmm<ObservationReal> hmm) {
        return new MarkovGenerator<>(hmm);
    }
}

class VectorRelatedObjects implements RelatedObjs<ObservationVector> {

    final int dimension;

    VectorRelatedObjects() throws WrongArgumentsException {
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
    public List<List<ObservationVector>> readSequences(Reader reader) throws FileFormatException, IOException {
        return ObservationSequencesReader.readSequences(observationReader(), reader);
    }

    @Override
    public MarkovGenerator<ObservationVector> generator(Hmm<ObservationVector> hmm) {
        return new MarkovGenerator<>(hmm);
    }
}
