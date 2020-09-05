package services;

import lombok.Setter;
import org.hibernate.Session;
import model.City;
import model.CityDistance;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CityDistanceService {

    @Setter
    private static Session session;

    public void putDistanceToDB(City origin, City destination, double distance) {

        CityDistance cityDistance = new CityDistance()
                .setOriginCity(origin)
                .setDestinationCity(destination)
                .setDistance(distance);

        CityDistance cityDistance2 = new CityDistance()
                .setOriginCity(destination)
                .setDestinationCity(origin)
                .setDistance(distance);

        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(cityDistance);
        session.saveOrUpdate(cityDistance2);
        transaction.commit();
    }

    public void putDistanceToDB (List<CityDistance> listOfDistances ){
        Transaction transaction = session.beginTransaction();
        for (CityDistance cd:listOfDistances
             ) {
            session.saveOrUpdate(cd);
        }
        transaction.commit();


    }


    public Double getDistanceBetween(City origin, City destination){
        return origin.getDistances().get(destination);
    }

    public CityDistance getDistanceFromDB(City origin, City destination){
        String findQuery = "from distances d where d.originCity = :cityOrigin and d.destinationCity = :cityDestination";
        Query<CityDistance> query = session.createQuery(findQuery);
        query.setParameter("cityOrigin", origin);
        query.setParameter("cityDestination", destination);
        return query.getSingleResult();
    }

    public Map<City, Double> distancesMapFrom(City city) {
        String findQuery = "from distances d where d.originCity = :city";
        Map<City,Double> result = new HashMap<>();
        Query<CityDistance> query = session.createQuery(findQuery);
        query.setParameter("city", city);

        List<CityDistance> distanceList = query.getResultList();
        distanceList.forEach(d-> result.put(d.getDestinationCity(),d.getDistance()));
        return result;
    }


}
