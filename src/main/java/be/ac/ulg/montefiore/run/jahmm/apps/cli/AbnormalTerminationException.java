package be.ac.ulg.montefiore.run.jahmm.apps.cli;


/**
 * Implements an exception thrown when the CLI terminates abruptly.
 */
public class AbnormalTerminationException
extends Exception
{	
	/**
	 * Creates an exception thrown when the CLI terminates abruptly.
	 */
	public AbnormalTerminationException()
	{
	}
	
	
	/**
	 * Creates an exception thrown when the CLI terminates abruptly.
	 *
	 * @param s A string describing the problem.
	 */
	public AbnormalTerminationException(String s)
	{
		super(s);
	}
	
	
	private static final long serialVersionUID = 1;
}

