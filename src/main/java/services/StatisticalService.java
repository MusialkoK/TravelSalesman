package services;

import model.Mutable;
import model.TravelSalesman;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class StatisticalService implements StatisticalServiceInterface {

    private List<TravelSalesman> populationHistory;

    @Override
    public Mutable getBestIndividual() {
        this.populationHistory = TravelSalesmanService.getHistory();
        AtomicReference<TravelSalesman> minReference = new AtomicReference<>();
        List<TravelSalesman> bestTravelersList = new ArrayList<>();
        minReference.set(populationHistory.get(0));
        populationHistory.forEach(ts -> {
            if (ts.getFitnessValue() < minReference.get().getFitnessValue()){
                minReference.set(ts);
                bestTravelersList.clear();
                bestTravelersList.add(ts);
            }else if(ts.getFitnessValue() == minReference.get().getFitnessValue()){
                bestTravelersList.add(ts);
            }
        });
        return bestTravelersList.get(0);
    }

    @Override
    public Mutable getWorstIndividual() {
        return null;
    }

    @Override
    public double getMutationRatio() {
        return 0;
    }

    @Override
    public double getGenerationDiversityIndex() {
        return 0;
    }
}
