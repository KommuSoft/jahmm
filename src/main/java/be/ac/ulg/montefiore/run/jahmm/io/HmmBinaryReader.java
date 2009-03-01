package be.ac.ulg.montefiore.run.jahmm.io;

import java.io.*;

import be.ac.ulg.montefiore.run.jahmm.Hmm;


/**
 * This class can read Hidden Markov Models from a byte stream.
 * <p>
 * The HMM objects are simply deserialized.  HMMs could thus be unreadable using
 * a different release of this library.
 */
public class HmmBinaryReader
{	
	/**
	 * Reads a HMM from a byte stream.
	 *
	 * @param stream Holds the byte stream the HMM is read from.
	 * @return The {@link be.ac.ulg.montefiore.run.jahmm.Hmm HMM} read.
	 */
	static public Hmm<?> read(InputStream stream)
	throws IOException
	{		
		ObjectInputStream ois = new ObjectInputStream(stream);
		
		try {
			return (Hmm) ois.readObject();
		}
		catch(ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
