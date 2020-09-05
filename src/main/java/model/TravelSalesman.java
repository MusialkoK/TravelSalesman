package model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "travelers")
@Getter
@Setter
@Accessors(chain = true)
public class TravelSalesman implements Mutable, Comparable<TravelSalesman> {

    @Transient
    private Phenotype phenotype;

    @Id
    @GeneratedValue
    private long id;


    @Setter
    private int generationNumber;

    @ManyToMany
    private final List<TravelSalesman> parents = new ArrayList<>();

    @ManyToMany
    private List<City> genotype;

    private Double fitnessValue;

    public TravelSalesman(List<Gene> genotype) {
        this.genotype = genotype.stream().map(g -> (City) g).collect(Collectors.toList());
        phenotype = new MinimumPhenotype().setGenotype(this.genotype);
        fitnessValue = (double) phenotype.fitness();
    }

    @Override
    public int getGenomeLength() {
        return genotype.size();
    }

    @Override
    public Mutable setGenotype(List<Gene> genotype) {
        this.genotype = genotype.stream().map(g -> (City) g).collect(Collectors.toList());
        return this;
    }

    @Override
    public List<Gene> getGenotype() {
        return genotype.stream().map(g -> (Gene) g).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Id: " + id +
                " Route: " + genotype.toString() +
                " Length: " + fitnessValue;
    }

    @Override
    public int compareTo(TravelSalesman o) {
        return this.fitnessValue.compareTo(o.fitnessValue);
    }
}
