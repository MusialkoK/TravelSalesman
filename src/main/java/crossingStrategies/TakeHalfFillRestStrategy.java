package crossingStrategies;

import model.Gene;
import model.Mutable;
import model.TravelSalesman;

import java.util.ArrayList;
import java.util.List;

public class TakeHalfFillRestStrategy implements CrossingStrategy {
    private List<Gene> parent1Genotype;
    private List<Gene> parent2Genotype;
    private final int lastGeneToTake;
    private final int genotypeLength;

    public TakeHalfFillRestStrategy(Mutable mutable) {
        this.genotypeLength = mutable.getGenomeLength();
        this.lastGeneToTake = genotypeLength / 2;
    }

    @Override
    public TravelSalesman cross(Mutable parent1, Mutable parent2) {
        parent1Genotype = parent1.getGenotype();
        parent2Genotype = parent2.getGenotype();

        List<Gene> offspringGenotype = new ArrayList<>();
        parent1Genotype.stream().limit(lastGeneToTake).forEach(offspringGenotype::add);
        parent2Genotype.stream().filter(g->!offspringGenotype.contains(g)).forEach(offspringGenotype::add);

        return new TravelSalesman(offspringGenotype).setParents(parent1,parent2);
    }

}
