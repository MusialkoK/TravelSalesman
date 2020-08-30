package service;

import model.City;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class CityService {

    List <City> listOfCities = new ArrayList<>();
    Random random = new Random();

   public void addCities(List <String> citiesNames){
       for (int i =0; i<citiesNames.size(); i++){
        City city = new City(citiesNames.get(i), i+1);
        listOfCities.add(city);
       }

       for (City city:listOfCities) {
           for(int n = 0; n<listOfCities.size(); n++){
               if (n< listOfCities.indexOf(city)){
                   Integer dist = listOfCities.get(citiesNames.indexOf(city)).getDistanceTo(n);
                   city.setDistances(listOfCities.get(n), dist);

               }else {
                   int randomNumber = 50 + (random.nextInt() % 451);
                   city.setDistances(listOfCities.get(n), Integer.valueOf(randomNumber));
               }
           }
       }

   }


}
