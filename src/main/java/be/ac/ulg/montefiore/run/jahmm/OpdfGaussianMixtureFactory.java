package be.ac.ulg.montefiore.run.jahmm;



/**
 * Implements a factory of Gaussian mixtures distributions.
 *
 * @author Benjamin Chung (Creation)
 * @author Jean-Marc Francois (Minor adaptions)
 */
public class OpdfGaussianMixtureFactory 
implements OpdfFactory<OpdfGaussianMixture>
{
    final private int gaussiansNb;
    
    
    /**
     * Creates a new factory of Gaussian mixtures.
     *
     * @param gaussiansNb The number of Gaussian distributions involved in the
     *                    generated distributions.
     */
    public OpdfGaussianMixtureFactory(int gaussiansNb)
    {
        this.gaussiansNb = gaussiansNb;
    }
    
    
    public OpdfGaussianMixture factor()
    {
        return new OpdfGaussianMixture(gaussiansNb);
    }
}
