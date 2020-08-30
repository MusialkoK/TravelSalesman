package model;

import java.util.List;

public interface Mutable {

    List<Gene> getGenotype();

    int getGenomeLength();
}
