package parentSelectionStrategies;

import model.Mutable;

import java.util.List;

public interface ParentSelectingStrategy {

    List<Mutable> getBreeders();
}
