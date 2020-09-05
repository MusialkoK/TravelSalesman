package model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import services.CityDistanceService;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Accessors(chain = true)
@Setter
@Getter
@Entity(name = "cities")
@NoArgsConstructor
@ToString
public class City implements Gene {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Transient
    private Map<City, Double> distancesMap = new HashMap<>();

    public City(String name) {
        this.name = name;
    }

    public City assignDistances(City city, double distance) {
        this.distancesMap.put(city, distance);
        city.getDistancesMap().put(this, distance);
        return this;
    }
//    @PostLoad
//    private void loadDistancesMap(){
//        CityDistanceService cityDistanceService = new CityDistanceService();
//        distancesMap=cityDistanceService.distancesMapFrom(this);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(name, city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
