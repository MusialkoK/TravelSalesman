import model.TravelSalesman;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import services.CityDistanceService;
import services.CityService;
import services.ConsoleService;
import services.TravelSalesmanService;

import java.util.Collections;
import java.util.List;


public class App {

    public static void main(String[] args) {

        final StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();

        try (SessionFactory sessionFactory = new MetadataSources(standardServiceRegistry).buildMetadata().buildSessionFactory()) {
            Session session = sessionFactory.openSession();

            CityService.setSession(session);
            CityDistanceService.setSession(session);

            CityService cityService = new CityService();
            CityDistanceService cityDistanceService = new CityDistanceService();
            TravelSalesmanService travelSalesmanService = new TravelSalesmanService();
            ConsoleService consoleService = new ConsoleService();


            cityService.setFullCityList();
            CityService.setOperatingCityList(consoleService.askForOperatingCities());
            CityService.setStartCity(consoleService.askForStartCity());

            cityDistanceService.getCityDistances();
            cityDistanceService.assignDistancesToCities();



            List<TravelSalesman> travelSalesmanList= travelSalesmanService.createFirstGeneration();
            Collections.sort(travelSalesmanList);
            consoleService.displayTravelersList(travelSalesmanList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
