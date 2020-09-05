package services;

import lombok.Setter;
import org.hibernate.Session;
import model.City;
import model.CityDistance;
import org.hibernate.Transaction;

public class CityDistanceService {

    @Setter
    private Session session;

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

}
