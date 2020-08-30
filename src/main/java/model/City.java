package model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class City {
    private  String cityName;
    private int id;

    private Map<City, Integer > distances = new LinkedHashMap<>();




    public Integer getDistanceTo(int index) {
        return getDistances().get(index);
    }

    public Map<City, Integer> getDistances() {
        return distances;
    }

    public void setDistances(City city, Integer distance) {
        distances.put(city, distance);

    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public City(String cityName, int id) {
        this.cityName = cityName;
        this.id = id;

    }

    public static void main(String[] args) {
        City c1 = new City("koko", 9);

    }


}
