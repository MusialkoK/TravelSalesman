package mutatingStrategies;

import model.Gene;
import model.Mutable;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SwapMutateStrategy implements MutatingStrategy {
    private final double mutationChance;
    private List<Gene> genotype;

    public SwapMutateStrategy(double mutationChance) {
        this.mutationChance = mutationChance;
    }

    @Override
    public Mutable mutate(Mutable obj) {
        this.genotype = obj.getGenotype();
        Random random = new Random();
        double mutationRate = random.nextDouble();
        if (mutationRate < mutationChance) {
            int mutateFrom = random.nextInt(genotype.size());
            int mutateInto = random.nextInt(genotype.size());
            do {
                if (mutateFrom != mutateInto) {
                    Collections.swap(genotype, mutateFrom, mutateInto);
                    break;
                }
            } while (true);
        }

        return obj.setGenotype(genotype);
    }
}
