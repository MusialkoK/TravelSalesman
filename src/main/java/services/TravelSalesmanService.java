package services;

import lombok.Getter;
import model.City;
import model.Gene;
import model.TravelSalesman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TravelSalesmanService {
    @Getter
    private static int generationAbundance = 100;
    @Getter
    private static int numberOfGenerations = 200;
    @Getter
    private static int numberOfReproducers = 20;
    @Getter
    private static double mutatingChance = 0.02;


    public List<TravelSalesman> createFirstGeneration() {
        List<TravelSalesman> result = new ArrayList<>();
        for (int i = 0; i < generationAbundance; i++) {
            result.add(new TravelSalesman(getIndividualGenome(CityService.getOperatingCityList())).setGenerationNumber(0).setId(i));
        }
        return result;
    }

    private List<Gene> getIndividualGenome(List<City> genePool) {
        Collections.shuffle(genePool);
        return new ArrayList<>(genePool);
    }
}
