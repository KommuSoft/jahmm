package be.ac.ulg.montefiore.run.jahmm;


/**
 * This class can build <code>OpdfInteger</code> observation probability
 * functions.
 */
public class OpdfIntegerFactory 
implements OpdfFactory<OpdfInteger>
{	
	private int nbEntries;
	
	
	/**
	 * Creates a factory for {@link OpdfInteger OpdfInteger} objects.
	 * 
	 * @param nbEntries The number of entries of the returned distribution.
	 */
	public OpdfIntegerFactory(int nbEntries)
	{
		this.nbEntries = nbEntries;
	}
	
	
	public OpdfInteger factor()
	{
		return new OpdfInteger(nbEntries);
	}
}
