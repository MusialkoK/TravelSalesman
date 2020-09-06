package services;

import lombok.Getter;
import lombok.Setter;
import model.City;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CityService {

    @Setter
    private static Session session;
    @Getter
    @Setter
    private static List<City> fullCityList;
    @Getter
    @Setter
    private static List<City> operatingCityList;
    @Setter
    @Getter
    private static City startCity;

    public void addToDB(List<City> cities) {
        Transaction transaction = session.beginTransaction();
        cities.forEach(session::saveOrUpdate);

        transaction.commit();
    }

    public void setFullCityList() {
        String findQuery = "from cities";
        Query<City> query = session.createQuery(findQuery);
        fullCityList = query.getResultList();
    }
}
