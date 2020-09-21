package crossingStrategies;

import model.Gene;
import model.Mutable;
import model.TravelSalesman;

import java.util.ArrayList;
import java.util.List;

public class TakeHalfFillRestStrategy extends AbstractCrossingStrategy {
    private int lastGeneToTake;

    @Override
    public TravelSalesman cross(Mutable parent1, Mutable parent2) {
        setParentsGenotype(parent1,parent2);
        setLastGeneToTake(parent1);
        List<Gene> offspringGenotype = new ArrayList<>();
        parent1Genotype.stream().limit(lastGeneToTake).forEach(offspringGenotype::add);
        parent2Genotype.stream().filter(g->!offspringGenotype.contains(g)).forEach(offspringGenotype::add);

        return new TravelSalesman(offspringGenotype).setParents(parent1,parent2);
    }

    private void setLastGeneToTake(Mutable mutable){
        if(lastGeneToTake==0) this.lastGeneToTake= mutable.getGenomeLength()/2;
    }

}
