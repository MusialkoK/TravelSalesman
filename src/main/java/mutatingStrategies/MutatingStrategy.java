package mutatingStrategies;

import model.Gene;

import java.util.List;

public interface MutatingStrategy {

    List<Gene> mutate(List<Gene> genotype);
}
