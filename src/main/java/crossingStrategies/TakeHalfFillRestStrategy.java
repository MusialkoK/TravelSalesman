package crossingStrategies;

import model.Gene;
import model.Mutable;
import model.TravelSalesman;

import java.util.ArrayList;
import java.util.List;

public class TakeHalfFillRestStrategy extends AbstractCrossingStrategy {
    protected int lastGeneToTake;

    @Override
    public List<Mutable> createOffspring(Mutable parent1, Mutable parent2) {
        setParentsGenotype(parent1,parent2);
        this.lastGeneToTake = genomeLength / 2;
        List<Gene> offspringGenotype = new ArrayList<>();
        parent1Genotype.stream().limit(lastGeneToTake).forEach(offspringGenotype::add);
        parent2Genotype.stream().filter(g -> !offspringGenotype.contains(g)).forEach(offspringGenotype::add);
        List<Mutable> result = new ArrayList<>();
        result.add(new TravelSalesman(offspringGenotype).setParents(parent1, parent2));
        return result;
    }
}
