package de.clumsystuff.evolutionchamber.evolutions.evennumberdetection;

import de.clumsystuff.evolutionchamber.framework.data.Individual;

import java.util.Map;

public class Neuron implements Individual {

    private Map<Neuron, Double> neuralLinks;
    private Double activation;
    private Double activationThreshold;

    @Override
    public Individual mutate() {
        return null;
    }

    @Override
    public Individual crossover(Individual individual) {
        return null;
    }
}
