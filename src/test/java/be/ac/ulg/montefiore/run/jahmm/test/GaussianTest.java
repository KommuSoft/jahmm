package be.ac.ulg.montefiore.run.jahmm.test;

import junit.framework.TestCase;
import be.ac.ulg.montefiore.run.distributions.GaussianDistribution;
import be.ac.ulg.montefiore.run.distributions.RandomDistribution;
import be.ac.ulg.montefiore.run.jahmm.*;


public class GaussianTest
extends TestCase
{
	final static private double DELTA = 5.E-2;
	final static private int nbObservations = 10000;
	
	
	public void testGaussianFit()
	{	
		double[] mean = { 2., 4. };
		double[][] covariance = { { 3., 2. }, { 2., 4. } };
		
		OpdfMultiGaussian omg1 = new OpdfMultiGaussian(mean, covariance);
		
		assertEquals(omg1.dimension(), 2);
		
		ObservationVector[] obs = new ObservationVector[nbObservations];
		for (int i = 0; i < obs.length; i++)
			obs[i] = omg1.generate();
		
		OpdfMultiGaussian omg2 = new OpdfMultiGaussian(
				new double[] { 0., 0. },
				new double[][] { { 0., 0. }, { 0., 0. } });
		
		assertEquals(omg2.dimension(), 2);
		
		omg2.fit(obs);
		
		assertTrue("Different mean arrays: " +
				toString(mean) + " differ from " + toString(omg2.mean()),
				equalsArrays(mean, omg2.mean()));
		
		for (int i = 0; i < 2; i++)
			assertTrue("Different covariance arrays: " + 
					toString(omg1.covariance()[i]) + " differ from " +
					toString(omg2.covariance()[i]),
					equalsArrays(omg1.covariance()[i], omg2.covariance()[i],
							DELTA * 10.));
	}
	
	
	public void testGaussianMixture()
	{	
		/*
		 * Generates observations related to 2 gaussians : (0., 1.) and (4., 2.).
		 * Proportions : 1/3, 2/3
		 */
		ObservationReal[] observations = new ObservationReal[nbObservations];
		
		for (int g = 0, i = 0; g < 2; g++) {
			RandomDistribution d = new GaussianDistribution(g * 4.,
					1 + g);
			
			for (; i < ((g == 0) ? nbObservations / 3 : nbObservations); i++)
				observations[i] = new ObservationReal(d.generate());
		}
		
		// Fit distribution to observations
		OpdfGaussianMixture gm = new OpdfGaussianMixture(2);
		
		for (int i = 0; i < 20; i++)
			gm.fit(observations);
		
		assertTrue("Wrong proportion values (" + gm.proportions()[0] + ", " +
				gm.proportions()[1] + ")",
				equalsArrays(new double[] { 1. / 3., 2. / 3. },
						gm.proportions(), DELTA));
		
		assertTrue("Wrong mean values (" + gm.means()[0] + ", " +
				gm.means()[1] + ")",
				equalsArrays(new double[] { 0., 4. }, gm.means(),
						DELTA * 10.));
		assertTrue("Wrong variance values (" + gm.variances()[0] + ", " +
				gm.variances()[1] + ")",
				equalsArrays(new double[] { 1., 2. }, gm.variances(),
						DELTA * 10.));
	}
	
	
	public void testMultiGaussianFit()
	{	
		double[] mean = { 2., 4. };
		double[][] covariance = { { 3., 2. }, { 2., 4. } };
		
		OpdfMultiGaussian omg1 = new OpdfMultiGaussian(mean, covariance);
		
		assertEquals(omg1.dimension(), 2);
		
		ObservationVector[] obs = new ObservationVector[100000];
		for (int i = 0; i < obs.length; i++)
			obs[i] = omg1.generate();
		
		OpdfMultiGaussian omg2 = new OpdfMultiGaussian(
				new double[] { 0., 0. },
				new double[][] { { 0., 0. }, { 0., 0. } });
		
		assertEquals(omg2.dimension(), 2);
		
		omg2.fit(obs);
		
		assertTrue("Different mean arrays", equalsArrays(mean, omg2.mean()));
		
		for (int i = 0; i < 2; i++)
			assertTrue("Different covariance arrays", 
					equalsArrays(omg1.covariance()[i], omg2.covariance()[i]));
	}

	
	static String toString(double[] a)
	{
		String s = "[ ";
		
		for (double e : a)
			s += e + " ";
		
		return s + "]";
	}
	
	
	static boolean equalsArrays(double[] ea, double[] ra)
	{
		return equalsArrays(ea, ra, DELTA);
	}
	
	
	static boolean equalsArrays(double[] ea, double[] ra, double delta)
	{
		if (ea.length != ra.length)
			return false;
		
		for (int i = 0; i < ra.length; i++)
			if (Math.abs(ea[i] - ra[i]) > delta)
				return false;
		
		return true;
	}
}
