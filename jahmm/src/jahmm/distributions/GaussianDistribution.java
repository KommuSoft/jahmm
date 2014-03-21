/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package jahmm.distributions;

import java.util.Random;

/**
 * This class implements a Gaussian distribution.
 */
public class GaussianDistribution implements RandomDistribution {

    private final static Random randomGenerator = new Random();

    private static final long serialVersionUID = 9_127_329_839_769_283_975L;

    private double mean;
    private double variance;

    /**
     * Creates a new pseudo-random, Gaussian distribution with zero mean and
     * unitary variance.
     */
    public GaussianDistribution() {
        this(0., 1.);
    }

    /**
     * Creates a new pseudo-random, Gaussian distribution.
     *
     * @param mean The mean value of the generated numbers.
     * @param variance The variance of the generated numbers.
     */
    public GaussianDistribution(double mean, double variance) {
        this.setMean(mean);
        this.setVariance(variance);
    }

    /**
     * Returns this distribution's mean value.
     *
     * @return This distribution's mean value.
     */
    public double mean() {
        return mean;
    }

    /**
     * Returns this distribution's variance.
     *
     * @return This distribution's variance.
     */
    public double variance() {
        return variance;
    }

    @Override
    public double generate() {
        return randomGenerator.nextGaussian() * Math.sqrt(this.variance) + mean;
    }

    @Override
    public double probability(double n) {
        double expArg = -.5 * (n - mean) * (n - mean) / variance;
        return Math.pow(2. * Math.PI * variance, -.5)
                * Math.exp(expArg);
    }

    @Override
    public GaussianDistribution clone() throws CloneNotSupportedException {
        return new GaussianDistribution(this.mean, this.variance);
    }

    /**
     * @param mean the mean to set
     */
    public void setMean(double mean) {
        this.mean = mean;
    }

    /**
     * @param deviation the deviation to set
     */
    public void setDeviation(double deviation) {
        this.variance = deviation * deviation;
    }

    /**
     * @param variance the variance to set
     */
    public void setVariance(double variance) {
        if (variance <= 0.) {
            throw new IllegalArgumentException("Variance must be positive");
        }
        this.variance = variance;
    }
}
