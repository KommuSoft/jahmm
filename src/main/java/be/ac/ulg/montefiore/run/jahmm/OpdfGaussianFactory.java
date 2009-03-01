package be.ac.ulg.montefiore.run.jahmm;


/**
 * This class can build <code>OpdfMultiGaussian</code> observation probability 
 * functions.
 */
public class OpdfGaussianFactory 
implements OpdfFactory<OpdfGaussian>
{	
	public OpdfGaussian factor() 
	{
		return new OpdfGaussian();
	}
}
