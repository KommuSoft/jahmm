package be.ac.ulg.montefiore.run.jahmm.io;


/**
 * This exception reports the reading of an invalid (syntatically or
 * lexically incorrect) file.
 */
public class FileFormatException
extends Exception
{	
	/**
	 * Creates a new object reporting the reading of an invalid file.
	 */
	public FileFormatException()
	{
	}
	
	
	/**
	 * Creates a new object reporting the reading of an invalid file.
	 *
	 * @param s A string describing the problem.
	 */
	public FileFormatException(String s)
	{
		super(s);
	}
	
	
	/**
	 * Creates a new object reporting the reading of an invalid file.
	 *
	 * @param lineNb The line number where the problem occured.
	 * @param s A string describing the problem.
	 */
	public FileFormatException(int lineNb, String s)
	{
		super("Line " + lineNb + ": " + s);
	}	
	
	
	private static final long serialVersionUID = 2;
}
