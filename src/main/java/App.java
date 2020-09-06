import crossingStrategies.TakeHalfFillRestStrategy;
import model.City;
import model.TravelSalesman;
import mutatingStrategies.SwapMutateStrategy;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import services.*;

import java.util.List;


public class App {


    public static void main(String[] args) {

        CityService cityService = new CityService();
        CityDistanceService cityDistanceService = new CityDistanceService();
        ConsoleService consoleService = new ConsoleService();
        TravelSalesmanService travelSalesmanService = new TravelSalesmanService();


        final StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();

        try (SessionFactory sessionFactory = new MetadataSources(standardServiceRegistry).buildMetadata().buildSessionFactory()) {
            Session session = sessionFactory.openSession();
            setUpServices(session);
//            makeImport();

            cityService.setFullCityList();
            CityService.setOperatingCityList(consoleService.askForOperatingCities());
            CityService.setStartCity(consoleService.askForStartCity());
            cityDistanceService.getCityDistances();
            cityDistanceService.assignDistancesToCities();
            travelSalesmanService.setMutatingStrategy(new SwapMutateStrategy());

            List<TravelSalesman> currentGeneration = travelSalesmanService.createFirstGeneration();

            travelSalesmanService.setCrossingStrategy(new TakeHalfFillRestStrategy(currentGeneration.get(0)));

            consoleService.displayTravelersList(currentGeneration);
            currentGeneration=travelSalesmanService.killWeakTravelers(currentGeneration);
            consoleService.displayTravelersList(currentGeneration);
            currentGeneration=travelSalesmanService.createNextGeneration(currentGeneration);
            consoleService.displayTravelersList(currentGeneration);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setUpServices(Session session) {
        CityDistanceService.setSession(session);
        CityService.setSession(session);
    }

    public static void makeImport() {

        try {
            ImportDatabaseService importDatabaseService = new ImportDatabaseService();
            importDatabaseService.addCitiesFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}