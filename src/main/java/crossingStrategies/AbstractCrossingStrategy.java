package crossingStrategies;

import model.Gene;
import model.Mutable;

import java.util.List;

public abstract class AbstractCrossingStrategy implements CrossingStrategy{
    protected List<Gene> parent1Genotype;
    protected List<Gene> parent2Genotype;
    protected int genomeLength;


    protected void setParentsGenotype(Mutable parent1, Mutable parent2){
        this.parent1Genotype = parent1.getGenotype();
        this.parent2Genotype = parent2.getGenotype();
        setLastGeneToTake(parent1);
    }

    private void setLastGeneToTake(Mutable mutable){
        if(genomeLength==0) this.genomeLength= mutable.getGenomeLength();
    }
}
