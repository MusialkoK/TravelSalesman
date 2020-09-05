package model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Accessors(chain = true)
@Setter
@Getter
@Entity(name = "cities")
@NoArgsConstructor
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

    @Override
    public String toString() {
        return name;
    }
}
