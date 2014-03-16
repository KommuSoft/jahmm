package jahmm.jadetree.objectattributes;

import java.util.EnumSet;
import java.util.Set;
import java.util.logging.Logger;

public class InternalNominalObjectAttributeBase<TSource extends Enum<TSource>> extends NominalObjectAttributeBase<TSource, TSource> {

    private static final Logger LOG = Logger.getLogger(InternalNominalObjectAttributeBase.class.getName());

    private final String name;
    private final Class<TSource> classdef;

    public InternalNominalObjectAttributeBase(String name, Class<TSource> classdef) {
        this.name = name;
        this.classdef = classdef;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public TSource evaluate(TSource x) {
        return x;
    }

    @Override
    public Set<TSource> getPossibleValues() {
        return EnumSet.allOf(classdef);
    }

}
