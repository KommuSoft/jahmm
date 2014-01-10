/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package be.ac.ulg.montefiore.run.jahmm;

import be.ac.ulg.montefiore.run.distributions.GaussianDistribution;
import java.text.NumberFormat;
import static java.text.NumberFormat.getInstance;
import java.util.Arrays;
import static java.util.Arrays.asList;
import static java.util.Arrays.fill;
import java.util.Collection;
import java.util.logging.Logger;

/**
 * This class represents a (monovariate) gaussian distribution function.
 */
public class OpdfGaussian
        implements Opdf<ObservationReal> {

    private GaussianDistribution distribution;

    /**
     * Builds a new gaussian probability distribution with zero mean and unit
     * variance.
     */
    public OpdfGaussian() {
        distribution = new GaussianDistribution();
    }

    /**
     * Builds a new gaussian probability distribution with a given mean and
     * covariance matrix.
     *
     * @param mean The distribution's mean.
     * @param variance The distribution's variance.
     */
    public OpdfGaussian(double mean, double variance) {
        distribution = new GaussianDistribution(mean, variance);
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
        fit(asList(oa));
    }

    @Override
    public void fit(Collection<? extends ObservationReal> co) {
        double[] weights = new double[co.size()];
        fill(weights, 1. / co.size());

        fit(co, weights);
    }

    @Override
    public void fit(ObservationReal[] o, double[] weights) {
        fit(asList(o), weights);
    }

    @Override
    public void fit(Collection<? extends ObservationReal> co,
            double[] weights) {
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

        distribution = new GaussianDistribution(mean, variance);
    }

    /**
     *
     * @return
     */
    @Override
    public OpdfGaussian clone() {
        try {
            return (OpdfGaussian) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return toString(getInstance());
    }

    @Override
    public String toString(NumberFormat numberFormat) {
        return "Gaussian distribution --- "
                + "Mean: " + numberFormat.format(distribution.mean())
                + " Variance " + numberFormat.format(distribution.variance());
    }

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(OpdfGaussian.class.getName());
}
