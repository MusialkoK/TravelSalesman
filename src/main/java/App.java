import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import services.*;


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
            ImportService importDatabaseService = new ImportDatabaseServiceByKM();
            importDatabaseService.addCitiesFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}