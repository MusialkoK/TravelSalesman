package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "travelers")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class TravelSalesman implements Mutable, Comparable<TravelSalesman> {

    @Transient
    private Phenotype phenotype;

    @Id
    private long id;

    @Setter
    private int generationNumber;

    @ManyToMany
    private final List<TravelSalesman> parents = new ArrayList<>();

    @ManyToMany
    private List<City> genotype;

    private Double fitnessValue;

    boolean mutated = false;

    public TravelSalesman(List<Gene> genotype) {
        this.genotype = genotype.stream().map(g -> (City) g).collect(Collectors.toList());
        phenotype = new MinimumPhenotype().setGenotype(this.genotype);
    }

    @Override
    public void calculateFitnessValue() {
        fitnessValue = (double) phenotype.fitness();
    }

    @Override
    public Double getFitnessValue() {
        return fitnessValue;
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
                " Gen: " + generationNumber +
                " Route: " + genotype.toString() +
                " Length: " + fitnessValue +
                " Mutated: " + mutated +
                getParentsNames();
    }

    @Override
    public void gotMutated() {
        this.mutated = true;
    }

    private String getParentsNames() {
        if (parents.isEmpty()) {
            return " Parents ids: NONE";
        } else {
            return " Parents ids: " + parents.get(0).getId() + " & " + parents.get(1).getId();
        }
    }

    @Override
    public int compareTo(TravelSalesman o) {
        return this.fitnessValue.compareTo(o.fitnessValue);
    }

    public TravelSalesman setParents(Mutable parent1, Mutable parent2) {
        this.parents.add((TravelSalesman) parent1);
        this.parents.add((TravelSalesman) parent2);
        return this;
    }
}
