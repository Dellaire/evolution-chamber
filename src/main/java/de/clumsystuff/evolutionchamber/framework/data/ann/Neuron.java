package de.clumsystuff.evolutionchamber.framework.data.ann;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Neuron {

    private String id;
    private Double activation = 0.0;
    private Double activationThreshold = 0.0;
    private Integer layer = 0;
    private List<NeuralLink> neuralLinks = new ArrayList<>();

    public Set<Neuron> getLinkedNeurons() {

        Set<Neuron> linkedNeurons = new HashSet<>();
        linkedNeurons.add(this);

        this.neuralLinks.stream()
                .map(NeuralLink::getNeuron)
                .forEach(neuron -> linkedNeurons.addAll(neuron.getLinkedNeurons()));

        return linkedNeurons;
    }

    public void activate() {

        if (this.activation >= this.activationThreshold) {
            for (NeuralLink neuralLink : this.neuralLinks) {
                neuralLink.getNeuron().addActivation(neuralLink.getTransmissionValue());
            }
        }
    }

    public String getId() {
        return id;
    }

    public Neuron setId(String id) {
        this.id = id;
        return this;
    }

    public Double getActivation() {
        return activation;
    }

    public void addActivation(Double input) {
        this.activation += input;
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

    public Integer getLayer() {
        return layer;
    }

    public void setLayer(Integer layer) {
        this.layer = layer;
    }

    public List<NeuralLink> getNeuralLinks() {
        return neuralLinks;
    }

    public void setNeuralLinks(List<NeuralLink> neuralLinks) {

        for (NeuralLink neuralLink : neuralLinks) {
            neuralLink.getNeuron().setLayer(this.layer + 1);
        }
        this.neuralLinks = neuralLinks;
    }
}
