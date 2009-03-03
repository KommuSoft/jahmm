/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */

package be.ac.ulg.montefiore.run.jahmm.apps.cli;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import be.ac.ulg.montefiore.run.jahmm.*;
import be.ac.ulg.montefiore.run.jahmm.apps.cli.CommandLineArguments.Arguments;
import be.ac.ulg.montefiore.run.jahmm.io.*;
import be.ac.ulg.montefiore.run.jahmm.toolbox.MarkovGenerator;


/**
 * A repository of all the observation types and opdfs and the related
 * readers, writers, factories,...
 */
class Types
{
	public static RelatedObjs<?> relatedObjs()
	throws WrongArgumentsException
	{
		String opdf = Arguments.OPDF.get();
		
		if (opdf.equals("integer"))
			return new IntegerRelatedObjects();
		else if (opdf.equals("multi_gaussian"))
			return new VectorRelatedObjects();
		else if (opdf.equals("gaussian") || opdf.equals("gaussian_mixture"))
			return new RealRelatedObjects(opdf);
		
		throw new AssertionError("Unknown observation type");
	}
}


class IntegerRelatedObjects
implements RelatedObjs<ObservationInteger>
{
	final int range;
	
	
	public IntegerRelatedObjects() 
	throws WrongArgumentsException
	{
		range = Arguments.INTEGER_RANGE.getAsInt();
	}
	
	
	public ObservationReader<ObservationInteger> observationReader()
	{
		return new ObservationIntegerReader(range);
	}
	
	
	public ObservationWriter<ObservationInteger> observationWriter()
	{
		return new ObservationIntegerWriter();
	}

	
	public OpdfFactory<? extends Opdf<ObservationInteger>> opdfFactory()
	{
		return new OpdfIntegerFactory(range);
	}
	
	
	public OpdfReader<? extends Opdf<ObservationInteger>> opdfReader()
	{
		return new OpdfIntegerReader(range);
	}
	
	
	public OpdfWriter<? extends Opdf<ObservationInteger>> opdfWriter()
	{
		return new OpdfIntegerWriter();
	}
	
	
	public List<List<ObservationInteger>> readSequences(Reader reader)
	throws FileFormatException, IOException
	{
		return ObservationSequencesReader.readSequences(observationReader(),
				reader);
	}
	
	
	public MarkovGenerator<ObservationInteger>
	generator(Hmm<ObservationInteger> hmm)
	{
		return new MarkovGenerator<ObservationInteger>(hmm);
	}
}


class RealRelatedObjects
implements RelatedObjs<ObservationReal>
{
	public final String opdf;
	public final int nb;
	
	
	public RealRelatedObjects(String opdf)
	throws WrongArgumentsException
	{
		this.opdf = opdf;
		nb = Arguments.NB_GAUSSIANS.getAsInt();
	}
	
	
	public ObservationRealReader observationReader()
	{
		return new ObservationRealReader();
	}
	
	
	public ObservationRealWriter observationWriter()
	{
		return new ObservationRealWriter();
	}

	
	public OpdfFactory<? extends Opdf<ObservationReal>> opdfFactory()
	{
		if (opdf.equals("gaussian"))
			return new OpdfGaussianFactory();
		else { // Gaussian mixture
			return new OpdfGaussianMixtureFactory(nb);
		}
	}
	
	
	public OpdfReader<? extends Opdf<ObservationReal>> opdfReader()
	{
		if (opdf.equals("gaussian"))
			return new OpdfGaussianReader();
		else // Gaussian mixture
			return new OpdfGaussianMixtureReader();
	}
	
	
	public OpdfWriter<? extends Opdf<ObservationReal>> opdfWriter()
	{
		if (opdf.equals("gaussian"))
			return new OpdfGaussianWriter();
		else // Gaussian mixture
			return new OpdfGaussianMixtureWriter();
	}
	
	
	public List<List<ObservationReal>> readSequences(Reader reader)
	throws FileFormatException, IOException
	{
		return ObservationSequencesReader.readSequences(observationReader(),
				reader);
	}
	
	
	public MarkovGenerator<ObservationReal>
	generator(Hmm<ObservationReal> hmm)
	{
		return new MarkovGenerator<ObservationReal>(hmm);
	}
}




class VectorRelatedObjects
implements RelatedObjs<ObservationVector>
{
	final int dimension;
	
	
	public VectorRelatedObjects()
	throws WrongArgumentsException
	{
		dimension = Arguments.VECTOR_DIMENSION.getAsInt();
	}
	
	
	public ObservationVectorReader observationReader()
	{
		return new ObservationVectorReader(dimension);
	}
	
	
	public ObservationVectorWriter observationWriter()
	{
		return new ObservationVectorWriter();
	}
	
	
	public OpdfFactory<? extends Opdf<ObservationVector>> opdfFactory()
	{
		return new OpdfMultiGaussianFactory(dimension);
	}
	
	
	public OpdfReader<? extends Opdf<ObservationVector>> opdfReader()
	{
		return new OpdfMultiGaussianReader();
	}
	
	
	public OpdfWriter<? extends Opdf<ObservationVector>> opdfWriter()
	{
		return new OpdfMultiGaussianWriter();
	}
	
	
	public List<List<ObservationVector>> readSequences(Reader reader)
	throws FileFormatException, IOException
	{
		return ObservationSequencesReader.readSequences(observationReader(),
				reader);
	}
	
	
	public MarkovGenerator<ObservationVector>
	generator(Hmm<ObservationVector> hmm)
	{
		return new MarkovGenerator<ObservationVector>(hmm);
	}
}

