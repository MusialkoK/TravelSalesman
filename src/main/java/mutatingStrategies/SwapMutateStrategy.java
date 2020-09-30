package mutatingStrategies;

import model.Gene;
import model.Mutable;
import model.Randoms;
import services.TravelSalesmanService;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SwapMutateStrategy implements MutatingStrategy {

    @Override
    public void mutate(Mutable obj) {
        List<Gene> genotype = obj.getGenotype();
        Randoms randoms = new Randoms(genotype.size(), 2);
        Random random = new Random();
        double mutationRate = random.nextDouble();
        if (mutationRate < TravelSalesmanService.getMutatingChance()) {
            int[] mutateRange = randoms.getArray();
            Collections.swap(genotype, mutateRange[0], mutateRange[1]);
            obj.gotMutated();
        }
        obj.calculateFitnessValue();
    }
}
