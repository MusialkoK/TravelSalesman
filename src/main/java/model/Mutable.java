package model;

import java.util.List;

public interface Mutable {

    List<Gene> getGenotype();

    int getGenomeLength();

    Mutable setGenotype(List<Gene> genotype);

    void calculateFitnessValue();

    Comparable getFitnessValue();

    void gotMutated();
}
