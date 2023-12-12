package de.clumsystuff.evolutionchamber.framework.data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Population {

    private final List<Individual> individuals = new ArrayList<>();
    private final FitnessFunction fitnessFunction;
    private final Comparator<Individual> comparator;

    public Population(List<Individual> individuals, FitnessFunction fitnessFunction) {

        this.fitnessFunction = fitnessFunction;
        this.comparator = Comparator.comparing(o -> o.getFitness(fitnessFunction));
        this.addIndividuals(individuals);
    }

    public Population addIndividuals(List<Individual> individuals) {

        this.individuals.addAll(individuals);
        this.individuals.sort(comparator.reversed());

        return this;
    }

    public Population removeWorstIndividuals(int numberOfIndividuals) {

        this.individuals.removeAll(this.getWorstIndividuals(numberOfIndividuals));

        return this;
    }

    public List<Individual> getBestIndividuals(int numberOfIndividuals) {

        return this.individuals.subList(0, numberOfIndividuals);
    }

    public Map<Individual, Double> getBestIndividualsWithFitness(int numberOfIndividuals) {

        List<Individual> bestIndividuals = this.getBestIndividuals(numberOfIndividuals);

        return bestIndividuals.stream()
                .collect(Collectors.toMap(individual -> individual, individual -> individual.getFitness(this.fitnessFunction)));
    }

    public List<Individual> getWorstIndividuals(int numberOfIndividuals) {

        return this.individuals.subList(this.individuals.size() - numberOfIndividuals - 1, this.individuals.size() - 1);
    }
}
