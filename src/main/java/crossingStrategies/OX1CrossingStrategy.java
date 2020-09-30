package crossingStrategies;

import model.Gene;
import model.Mutable;
import model.Randoms;
import model.TravelSalesman;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OX1CrossingStrategy extends AbstractCrossingStrategy {
    private Gene[] childGenotypeArray;
    private Set<Gene> childGenotypeContent = new HashSet<>();
    private Mutable[] geneOriginArray;

    @Override
    public Mutable cross(Mutable parent1, Mutable parent2) {
        setParentsGenotype(parent1, parent2);
        childGenotypeArray = new Gene[parent1Genotype.size()];
        geneOriginArray = new Mutable[parent1Genotype.size()];
        Randoms randoms = new Randoms(parent1Genotype.size() - 1, 3, 1);
        int[] split = getSplitPoints(randoms);
        Arrays.sort(split);

        for (int i = 0; i <= split[1] - split[0]; i++) {
            writeGene(parent1, split[0] + i);
        }

        int parentIterator = assertGenotypeBoundaries(split[1] + 1);
        int childIterator = parentIterator;
        try {
            do {
                if (parentIterator == 0) {
                    writeGene(parent2,0);
                    parentIterator++;
                    continue;
                }
                if (childIterator == 0) {
                    childIterator++;
                    continue;
                }
                if (!childGenotypeContent.contains(parent2Genotype.get(parentIterator))) {
                    writeGene(parent2, parentIterator, childIterator);
                    childIterator = assertGenotypeBoundaries(++childIterator);
                }
                parentIterator = assertGenotypeBoundaries(++parentIterator);
            } while (childIterator != split[0]);

            TravelSalesman result = new TravelSalesman(List.of(childGenotypeArray))
                    .setParents(parent1, parent2)
                    .setGeneOrigin(List.of(geneOriginArray).stream().map(m->(TravelSalesman) m).collect(Collectors.toList()));

            Arrays.fill(childGenotypeArray, null);
            Arrays.fill(geneOriginArray, null);
            childGenotypeContent.clear();

            return result;

        } catch (NullPointerException e) {
            System.out.println(Arrays.toString(split));
            System.out.println(parent1);
            System.out.println(parent2);
            System.out.println(Arrays.toString(childGenotypeArray));
        }
        return null;
    }

    private void writeGene(Mutable parent, int geneIndex) {
        Gene gene = parent1Genotype.get(geneIndex);
        childGenotypeArray[geneIndex] = gene;
        geneOriginArray[geneIndex] = parent;
        childGenotypeContent.add(gene);
    }

    private void writeGene(Mutable parent, int parentGeneIndex, int childGeneIndex) {
        Gene gene = parent2Genotype.get(parentGeneIndex);
        childGenotypeArray[childGeneIndex] = gene;
        geneOriginArray[childGeneIndex] = parent;
        childGenotypeContent.add(gene);
    }

    private int assertGenotypeBoundaries(int value) {
        return value % parent1Genotype.size();
    }

    private int[] getSplitPoints(Randoms randoms){
        int[] draw = randoms.getArray();
        if(Math.abs(draw[0]-draw[1])==randoms.getRange()- randoms.getStartValue()){
            return new int[]{draw[0], draw[2]};
        }else{
            return new int[]{draw[0], draw[1]};
        }
    }
}
