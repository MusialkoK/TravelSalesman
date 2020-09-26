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
        Randoms randoms = new Randoms(genotype.size(), 2,false);
        Random random = new Random();
        double mutationRate = random.nextDouble();
        if (mutationRate < TravelSalesmanService.getMutatingChance()) {
            int mutateFrom = randoms.draw();
            int mutateInto = randoms.draw();
            Collections.swap(genotype, mutateFrom, mutateInto);
            obj.gotMutated();
        }
        obj.calculateFitnessValue();
    }
}
