package crossingStrategies;

import model.City;
import model.Gene;
import model.Mutable;
import model.TravelSalesman;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OX2TwinStrategyTest {

    @Test
    void createOffspring() {

        //given
        List<String> cityStrings = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8");
        List<City> cities = cityStrings.stream().map(City::new).collect(Collectors.toList());
        List<Gene> parent1Genotype = new ArrayList<>();
        parent1Genotype.add(cities.get(2));
        parent1Genotype.add(cities.get(3));
        parent1Genotype.add(cities.get(7));
        parent1Genotype.add(cities.get(1));
        parent1Genotype.add(cities.get(6));
        parent1Genotype.add(cities.get(0));
        parent1Genotype.add(cities.get(5));
        parent1Genotype.add(cities.get(4));
        List<Gene> parent2Genotype = new ArrayList<>();
        parent2Genotype.add(cities.get(3));
        parent2Genotype.add(cities.get(1));
        parent2Genotype.add(cities.get(4));
        parent2Genotype.add(cities.get(0));
        parent2Genotype.add(cities.get(5));
        parent2Genotype.add(cities.get(7));
        parent2Genotype.add(cities.get(2));
        parent2Genotype.add(cities.get(6));
        List<Gene> offspring1Genotype = new ArrayList<>();
        offspring1Genotype.add(cities.get(3));
        offspring1Genotype.add(cities.get(7));
        offspring1Genotype.add(cities.get(5));
        offspring1Genotype.add(cities.get(1));
        offspring1Genotype.add(cities.get(4));
        offspring1Genotype.add(cities.get(2));
        offspring1Genotype.add(cities.get(0));
        offspring1Genotype.add(cities.get(6));
        List<Gene> offspring2Genotype = new ArrayList<>();
        offspring2Genotype.add(cities.get(0));
        offspring2Genotype.add(cities.get(6));
        offspring2Genotype.add(cities.get(3));
        offspring2Genotype.add(cities.get(7));
        offspring2Genotype.add(cities.get(5));
        offspring2Genotype.add(cities.get(1));
        offspring2Genotype.add(cities.get(4));
        offspring2Genotype.add(cities.get(2));



        Mutable parent1 = new TravelSalesman();
        parent1.setGenotype(parent1Genotype);
        Mutable parent2 = new TravelSalesman();
        parent2.setGenotype(parent2Genotype);

        Mutable child1 = new TravelSalesman();
        child1.setGenotype(offspring1Genotype);
        Mutable child2 = new TravelSalesman();
        child2.setGenotype(offspring2Genotype);



        OX2TwinStrategy ox2TwinStrategy = new OX2TwinStrategy();

        //when
        List<Mutable> list = ox2TwinStrategy.createOffspring(parent1, parent2);
        Mutable child1Actual = list.get(0);
        Mutable child2Actual = list.get(1);

        //then
        assertEquals(child1,child1Actual);
        assertEquals(child2,child2Actual);

    }
}