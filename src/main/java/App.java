import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import services.CityDistanceService;
import services.CityService;
import services.ImportDatabaseService;
import services.TravelSalesmanService;


public class App {


    public static void main(String[] args) {
        TravelSalesmanService travelSalesmanService = new TravelSalesmanService();


        final StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();

        try (SessionFactory sessionFactory = new MetadataSources(standardServiceRegistry).buildMetadata().buildSessionFactory()) {
            Session session = sessionFactory.openSession();
            setUpServices(session);
//            makeImport();
            travelSalesmanService.makeAnalysis();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setUpServices(Session session) {
        CityDistanceService.setSession(session);
        CityService.setSession(session);
        TravelSalesmanService.setSession(session);
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