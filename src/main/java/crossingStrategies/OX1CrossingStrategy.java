package crossingStrategies;

import model.Mutable;

public class OX1CrossingStrategy extends AbstractCrossingStrategy{




    @Override
    public Mutable cross(Mutable parent1, Mutable parent2) {
        setParentsGenotype(parent1,parent2);



        return null;
    }
}
