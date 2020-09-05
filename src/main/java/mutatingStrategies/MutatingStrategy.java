package mutatingStrategies;

import model.Mutable;

public interface MutatingStrategy {

    Mutable mutate(Mutable obj);
}
