package services;

import model.City;
import model.CityDistance;

import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImportDatabaseService {

    List<City> listOfCities = new ArrayList<>();
    List<CityDistance> listOfDistances = new ArrayList<>();

    BufferedReader reader = new BufferedReader(new FileReader("distances.csv"));

    public void addCitiesFromFile() throws IOException {
        String line = "";
        String splitBy = ",";
        Long id = 1L;

        CityService cityService = new CityService();
        while ((line = reader.readLine()) != null) {
                String[] cityInfo = line.split(splitBy);
                City originCity = new City(cityInfo[0]);
                City destinationCity = new City(cityInfo[1]);
                double distance = Double.parseDouble(cityInfo[2]);


                    if (!listOfCities.contains(originCity)) {
                        listOfCities.add(originCity);
                    }
                    if (!listOfCities.contains(destinationCity)) {
                        listOfCities.add(destinationCity);
                    }

                CityDistance cityDistance = new CityDistance(originCity, destinationCity, distance, id);
                    listOfDistances.add(cityDistance);
                    id = id+1;


            }


        CityDistanceService cityDistanceService = new CityDistanceService();

        cityService.addToDB(listOfCities);
        cityDistanceService.putDistanceToDB(listOfDistances);



        }



    public ImportDatabaseService() throws FileNotFoundException {
    }
}
