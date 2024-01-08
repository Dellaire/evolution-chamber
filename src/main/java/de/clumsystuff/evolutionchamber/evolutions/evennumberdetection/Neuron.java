package de.clumsystuff.evolutionchamber.evolutions.evennumberdetection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Neuron {

    private Map<Neuron, Double> neuralLinks = new HashMap<>();
    private Double activation;
    private Double activationThreshold;

    public List<Neuron> getLinkedNeurons() {

        List<Neuron> linkedNeurons = new ArrayList<>();
        linkedNeurons.add(this);
        this.neuralLinks.keySet().forEach(neuron -> linkedNeurons.addAll(neuron.getLinkedNeurons()));

        return linkedNeurons;
    }

    public Map<Neuron, Double> getNeuralLinks() {
        return neuralLinks;
    }

    public void setNeuralLinks(Map<Neuron, Double> neuralLinks) {
        this.neuralLinks = neuralLinks;
    }

    public Double getActivation() {
        return activation;
    }

    public void setActivation(Double activation) {
        this.activation = activation;
    }

    public Double getActivationThreshold() {
        return activationThreshold;
    }

    public void setActivationThreshold(Double activationThreshold) {
        this.activationThreshold = activationThreshold;
    }
}
