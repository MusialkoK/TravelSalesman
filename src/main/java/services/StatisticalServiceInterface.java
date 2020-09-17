package services;

import model.Mutable;

public interface StatisticalServiceInterface {

    Mutable getBestIndividual();
    Mutable getWorstIndividual();
    double getMutationRatio();
    double getGenerationDiversityIndex();

}
