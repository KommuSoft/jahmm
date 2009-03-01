package be.ac.ulg.montefiore.run.jahmm.apps.cli;


/**
 * Implements an exception thrown when the CLI is given wrong arguments.
 */
public class WrongArgumentsException
extends AbnormalTerminationException
{	
	/**
	 * Creates an exception thrown when the CLI is given wrong arguments.
	 */
	public WrongArgumentsException()
	{
		super("Wrong arguments. Use 'Cli -help' for help.");
	}
	
	
	/**
	 * Creates an exception thrown when the CLI is given wrong arguments.
	 *
	 * @param s A string describing the problem.
	 */
	public WrongArgumentsException(String s)
	{
		super(s + ". Use 'Cli -help' for help.");
	}
	
	
	private static final long serialVersionUID = 1;
}

