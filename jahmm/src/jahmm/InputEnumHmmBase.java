package jahmm;

import jahmm.observables.Observation;
import jahmm.observables.Opdf;
import jahmm.observables.OpdfFactory;
import java.util.logging.Logger;
import jutlis.lists.ListArray;

/**
 * A constructor class to construct an InputHmm based on all the values of an
 * enum type.
 *
 * @author kommusoft
 * @param <TObs> The type of the observations of the Hidden Markov Model.
 * @param <TIn> The type of the input of the Hidden Markov Model.
 */
public class InputEnumHmmBase<TObs extends Observation, TIn extends Enum<TIn>> extends InputHmmBase<TObs, TIn> {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(InputEnumHmmBase.class.getName());

    /**
     * Creates a new IHMM. Each state has the same <i>pi</i> value and the
     * transition probabilities are all equal.
     *
     * @param nbStates The (strictly positive) number of states of the IHMM.
     * @param opdfFactory A pdf generator that is used to build the pdfs
     * associated to each state.
     * @param classdef Gets the class definition of the input to derive the enum
     * constants from.
     */
    public InputEnumHmmBase(int nbStates, OpdfFactory<? extends Opdf<TObs>> opdfFactory, Class<TIn> classdef) {
        super(nbStates, opdfFactory, new ListArray<>(classdef.getEnumConstants()));
    }

    /**
     * Creates a new InputHmmBase instance that contains the same data.
     *
     * @return A new InputHmmBase instance that contains the same data.
     * @throws CloneNotSupportedException If something went wrong in the cloning
     * process.
     */
    @Override
    public InputHmmBase<TObs, TIn> clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
