/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package jahmm.distributions;

import java.util.Random;

/**
 * This class implements a multi-variate Gaussian distribution.
 */
public class MultiGaussianDistribution implements MultiRandomDistribution {

    private final static Random randomGenerator = new Random();

    private static final long serialVersionUID = 2_438_571_303_843_585_271L;

    private final int dimension;
    private final double[] mean;
    private final double[][] covariance;
    private double[][] covarianceL = null; // covariance' Cholesky decomposition
    private double[][] covarianceInv = null;
    private double covarianceDet;

    /**
     * Creates a new pseudo-random, multivariate gaussian distribution.
     *
     * @param mean The mean vector of the generated numbers. This array is
     * copied.
     * @param covariance The covariance of the generated numbers. This array is
     * copied.  <code>covariance[r][c]</code> is the element at row
     * <code>r</code> and column <code>c</code>.
     */
    public MultiGaussianDistribution(double[] mean, double[][] covariance) {
        if (!SimpleMatrix.isSquare(covariance)) {
            throw new IllegalArgumentException("Covariance must be a square matrix");
        }
        dimension = SimpleMatrix.nbRows(covariance);
        if (mean.length != dimension) {
            throw new IllegalArgumentException("mean and covariance dimensions don't match");
        }
        this.mean = SimpleMatrix.vector(mean);
        this.covariance = SimpleMatrix.matrix(covariance);
    }

    /**
     * Creates a new pseudo-random, multivariate gaussian distribution with zero
     * mean and identity covariance.
     *
     * @param dimension This distribution dimension.
     */
    public MultiGaussianDistribution(int dimension) {
        if (dimension <= 0) {
            throw new IllegalArgumentException();
        }
        this.dimension = dimension;
        this.mean = SimpleMatrix.vector(dimension);
        this.covariance = SimpleMatrix.matrixIdentity(dimension);
    }

    @Override
    public int dimension() {
        return this.dimension;
    }

    /**
     * Returns (a copy of) this distribution's mean vector.
     *
     * @return This distribution's mean vector.
     */
    public double[] mean() {
        return this.mean.clone();
    }

    /**
     * Returns (a copy of) this distribution's covariance matrix.
     *
     * @return This distribution's covariance matrix.
     */
    public double[][] covariance() {
        return SimpleMatrix.matrix(this.covariance);
    }

    private double[][] covarianceL() {
        if (this.covarianceL == null) {
            this.covarianceL = SimpleMatrix.decomposeCholesky(this.covariance);
            this.covarianceDet = SimpleMatrix.determinantCholesky(this.covarianceL);
        }

        return covarianceL;
    }

    private double[][] covarianceInv() {
        if (this.covarianceInv == null) {
            this.covarianceInv = SimpleMatrix.inverseCholesky(covarianceL());
        }

        return this.covarianceInv;
    }

    /**
     * Returns the covariance matrix determinant.
     *
     * @return The covariance matrix determinant.
     */
    public double covarianceDet() {
        this.covarianceL();
        return this.covarianceDet;
    }

    /**
     * Generates a pseudo-random vector according to this distribution. The
     * vectors are generated using the Cholesky decomposition of the covariance
     * matrix.
     *
     * @return A pseudo-random vector.
     */
    @Override
    public double[] generate() {
        double[] d = SimpleMatrix.vector(this.dimension);
        for (int i = 0; i < this.dimension; i++) {
            d[i] = this.randomGenerator.nextGaussian();
        }

        return SimpleMatrix.plus(SimpleMatrix.times(this.covarianceL(), d), this.mean);
    }

    @Override
    public double probability(double[] v) {
        if (v.length != this.dimension) {
            throw new IllegalArgumentException("Argument array size is not compatible with this distribution");
        }
        double[][] vmm = SimpleMatrix.matrix(SimpleMatrix.minus(v, mean));
        double expArg = -0.5d * SimpleMatrix.times(SimpleMatrix.transpose(vmm), SimpleMatrix.times(this.covarianceInv(), vmm))[0][0];
        return Math.exp(expArg) / (Math.pow(2.0d * Math.PI, 0.5d * dimension) * Math.pow(covarianceDet(), 0.5d));
    }

    public void setMean(double[] mean) {
        System.arraycopy(mean, 0, this.mean, 0, mean.length);
    }

    public void setMean(int i, double mean) {
        this.mean[i] = mean;
    }

    public void setCovariance(int i, int j, double covariance) {
        this.covariance[i][j] = covariance;
    }

    public double mean(int i) {
        return this.mean[i];
    }

    public double covariance(int i, int j) {
        return this.covariance[i][j];
    }

    public void setCovariance(double[][] covariance) {
        for (int i = 0x00; i < covariance.length; i++) {
            System.arraycopy(covariance[i], 0, this.covariance[i], 0, covariance[i].length);
        }
    }

    @Override
    public MultiGaussianDistribution clone() throws CloneNotSupportedException {
        return new MultiGaussianDistribution(this.mean, this.covariance);
    }
}
