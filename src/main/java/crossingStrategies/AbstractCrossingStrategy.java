package crossingStrategies;

import model.Gene;
import model.Mutable;

import java.util.List;

public abstract class AbstractCrossingStrategy implements CrossingStrategy{
    protected List<Gene> parent1Genotype;
    protected List<Gene> parent2Genotype;


    protected void setParentsGenotype(Mutable parent1, Mutable parent2){
        parent1Genotype = parent1.getGenotype();
        parent2Genotype = parent2.getGenotype();
    }
}
