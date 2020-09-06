package services;


import model.City;
import model.TravelSalesman;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ConsoleService {

    private final String CITY_LIST_FORMAT = "%d: %s\n";

    public List<City> askForOperatingCities() {
        List<City> genes = CityService.getFullCityList();
        Scanner sc = new Scanner(System.in);
        displayCityList(genes);
        System.out.println("A: All cities");
        System.out.println("Choose cities through which to travel [x,x,x ...]:");

        String line = sc.nextLine();
        if(line.equals("A")) return genes;

        List<Long> integerList = Arrays
                .stream(line.split(","))
                .map(Long::valueOf).collect(Collectors.toList());
        return genes.stream().filter(c -> integerList.contains(c.getId())).collect(Collectors.toList());
    }

    public City askForStartCity() {
        List<City> list = CityService.getOperatingCityList();
        Scanner sc = new Scanner(System.in);
        displayCityList(list);
        System.out.println("Choose start city [x]:");
        Long intCity = sc.nextLong();
        for (City c:list) {
            if(c.getId().equals(intCity)){
                return c;
            }
        }
        return null;
    }

    public void displayCityList(List<City> cityList) {
        AtomicInteger integer = new AtomicInteger(1);
        cityList.forEach(c -> System.out.printf(CITY_LIST_FORMAT, integer.getAndIncrement(), c.getName()));
    }

    public void displayTravelersList(List<TravelSalesman> travelSalesmanList) {
        travelSalesmanList.forEach(System.out::println);
        System.out.println();
    }

}
