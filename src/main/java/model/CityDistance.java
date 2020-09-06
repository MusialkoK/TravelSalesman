package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "distances")
@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CityDistance {

    @Id
    private Long distanceId;

    @OneToOne(cascade = CascadeType.ALL)
    private City originCity;

    @OneToOne(cascade = CascadeType.ALL)
    private City destinationCity;

    private double distance;

    public CityDistance(City originCity, City destinationCity, double distance, Long id) {
        this.distanceId = id;
        this.originCity = originCity;
        this.destinationCity = destinationCity;
        this.distance = distance;
    }
}
