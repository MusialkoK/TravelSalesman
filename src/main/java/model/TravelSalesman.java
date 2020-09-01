package model;

import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "travelers")
public class TravelSalesman implements Mutable{

    @Id
    @GeneratedValue
    private long id;

    @Column
    @Setter
    private int generationNumber;

    @ManyToMany
    private final List<Mutable> parents = new ArrayList<>();

    @ManyToMany
    private List<Gene> genotype = new ArrayList<>();

    public TravelSalesman(List<Gene> genotype) {
        this.genotype = genotype;
    }

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
