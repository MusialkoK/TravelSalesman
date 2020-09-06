package mutatingStrategies;

import model.Gene;
import model.Mutable;
import model.TwoRandoms;
import services.TravelSalesmanService;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SwapMutateStrategy implements MutatingStrategy {
    private List<Gene> genotype;

    @Override
    public Mutable mutate(Mutable obj) {
        this.genotype = obj.getGenotype();
        TwoRandoms twoRandoms = new TwoRandoms(genotype.size(), false);
        Random random = new Random();
        double mutationRate = random.nextDouble();
        if (mutationRate < TravelSalesmanService.getMutatingChance()) {
            int mutateFrom = twoRandoms.getFirstRandom();
            int mutateInto = twoRandoms.getSecondRandom();
            Collections.swap(genotype, mutateFrom, mutateInto);
            twoRandoms.reset();
        }

        return obj.setGenotype(genotype);
    }
}
