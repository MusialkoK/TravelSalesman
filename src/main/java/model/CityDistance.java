package model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity(name = "distances")
@Accessors(chain = true)
@Getter
@Setter
public class CityDistance {

    @Id
    @GeneratedValue
    private Long distanceId;

    @OneToOne
    private City originCity;

    @OneToOne
    private City destinationCity;

    private double distance;
}
