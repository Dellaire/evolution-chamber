package de.clumsystuff.evolutionchamber.framework.data.core;

public interface Individual {

    Individual mutate();

    Individual crossover(Individual individual);
}
