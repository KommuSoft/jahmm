package be.ac.ulg.montefiore.run.jahmm.io;

import java.io.*;

import be.ac.ulg.montefiore.run.jahmm.Hmm;


/**
 * This class can write a Hidden Markov Models to a byte stream.
 * <p>
 * The HMM objects are simply serialized.  HMMs could thus be unreadable using
 * a different release of this library.
 */
public class HmmBinaryWriter
{	
	/**
	 * Writes a HMM to byte stream.
	 *
	 * @param stream Holds the byte stream the HMM is written to.
	 */
	static public void write(OutputStream stream, Hmm<?> hmm)
	throws IOException
	{		
		ObjectOutputStream oos = new ObjectOutputStream(stream);	
		oos.writeObject(hmm);
	}
}
