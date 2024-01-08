package de.clumsystuff.evolutionchamber.evolutions.evennumberdetection;

import de.clumsystuff.evolutionchamber.framework.data.Individual;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork implements Individual {

    private String id;
    private String networkId;
    private List<Neuron> inputLayer = new ArrayList<>();
    private List<Neuron> outputLayer = new ArrayList<>();

    @Override
    public Individual mutate() {
        return null;
    }

    @Override
    public Individual crossover(Individual individual) {
        return null;
    }

    public List<Double> evaluate() {

        return this.outputLayer.stream()
                .map(Neuron::getActivation)
                .toList();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNetworkId() {
        return networkId;
    }

    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }

    public List<Neuron> getInputLayer() {
        return inputLayer;
    }

    public NeuralNetwork setInputLayer(List<Neuron> inputLayer) {
        this.inputLayer = inputLayer;
        return this;
    }

    public List<Neuron> getOutputLayer() {
        return outputLayer;
    }

    public NeuralNetwork setOutputLayer(List<Neuron> outputLayer) {
        this.outputLayer = outputLayer;
        return this;
    }
}
