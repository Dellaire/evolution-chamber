package de.clumsystuff.evolutionchamber.evolutions.evennumberdetection;

import de.clumsystuff.evolutionchamber.framework.data.Individual;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

@Document
@TypeAlias("NeuralNetwork")
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

    public void apply(List<Double> inputVector) {

        if (this.inputLayer.size() != inputVector.size()) {
            throw new RuntimeException("The input vector must have the same size as the input layer.");
        }

        for (int i = 0; i < inputVector.size(); i++) {
            this.inputLayer.get(i).setActivation(inputVector.get(i));
        }

        List<Neuron> allNeurons = this.inputLayer.stream()
                .map(Neuron::getLinkedNeurons)
                .flatMap(Collection::stream)
                .distinct()
                .sorted(Comparator.comparing(Neuron::getLayer))
                .toList();

        for (Neuron neuron : allNeurons) {
            neuron.activate();
        }
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
