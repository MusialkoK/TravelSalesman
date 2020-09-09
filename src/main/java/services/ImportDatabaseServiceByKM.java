package services;

import model.City;
import model.CityDistance;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ImportDatabaseServiceByKM implements ImportService {
    BufferedReader reader;
    List<City> cities = new ArrayList<>();
    List<CityDistance> distances = new ArrayList<>();
    Long distanceCounter = 1L;
    Long cityCounter = 1L;

    public ImportDatabaseServiceByKM() {
        try {
            reader = new BufferedReader(new FileReader("distances.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void addCitiesFromFile() {

        String line;
        final String splitBy = ",";
        try {
            while ((line = reader.readLine()) != null) {
                String[] cityLine = line.split(splitBy);
                CityDistance cityDistance = new CityDistance()
                        .setOriginCity(getExistingOrNew(cityLine[0]))
                        .setDestinationCity(getExistingOrNew(cityLine[1]))
                        .setDistance(Double.parseDouble(cityLine[2]))
                        .setDistanceId(distanceCounter++);
                distances.add(cityDistance);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        addToDB();
    }

    private void addToDB() {
        CityService cityService = new CityService();
        cityService.addToDB(cities);
        CityDistanceService cityDistanceService = new CityDistanceService();
        cityDistanceService.putDistanceToDB(distances);
    }

    private City getExistingOrNew(String cityName) {
        if (!cities.isEmpty()) {
            List<String> citiesNames = cities.stream().map(City::getName).collect(Collectors.toList());
            if (citiesNames.contains(cityName)) {
                int cityIndex = citiesNames.indexOf(cityName);
                return cities.get(cityIndex);
            }
        }
        City result = new City(cityName);
        cities.add(result);
        return result;
    }


}
