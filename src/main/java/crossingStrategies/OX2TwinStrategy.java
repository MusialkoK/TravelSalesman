package crossingStrategies;

import model.Gene;
import model.Mutable;
import model.TravelSalesman;

import java.util.*;

public class OX2TwinStrategy extends AbstractCrossingStrategy {
    private final Deque<Gene> child1Genotype = new ArrayDeque<>();
    private final Deque<Gene> child2Genotype = new ArrayDeque<>();


    @Override
    public List<Mutable> createOffspring(Mutable parent1, Mutable parent2) {
        setParentsGenotype(parent1, parent2);
        calculateStep1();
        for (int i = 0; i < parent2Genotype.size(); i++) {
            calculateStep2();
            if (i < parent2Genotype.size()-1) calculateStep3();
        }
        Mutable child1 = new TravelSalesman(new ArrayList<>(child1Genotype));
        Mutable child2 = new TravelSalesman(new ArrayList<>(child2Genotype));
        return Arrays.asList(child1,child2);
    }

    private void calculateStep1() {
        child1Genotype.add(parent2Genotype.get(0));
    }

    private void calculateStep2() {
        child2Genotype.add(parent2Genotype.get(
                parent1Genotype.lastIndexOf(
                        parent2Genotype.get(
                                parent1Genotype.lastIndexOf(child1Genotype.peekLast())
                        )
                )
                )
        );
    }

    private void calculateStep3() {
        int index1 = parent1Genotype.lastIndexOf(child2Genotype.peekLast());
        child1Genotype.addLast(parent2Genotype.get(index1));
    }

}
