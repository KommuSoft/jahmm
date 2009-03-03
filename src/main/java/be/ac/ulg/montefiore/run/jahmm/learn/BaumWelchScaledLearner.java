/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */

package be.ac.ulg.montefiore.run.jahmm.learn;

import java.util.*;

import be.ac.ulg.montefiore.run.jahmm.*;


/**
 * An implementation of the Baum-Welch learning algorithm.  It uses a
 * scaling mechanism so as to avoid underflows.
 * <p>
 * For more information on the scaling procedure, read <i>Rabiner</i> and 
 * <i>Juang</i>'s <i>Fundamentals of speech recognition</i> (Prentice Hall,
 * 1993).
 */
public class BaumWelchScaledLearner
extends BaumWelchLearner
{	
	/**
	 * Initializes a Baum-Welch algorithm implementation.
	 */
	public BaumWelchScaledLearner()
	{
	}
	
	
	protected <O extends Observation> ForwardBackwardCalculator
	generateForwardBackwardCalculator(List<? extends O> sequence,
			Hmm<O> hmm)
	{
		return new ForwardBackwardScaledCalculator(sequence, hmm, 
				EnumSet.allOf(ForwardBackwardCalculator.Computation.class));
	}
	
	
	/* Here, the xi (and, thus, gamma) values are not divided by the
	 probability of the sequence because this probability might be
	 too small and induce an underflow. xi[t][i][j] still can be
	 interpreted as P[q_t = i and q_(t+1) = j | obsSeq, hmm] because
	 we assume that the scaling factors are such that their product
	 is equal to the inverse of the probability of the sequence. */
	protected <O extends Observation> double[][][]
	estimateXi(List<? extends O> sequence, ForwardBackwardCalculator fbc,
			Hmm<O> hmm)
	{	
		if (sequence.size() <= 1)
			throw new IllegalArgumentException("Observation sequence too " + 
			"short");
		
		double xi[][][] = 
			new double[sequence.size() - 1][hmm.nbStates()][hmm.nbStates()];
		
		Iterator<? extends O> seqIterator = sequence.iterator();
		seqIterator.next();
		
		for (int t = 0; t < sequence.size() - 1; t++) {
			O observation = seqIterator.next();
			
			for (int i = 0; i < hmm.nbStates(); i++)
				for (int j = 0; j < hmm.nbStates(); j++)
					xi[t][i][j] = fbc.alphaElement(t, i) *
					hmm.getAij(i, j) * 
					hmm.getOpdf(j).probability(observation) *
					fbc.betaElement(t + 1, j);
		}
		
		return xi;
	}
}
