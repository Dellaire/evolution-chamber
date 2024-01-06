package de.clumsystuff.evolutionchamber.framework.data;

public interface Individual {

    Individual mutate();

    Individual crossover(Individual individual);
}
