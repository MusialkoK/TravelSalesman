package model;




import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class City implements Gene {
    Map<City,Double> distances = new HashMap<>();
    String name;

    @Override
    public String toString() {
        return name;
    }

    public City setName(String name) {
        this.name=name;
        return this;
    }

    public String getName() {
        return name;
    }

    public double distance(City city){
        return distances.get(city);
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
}
