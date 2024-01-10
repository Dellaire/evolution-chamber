package de.clumsystuff.evolutionchamber.framework.data.ann;

import de.clumsystuff.evolutionchamber.framework.data.core.Individual;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class NeuralNetwork implements Individual {

    private String id;
    private String networkId;
    private List<Neuron> inputLayer = new ArrayList<>();
    private List<Neuron> outputLayer = new ArrayList<>();

    @Override
    public Individual mutate() {

        for (Neuron neuron : this.getAllNeurons()) {
            neuron.mutate();
        }

        return this;
    }

    @Override
    public Individual crossover(Individual individual) {

        NeuralNetwork otherNeuralNetwork = (NeuralNetwork) individual;
        List<Neuron> otherNeurons = otherNeuralNetwork.getAllNeurons();
        List<Neuron> neurons = this.getAllNeurons();

        if (neurons.size() != otherNeurons.size()) {
            throw new RuntimeException("Crossovers between neural networks are only allowed when the numbers of neurons are identical.");
        }

        List<Neuron> childInputLayer = new ArrayList<>();
        List<Neuron> childOutputLayer = new ArrayList<>();
        for (int i = 0; i < neurons.size(); i++) {

            Neuron childNeuron = neurons.get(i).crossover(otherNeurons.get(i));
            if (this.inputLayer.contains(neurons.get(i))) {
                childInputLayer.add(childNeuron);
            }
            if (this.outputLayer.contains(neurons.get(i))) {
                childOutputLayer.add(childNeuron);
            }
        }

        return new NeuralNetwork()
                .setInputLayer(childInputLayer)
                .setOutputLayer(childOutputLayer);
    }

    public void apply(List<Double> inputVector) {

        if (this.inputLayer.size() != inputVector.size()) {
            throw new RuntimeException("The input vector must have the same size as the input layer.");
        }

        for (int i = 0; i < inputVector.size(); i++) {
            this.inputLayer.get(i).setActivation(inputVector.get(i));
        }

        List<Neuron> allNeurons = this.getAllNeurons();
        for (Neuron neuron : allNeurons) {
            neuron.activate();
        }
    }

    public List<Neuron> getAllNeurons() {

        return this.inputLayer.stream()
                .map(Neuron::getLinkedNeurons)
                .flatMap(Collection::stream)
                .distinct()
                .sorted(Comparator.comparing(Neuron::getLayer))
                .toList();
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
