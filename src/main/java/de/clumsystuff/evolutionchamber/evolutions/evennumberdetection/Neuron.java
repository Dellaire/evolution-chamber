package de.clumsystuff.evolutionchamber.evolutions.evennumberdetection;

import java.util.ArrayList;
import java.util.List;

public class Neuron {

    private Double activation;
    private Double activationThreshold;
    private List<NeuralLink> neuralLinks = new ArrayList<>();

    /*public List<Neuron> getLinkedNeurons() {

        List<Neuron> linkedNeurons = new ArrayList<>();
        linkedNeurons.add(this);
        this.neuralLinks.keySet().forEach(neuron -> linkedNeurons.addAll(neuron.getLinkedNeurons()));

        return linkedNeurons;
    }*/

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

    public List<NeuralLink> getNeuralLinks() {
        return neuralLinks;
    }

    public void setNeuralLinks(List<NeuralLink> neuralLinks) {
        this.neuralLinks = neuralLinks;
    }
}
