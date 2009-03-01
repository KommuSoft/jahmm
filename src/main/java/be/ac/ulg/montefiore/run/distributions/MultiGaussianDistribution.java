package be.ac.ulg.montefiore.run.distributions;

import java.util.Random;


/**
 * This class implements a multi-variate Gaussian distribution.
 */
public class MultiGaussianDistribution
implements MultiRandomDistribution
{
	final private int dimension;
	final private double[] mean;
	final private double[][] covariance;
	private double[][] covarianceL = null; // covariance' Cholesky decomposition
	private double[][] covarianceInv = null;
	private double covarianceDet;
	private final static Random randomGenerator = new Random();
	
	
	/**
	 * Creates a new pseudo-random, multivariate gaussian distribution.
	 *
	 * @param mean The mean vector of the generated numbers.  This array is
	 *             copied.
	 * @param covariance The covariance of the generated numbers.  This array
	 *                   is copied.  <code>covariance[r][c]</code> is the
	 *                   element at row <code>r</code> and column
	 *                   <code>c</code>.
	 */
	public MultiGaussianDistribution(double[] mean, double[][] covariance)
	{	
		if (!SimpleMatrix.isSquare(covariance))
			throw new IllegalArgumentException("Covariance must be a square " +
			"matrix");
		
		dimension = SimpleMatrix.nbRows(covariance);
		if (mean.length != dimension)
			throw new IllegalArgumentException("mean and covariance " +
			"dimensions don't match");
		
		this.mean = SimpleMatrix.vector(mean);
		this.covariance = SimpleMatrix.matrix(covariance);
	}
	
	
	/**
	 * Creates a new pseudo-random, multivariate gaussian distribution with
	 * zero mean and identity covariance.
	 *
	 * @param dimension This distribution dimension.
	 */
	public MultiGaussianDistribution(int dimension)
	{
		if (dimension <= 0)
			throw new IllegalArgumentException();
		
		this.dimension = dimension;
		mean = SimpleMatrix.vector(dimension);
		covariance = SimpleMatrix.matrixIdentity(dimension);
	}
	
	
	public int dimension()
	{
		return dimension;
	}
	
	
	/**
	 * Returns (a copy of) this distribution's mean vector.
	 *
	 * @return This distribution's mean vector.
	 */
	public double[] mean()
	{
		return (double[]) mean.clone();
	}
	
	
	/**
	 * Returns (a copy of) this distribution's covariance matrix.
	 *
	 * @return This distribution's covariance matrix.
	 */
	public double[][] covariance()
	{
		return SimpleMatrix.matrix(covariance);
	}
	
	
	private double[][] covarianceL()
	{
		if (covarianceL == null) {
			covarianceL = SimpleMatrix.decomposeCholesky(covariance);
			covarianceDet = SimpleMatrix.determinantCholesky(covarianceL);
		}
		
		return covarianceL;
	}
	
	
	private double[][] covarianceInv()
	{
		if (covarianceInv == null)
			covarianceInv = SimpleMatrix.inverseCholesky(covarianceL());
		
		return covarianceInv;
	}
	
	
	/**
	 * Returns the covariance matrix determinant.
	 *
	 * @return The covariance matrix determinant.
	 */
	public double covarianceDet()
	{
		covarianceL();
		
		return covarianceDet;
	}
	
	
	/**
	 * Generates a pseudo-random vector according to this distribution.
	 * The vectors are generated using the Cholesky decomposition of the
	 * covariance matrix.
	 *
	 * @return A pseudo-random vector.
	 */
	public double[] generate()
	{
		double[] d = SimpleMatrix.vector(dimension);
		
		for (int i = 0; i < dimension; i++)
			d[i] = randomGenerator.nextGaussian();
		
		return SimpleMatrix.plus(SimpleMatrix.times(covarianceL(), d), mean);
	}
	
	
	public double probability(double[] v)
	{
		if (v.length != dimension)
			throw new IllegalArgumentException("Argument array size is not " +
					"compatible with this distribution");
		
		double[][] vmm = SimpleMatrix.matrix(SimpleMatrix.minus(v, mean));
		
		double expArg =
			(SimpleMatrix.times(SimpleMatrix.transpose(vmm),
					SimpleMatrix.times(covarianceInv(), vmm))[0][0]) * -.5;
		
		return Math.exp(expArg) / 
		(Math.pow(2. * Math.PI, ((double) dimension) / 2.) * 
				Math.pow(covarianceDet(), .5)); 
	}
	
	
	private static final long serialVersionUID = -2438571303843585271L;
}
