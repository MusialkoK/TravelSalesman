package services;

import crossingStrategies.CrossingStrategy;
import lombok.Getter;
import lombok.Setter;
import model.City;
import model.Gene;
import model.TravelSalesman;
import mutatingStrategies.MutatingStrategy;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class TravelSalesmanService {
    @Setter
    private static Session session;

    @Getter
    @Setter
    private static int generationAbundance = 100;
    @Getter
    @Setter
    private static int numberOfGenerations = 20;
    @Getter
    @Setter
    private static int numberOfReproducers = 20;
    @Getter
    @Setter
    private static double mutatingChance = 0.02;
    @Setter
    private CrossingStrategy crossingStrategy;
    @Setter
    private MutatingStrategy mutatingStrategy;

    private final TwoRandoms twoRandoms = new TwoRandoms();

    private int generationCounter = 0;
    private int individualCounter = 0;


    public List<TravelSalesman> createFirstGeneration() {
        List<TravelSalesman> result = new ArrayList<>();
        for (int i = 0; i < generationAbundance; i++) {
            result.add(new TravelSalesman(getIndividualGenome(CityService.getOperatingCityList()))
                    .setGenerationNumber(generationCounter)
                    .setId(individualCounter++));
        }
        generationCounter++;
        saveTravelSalesmenListToDB(result);
        return result;
    }

    public List<TravelSalesman> killWeakTravelers(List<TravelSalesman> list) {
        Collections.sort(list);
        return list.stream().limit(numberOfReproducers).collect(Collectors.toList());
    }

    public List<TravelSalesman> createNextGeneration(List<TravelSalesman> breeders) {
        List<TravelSalesman> offspring = new ArrayList<>();
        for (int i = 0; i < generationAbundance-numberOfReproducers; i++) {
            twoRandoms.createRandoms();
            TravelSalesman parent1 = breeders.get(twoRandoms.firstRandom);
            TravelSalesman parent2 = breeders.get(twoRandoms.secondRandom);
            TravelSalesman child = (TravelSalesman) crossingStrategy.cross(parent1, parent2);
            mutatingStrategy.mutate(child);
            child.setGenerationNumber(generationCounter)
                    .setId(individualCounter++);
            offspring.add(child);
        }
        saveTravelSalesmenListToDB(offspring);
        List<TravelSalesman> newGeneration = new ArrayList<>(breeders);
        generationCounter++;
        return newGeneration;
    }

    public void saveTravelSalesmenListToDB(List<TravelSalesman> travelSalesmanList){
        Transaction transaction = session.beginTransaction();
        travelSalesmanList.forEach(session::saveOrUpdate);
        transaction.commit();
    }

    private List<Gene> getIndividualGenome(List<City> genePool) {
        Collections.shuffle(genePool);
        return new ArrayList<>(genePool);
    }

    private static class TwoRandoms {
        private int firstRandom;
        private int secondRandom;

        public TwoRandoms() {
            createRandoms();
        }

        public void createRandoms() {
            Random random = new Random();
            int range = 20;
            firstRandom = random.nextInt(range);
            do {
                secondRandom = random.nextInt(range);
            } while (firstRandom == secondRandom);
        }
    }
}
