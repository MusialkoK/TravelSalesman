package services;

import lombok.Setter;
import model.City;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Arrays;
import java.util.List;

public class CityService {

    @Setter
    private Session session;

    public void addToDB(City city){
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(city);
        transaction.commit();
    }

    public void addToDB(List<City> cities){
        Transaction transaction = session.beginTransaction();
        cities.forEach(session::saveOrUpdate);
        transaction.commit();
    }

    public void addToDB(City[]...cities){
        Transaction transaction = session.beginTransaction();
        Arrays.stream(cities).forEach(session::saveOrUpdate);
        transaction.commit();
    }


}
