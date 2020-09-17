package parentSelectionStrategies;

import model.Mutable;
import model.TravelSalesman;
import services.TravelSalesmanService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RouletteWheelSelection implements ParentSelectingStrategy {

    List<TravelSalesman> currentGeneration;
    List<BigDecimal> reverseFitness = new ArrayList<>();
    BigDecimal reverseFitnessSum = new BigDecimal(BigInteger.ZERO);
    List<BigDecimal> distributionFunction = new ArrayList<>();
    List<TravelSalesman> breeders = new ArrayList<>();

    @Override
    public List<Mutable> getBreeders() {
        setCurrentGeneration();
        setReverseFitnessSum();
        setBreedProbability();
        setBreeders();
        return breeders.stream().map(ts -> (Mutable) ts).collect(Collectors.toList());
    }

    private void setBreeders() {
        for (int i = 0; i < TravelSalesmanService.getNumberOfReproducers(); i++) {
            breeders.add(getSingleParent());
        }
    }

    private TravelSalesman getSingleParent() {
        Random random = new Random();
        BigDecimal value = BigDecimal.valueOf(random.nextDouble());
        int index = 0;
        for (int i = 1; i < distributionFunction.size(); i++) {
            if (value.compareTo(distributionFunction.get(i)) < 0) {
                index = i;
                break;
            }
        }
        return currentGeneration.get(index);
    }

    private void setBreedProbability() {
        BigDecimal probabilitySum = new BigDecimal(BigInteger.ZERO);
        BigDecimal probability;
        for (int i = 0; i < currentGeneration.size(); i++) {
            probability = reverseFitness.get(i).divide(reverseFitnessSum,50, RoundingMode.HALF_DOWN);
            currentGeneration.get(i).setBreedProbability(probability);
            distributionFunction.add(probability.add(probabilitySum));
            probabilitySum = probabilitySum.add(probability);
        }
    }

    private void setReverseFitnessSum() {
        currentGeneration.forEach(ts -> reverseFitness.add(reverse(ts.getFitnessValue())));
        reverseFitnessSum = reverseFitness.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void setCurrentGeneration() {
        this.currentGeneration = TravelSalesmanService.getCurrentGeneration();
    }

    private BigDecimal reverse(double value) {
        return BigDecimal.valueOf((Math.pow(value, -1)));
    }

}
