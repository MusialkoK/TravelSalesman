package mutatingStrategies;

import model.Gene;
import model.Mutable;

import java.util.List;

public interface MutatingStrategy {

    Mutable mutate(Mutable obj);
}
