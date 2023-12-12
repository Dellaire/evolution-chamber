package de.clumsystuff.evolutionchamber.framework.data;

public interface Individual {

    Double getFitness(FitnessFunction fitnessFunction);

    Individual mutate();

    Individual crossover(Individual individual);
}
