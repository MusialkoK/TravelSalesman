package mutatingStrategies;

import model.Gene;
import model.Mutable;
import services.TravelSalesmanService;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SwapMutateStrategy implements MutatingStrategy {
    private List<Gene> genotype;

    @Override
    public Mutable mutate(Mutable obj) {
        this.genotype = obj.getGenotype();
        Random random = new Random();
        double mutationRate = random.nextDouble();
        if (mutationRate < TravelSalesmanService.getMutatingChance()) {
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
