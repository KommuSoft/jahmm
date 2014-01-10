/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */
package be.ac.ulg.montefiore.run.distributions;

import static java.lang.Math.exp;
import static java.lang.Math.log;
import static java.lang.Math.random;
import java.util.logging.Logger;

/**
 * This class implements a generator of Poisson distributed numbers.
 */
public class PoissonDistribution
        implements DiscreteDistribution {

    private final double mean;

    // First values of log(k!) (k up to 29)
    private static final double[] logFactTable = {
        0.00000000000000000, 0.00000000000000000, 0.69314718055994531,
        1.79175946922805500, 3.17805383034794562, 4.78749174278204599,
        6.57925121201010100, 8.52516136106541430, 10.60460290274525023,
        12.80182748008146961, 15.10441257307551530, 17.50230784587388584,
        19.98721449566188615, 22.55216385312342289, 25.19122118273868150,
        27.89927138384089157, 30.67186010608067280, 33.50507345013688888,
        36.39544520803305358, 39.33988418719949404, 42.33561646075348503,
        45.38013889847690803, 48.47118135183522388, 51.60667556776437357,
        54.78472939811231919, 58.00360522298051994, 61.26170176100200198,
        64.55753862700633106, 67.88974313718153498, 71.25703896716800901
    };

    private final double C0 = 9.18938533204672742e-01;
    private final double C1 = 8.33333333333333333e-02;
    private final double C3 = -2.77777777777777778e-03;
    private final double C5 = 7.93650793650793651e-04;
    private final double C7 = -5.95238095238095238e-04;

    /**
     * Creates a new pseudo-random Poisson distribution.
     *
     * @param mean The mean duration between two consecutive events.
     */
    public PoissonDistribution(double mean) {
        if (mean < 0.) {
            throw new IllegalArgumentException();
        }

        this.mean = mean;
    }

    /**
     * Returns this distribution's mean.
     *
     * @return This distribution's mean.
     */
    public double mean() {
        return mean;
    }

    @Override
    public int generate() {
        int count = 0;
        double product = 1.;
        final double elambda = exp(-mean);

        while (product > elambda) {
            product *= random();
            count++;
        }

        return count - 1;
    }

    @Override
    public double probability(int n) {
        return exp(n * log(mean) - logFactorial(n) - mean);
    }

    // Based on Stirling approximation
    private double logFactorial(int n) {
        if (n >= logFactTable.length) {
            double r = 1. / (double) n;

            return (n + .5) * log(n) - n + C0
                    + r * (C1 + r * r * (C3 + r * r * (C5 + r * r * C7)));
        } else {
            return logFactTable[n];
        }
    }

    private static final long serialVersionUID = 2_516_179_242_233_627_286L;
    private static final Logger LOG = Logger.getLogger(PoissonDistribution.class.getName());
}
