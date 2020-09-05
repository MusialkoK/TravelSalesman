package services;


import model.City;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ConsoleService {

    private final String CITY_LIST_FORMAT = "%d: %s\n";

    public List<City> askForGenes(List<City> genes) {
        Scanner sc = new Scanner(System.in);
        displayCityList(genes);
        System.out.println("Choose cities through which to travel [x,x,x ...]:");

        List<Long> integerList = Arrays
                .stream(sc.nextLine().split(","))
                .map(Long::valueOf).collect(Collectors.toList());

        return genes.stream().filter(c->integerList.contains(c.getId())).collect(Collectors.toList());
    }

    public City askForStartCity(List<City> list){
        Scanner sc = new Scanner(System.in);
        displayCityList(list);
        System.out.println("Choose start city [x]:");
        Long intCity = sc.nextLong();

        return list.stream().findFirst().filter(c-> c.getId().equals(intCity)).get();

    }

    private void displayCityList(List<City> cityList) {
        AtomicInteger integer = new AtomicInteger(1);
        cityList.forEach(c -> System.out.printf(CITY_LIST_FORMAT, integer.getAndIncrement(), c.getName()));
    }


}
