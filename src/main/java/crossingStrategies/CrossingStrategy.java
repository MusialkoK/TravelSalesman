package crossingStrategies;

import model.Mutable;

public interface CrossingStrategy {

    Mutable cross(Mutable parent1, Mutable parent2);
}
