package de.clumsystuff.evolutionchamber.evolutions.evennumberdetection;

import de.clumsystuff.evolutionchamber.framework.data.FitnessFunction;
import de.clumsystuff.evolutionchamber.framework.data.Individual;

import java.util.List;

public class NeuralNetwork implements Individual {

    private List<Neuron> inputLayer;

    @Override
    public Double getFitness(FitnessFunction fitnessFunction) {
        return null;
    }

    @Override
    public Individual mutate() {
        return null;
    }

    @Override
    public Individual crossover(Individual individual) {
        return null;
    }
}