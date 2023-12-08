package de.clumsystuff.evolutionchamber.data;

public interface Individual {

    Double getFitness(FitnessFunction fitnessFunction);

    Individual mutate();

    Individual crossover(Individual individual);
}
