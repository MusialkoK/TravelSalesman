import model.City;
import model.CityDistance;
import model.Mutable;
import model.TravelSalesman;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import services.CityDistanceService;
import services.CityService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class App {

    public static void main(String[] args) {

//        City city1 = new City("Warszawa");
//        City city2 = new City("Kraków")
//                .assignDistances(city1, 295);
//        City city3 = new City("Gdańsk")
//                .assignDistances(city1, 339)
//                .assignDistances(city2, 565);
//        City city4 = new City("Poznań")
//                .assignDistances(city1, 310)
//                .assignDistances(city2, 403)
//                .assignDistances(city3, 296);

//        List<City> genes = new ArrayList<>(Arrays.asList(city1, city2, city3, city4));

//        int n = 10;
//        List<Mutable> travelers = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            Collections.shuffle(genes);
//            travelers.add(new TravelSalesman(new ArrayList<>(genes)));
//        }


        final StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();

        try (SessionFactory sessionFactory = new MetadataSources(standardServiceRegistry).buildMetadata().buildSessionFactory()) {
            Session session = sessionFactory.openSession();
            CityDistanceService.setSession(session);
            CityService.setSession(session);

            CityDistanceService cityDistanceService = new CityDistanceService();
            CityService cityService = new CityService();

            City warszawa = cityService.getCityBy("Warszawa");
            City krakow = cityService.getCityBy("Kraków");
            CityDistance cityDistance = cityDistanceService.getDistanceFromDB(warszawa,krakow);
            System.out.println(cityDistance);

            System.out.println(warszawa);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
