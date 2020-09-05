package services;

import lombok.Setter;
import model.City;
import model.CityDistance;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CityDistanceService {

    @Setter
    private static Session session;

    private static List<CityDistance> distanceList = new ArrayList<>();

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

    public void putDistanceToDB(List<CityDistance> list){
        Transaction transaction = session.beginTransaction();
        list.forEach(session::saveOrUpdate);
        transaction.commit();
    }

    public Double getDistanceBetween(City origin, City destination) {
        return origin.getDistancesMap().get(destination);
    }

    public CityDistance getDistanceFromDB(City origin, City destination) {
        List<CityDistance> result = distanceList.stream()
                .filter(distance -> distance.getOriginCity() == origin && distance.getDestinationCity() == destination)
                .collect(Collectors.toList());
        return result.get(0);
    }

    public void assignDistancesToCities() {
        CityService.getOperatingCityList().forEach(c -> c.setDistancesMap(distancesMapFrom(c)));
    }

    public void getCityDistances() {
        String findQuery = "from distances d";
        Query<CityDistance> query = session.createQuery(findQuery);
        distanceList = query.getResultList();
    }

    private Map<City, Double> distancesMapFrom(City city) {
        List<CityDistance> mapEntry = distanceList.stream().filter(distance -> distance.getOriginCity() == city).collect(Collectors.toList());
        Map<City, Double> result = new HashMap<>();
        mapEntry.forEach(d -> result.put(d.getDestinationCity(), d.getDistance()));
        return result;
    }

}
