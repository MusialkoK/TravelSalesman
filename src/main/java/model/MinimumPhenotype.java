package model;

import lombok.Setter;
import lombok.experimental.Accessors;
import services.CityDistanceService;

import java.util.ArrayList;
import java.util.List;

@Accessors(chain = true)
public class MinimumPhenotype implements Phenotype {
    @Setter
    private List<City> genotype;

    @Override
    public Double fitness() {
        List<Double> distances = new ArrayList<>();
        CityDistanceService cityDistanceService = new CityDistanceService();
        for (int i = 0; i < genotype.size(); i++) {
            distances.add(cityDistanceService.getDistanceBetween(genotype.get(i), genotype.get((i + 1) % genotype.size())));
        }
        return distances.stream().mapToDouble(d->d).sum();
    }

}
