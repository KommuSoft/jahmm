/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package jahmm.observables;

import jahmm.distributions.GaussianDistribution;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collection;

/**
 * This class represents a (mono variate) Gaussian distribution function.
 */
public class OpdfGaussian extends OpdfBase<ObservationReal> implements Opdf<ObservationReal> {

    private static final long serialVersionUID = 1L;

    private final GaussianDistribution distribution;

    /**
     * Builds a new Gaussian probability distribution with zero mean and unit
     * variance.
     */
    public OpdfGaussian() {
        distribution = new GaussianDistribution();
    }

    /**
     * Builds a new Gaussian probability distribution with a given mean and
     * covariance matrix.
     *
     * @param mean The distribution's mean.
     * @param variance The distribution's variance.
     */
    public OpdfGaussian(double mean, double variance) {
        distribution = new GaussianDistribution(mean, variance);
    }

    /**
     * Builds a new Gaussian probability distribution with a given mean and
     * covariance matrix.
     *
     * @param mean The distribution's mean.
     * @param variance The distribution's variance.
     */
    private OpdfGaussian(GaussianDistribution distribution) {
        this.distribution = distribution;
    }

    /**
     * Returns this distribution's mean value.
     *
     * @return This distribution's mean value.
     */
    public double mean() {
        return distribution.mean();
    }

    /**
     * Returns this distribution's variance.
     *
     * @return This distribution's variance.
     */
    public double variance() {
        return distribution.variance();
    }

    @Override
    public double probability(ObservationReal o) {
        return distribution.probability(o.value);
    }

    @Override
    public ObservationReal generate() {
        return new ObservationReal(distribution.generate());
    }

    @Override
    public void fit(ObservationReal... oa) {
        fit(Arrays.asList(oa));
    }

    @Override
    public void fit(Collection<? extends ObservationReal> co) {
        double[] weights = new double[co.size()];
        Arrays.fill(weights, 1. / co.size());

        fit(co, weights);
    }

    @Override
    public void fit(ObservationReal[] o, double... weights) {
        fit(Arrays.asList(o), weights);
    }

    @Override
    public void fit(Collection<? extends ObservationReal> co, double... weights) {
        if (co.isEmpty() || co.size() != weights.length) {
            throw new IllegalArgumentException();
        }

        // Compute mean
        double mean = 0.;
        int i = 0;
        for (ObservationReal o : co) {
            mean += o.value * weights[i++];
        }

        // Compute variance
        double variance = 0.;
        i = 0;
        for (ObservationReal o : co) {
            double d = o.value - mean;

            variance += d * d * weights[i++];
        }

        this.distribution.setMean(mean);
        this.distribution.setVariance(variance);
    }

    @Override
    public OpdfGaussian clone() throws CloneNotSupportedException {
        return new OpdfGaussian(this.distribution.clone());
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return toString(NumberFormat.getInstance());
    }

    @Override
    public String toString(NumberFormat numberFormat) {
        return String.format("Gaussian distribution --- Mean: %s Variance %s", numberFormat.format(distribution.mean()), numberFormat.format(distribution.variance()));
    }
}
