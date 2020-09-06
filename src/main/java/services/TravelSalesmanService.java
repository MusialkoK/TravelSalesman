package services;

import crossingStrategies.CrossingStrategy;
import crossingStrategies.TakeHalfFillRestStrategy;
import lombok.Getter;
import lombok.Setter;
import model.City;
import model.Gene;
import model.TravelSalesman;
import model.TwoRandoms;
import mutatingStrategies.MutatingStrategy;
import mutatingStrategies.SwapMutateStrategy;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TravelSalesmanService {
    @Setter
    private static Session session;

    @Getter
    @Setter
    private static int generationAbundance = 10000;
    @Getter
    @Setter
    private static int numberOfReproducers = 100;
    @Getter
    @Setter
    private static int numberOfGenerations = 100;
    @Getter
    @Setter
    private static double mutatingChance = 0.2;
    @Setter
    private CrossingStrategy crossingStrategy;
    @Setter
    private MutatingStrategy mutatingStrategy;

    private int generationCounter = 0;
    private int individualCounter = 0;

    @Getter
    @Setter
    private List<TravelSalesman> currentGeneration;
    private boolean addToDB=false;
    private boolean displayIndividual = true;


    public List<TravelSalesman> createFirstGeneration() {
        List<TravelSalesman> result = new ArrayList<>();
        for (int i = 0; i < generationAbundance; i++) {
            result.add(new TravelSalesman(getIndividualGenome(CityService.getOperatingCityList(),CityService.getStartCity()))
                    .setGenerationNumber(generationCounter)
                    .setId(individualCounter++));
        }
        saveTravelSalesmenListToDB(result);
        return result;
    }

    public List<TravelSalesman> killWeakTravelers(List<TravelSalesman> list) {
        Collections.sort(list);
        return list.stream().limit(numberOfReproducers).collect(Collectors.toList());
    }

    public List<TravelSalesman> createNextGeneration(List<TravelSalesman> breeders) {
        TwoRandoms twoRandoms = new TwoRandoms(numberOfReproducers,true);
        generationCounter++;
        List<TravelSalesman> offspring = new ArrayList<>();
        for (int i = 0; i < generationAbundance-numberOfReproducers; i++) {
            TravelSalesman parent1 = breeders.get(twoRandoms.getFirstRandom());
            TravelSalesman parent2 = breeders.get(twoRandoms.getSecondRandom());
            twoRandoms.reset();
            TravelSalesman child = (TravelSalesman) crossingStrategy.cross(parent1, parent2);
            mutatingStrategy.mutate(child);
            child.setGenerationNumber(generationCounter)
                    .setId(individualCounter++);
            offspring.add(child);
        }
        saveTravelSalesmenListToDB(offspring);
        List<TravelSalesman> newGeneration = new ArrayList<>(breeders);
        newGeneration.addAll(offspring);
        return newGeneration;
    }

    public void saveTravelSalesmenListToDB(List<TravelSalesman> travelSalesmanList){
        if(addToDB){
            Transaction transaction = session.beginTransaction();
            travelSalesmanList.forEach(session::saveOrUpdate);
            transaction.commit();
        }
    }

    private List<Gene> getIndividualGenome(List<City> genePool, City startCity) {
        Collections.shuffle(genePool);
        genePool.remove(startCity);
        List<Gene> result = new ArrayList<>();
        result.add(startCity);
        result.addAll(genePool);
        return result;
    }

    public void makeAnalysis(){
        CityService cityService = new CityService();
        CityDistanceService cityDistanceService = new CityDistanceService();
        ConsoleService consoleService = new ConsoleService();

        cityService.setFullCityList();
        CityService.setOperatingCityList(consoleService.askForOperatingCities());
        CityService.setStartCity(consoleService.askForStartCity());
        cityDistanceService.getCityDistances();
        cityDistanceService.assignDistancesToCities();
        setMutatingStrategy(new SwapMutateStrategy());

        currentGeneration = createFirstGeneration();

        setCrossingStrategy(new TakeHalfFillRestStrategy(currentGeneration.get(0)));
        consoleService.generationCreatedMsg(generationCounter, getBestFitness(currentGeneration));
        if(displayIndividual) consoleService.displayTravelersList(currentGeneration);

        for (int i = 0; i < numberOfGenerations-1; i++) {
            currentGeneration=killWeakTravelers(currentGeneration);
            currentGeneration=createNextGeneration(currentGeneration);
            consoleService.generationCreatedMsg(generationCounter, getBestFitness(currentGeneration));
            if(displayIndividual) consoleService.displayTravelersList(currentGeneration);
        }
    }

    private double getBestFitness(List<TravelSalesman> list){
        return list.stream().mapToDouble(TravelSalesman::getFitnessValue).min().orElse(-1.0);
    }

}
