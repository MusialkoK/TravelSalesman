package crossingStrategies;

import model.Gene;
import model.Mutable;
import model.TravelSalesman;

import java.util.*;

public class CX2TwinStrategy extends AbstractCrossingStrategy {
    private final Deque<Gene> child1Genotype = new ArrayDeque<>();
    private final Deque<Gene> child2Genotype = new ArrayDeque<>();
    int breakPosition;
    List<Gene> newParent1Genotype;
    List<Gene> newParent2Genotype;

    @Override
    public List<Mutable> createOffspring(Mutable parent1, Mutable parent2) {
        boolean goToStep4 = false;
        setParentsGenotype(parent1, parent2);

        child1Genotype.add(calculateStep1());

        for (int i = 0; i < parent2Genotype.size(); i++) {
            child2Genotype.add(calculateStep2());
            if (child2Genotype.contains(parent1Genotype.get(0))) {
                breakPosition = child1Genotype.size();
                break;
            }
            if (i < parent2Genotype.size() - 1) child1Genotype.addLast(calculateStep3());
        }
        if (child1Genotype.size() < parent1Genotype.size()) goToStep4 = true;


        if (goToStep4) {
            parent1Genotype = crossOutGenes(parent1Genotype, child2Genotype);
            parent2Genotype = crossOutGenes(parent2Genotype, child1Genotype);

            child1Genotype.add(calculateStep1());

            for (int i = 0; i < parent2Genotype.size(); i++) {
                child2Genotype.add(calculateStep2());
                if (child2Genotype.contains(parent1Genotype.get(0))) {
                    breakPosition = child1Genotype.size();
                    break;
                }
                if (i < parent2Genotype.size() - 1) child1Genotype.addLast(calculateStep3());
            }
        }


        Mutable child1 = new TravelSalesman(new ArrayList<>(child1Genotype));
        Mutable child2 = new TravelSalesman(new ArrayList<>(child2Genotype));
        return Arrays.asList(child1, child2);
    }

    private Gene calculateStep1() {
        return parent2Genotype.get(0);
    }

    private Gene calculateStep2() {
        return parent2Genotype.get(parent1Genotype.lastIndexOf(parent2Genotype.get(parent1Genotype.lastIndexOf(child1Genotype.peekLast()))));
    }

    private Gene calculateStep3() {
        int index1 = parent1Genotype.lastIndexOf(child2Genotype.peekLast());
        return parent2Genotype.get(index1);
    }

    private List<Gene> crossOutGenes(List<Gene> parentGenotype, Deque<Gene> childGenotype) {
        List<Gene> result = new ArrayList<>();
        parentGenotype.forEach(g -> {
            if (!childGenotype.contains(g)) {
                result.add(g);
            }
        });
        return result;
    }

}
