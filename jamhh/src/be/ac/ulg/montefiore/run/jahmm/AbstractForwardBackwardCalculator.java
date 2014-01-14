/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.ac.ulg.montefiore.run.jahmm;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author kommusoft
 */
public interface AbstractForwardBackwardCalculator {
    
    public <O extends Observation> void computeAlpha(Hmm<? super O> hmm, Collection<O> oseq);
    
    public <O extends Observation> void computeBeta(Hmm<? super O> hmm, List<O> oseq);
    
}
