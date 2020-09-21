package crossingStrategies;

import model.City;
import model.Gene;
import model.TravelSalesman;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

class TakeHalfFillRestStrategyTest {

    @Test
    void cross() {
        //given
        City cityA = new City().setName("A");
        City cityB = new City().setName("B");
        City cityC = new City().setName("C");
        City cityD = new City().setName("D");
        List<Gene> genome1 = Arrays.asList(cityA, cityB, cityC, cityD);
        List<Gene> genome2 = Arrays.asList(cityD, cityC, cityB, cityA);
        List<Gene> genome3 = Arrays.asList(cityA, cityB, cityD, cityC);
        TravelSalesman travelSalesman1 = (TravelSalesman) new TravelSalesman().setGenotype(genome1);
        TravelSalesman travelSalesman2 = (TravelSalesman) new TravelSalesman().setGenotype(genome2);
        TakeHalfFillRestStrategy takeHalfFillRestStrategy = new TakeHalfFillRestStrategy();

        //when
        TravelSalesman travelSalesman3 = takeHalfFillRestStrategy.cross(travelSalesman1,travelSalesman2);

        //then
        assertEquals(travelSalesman3.getGenotype(),genome3);


    }
}