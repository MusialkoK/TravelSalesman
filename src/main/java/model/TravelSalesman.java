package model;





import java.util.ArrayList;
import java.util.List;



public class TravelSalesman implements Mutable{

    List<Gene> genotype = new ArrayList<>();

    @Override
    public Mutable setGenotype(List<Gene> genotype) {
        this.genotype=genotype;
        return this;
    }

    @Override
    public List<Gene> getGenotype() {
        return genotype;
    }

    @Override
    public int getGenomeLength() {
        return genotype.size();
    }
}
