package de.clumsystuff.evolutionchamber.framework.data.ann;

import de.clumsystuff.evolutionchamber.evolutions.picalculation.RandomHelper;

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

    public Neuron(String id) {
        this.id = id;
    }

    public Neuron mutate() {

        this.setActivationThreshold(this.activationThreshold + RandomHelper.RANDOM.nextDouble() - 0.5);

        return this;
    }

    public Neuron crossover(Neuron neuron) {

        if (this.neuralLinks.size() != neuron.getNeuralLinks().size()) {
            throw new RuntimeException("Crossovers between neurons are only allowed when the numbers of neural links are identical.");
        }

        return new Neuron(this.id)
                .setActivationThreshold((this.activationThreshold + neuron.getActivationThreshold()) / 2)
                .setLayer(this.layer);
    }

    public Set<Neuron> getLinkedNeurons() {

        Set<Neuron> linkedNeurons = new HashSet<>();
        linkedNeurons.add(this);

        this.neuralLinks.stream()
                .map(NeuralLink::getNeuron)
                .forEach(neuron -> linkedNeurons.addAll(neuron.getLinkedNeurons()));

        return linkedNeurons;
    }

    public Set<NeuralLink> getLinkedNeuralLinks() {

        Set<NeuralLink> linkedNeuralLinks = new HashSet<>(this.neuralLinks);

        this.neuralLinks.stream()
                .map(NeuralLink::getNeuron)
                .forEach(neuron -> linkedNeuralLinks.addAll(neuron.getLinkedNeuralLinks()));

        return linkedNeuralLinks;
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

    public Neuron setActivationThreshold(Double activationThreshold) {
        this.activationThreshold = activationThreshold;
        return this;
    }

    public Integer getLayer() {
        return layer;
    }

    public Neuron setLayer(Integer layer) {
        this.layer = layer;
        return this;
    }

    public List<NeuralLink> getNeuralLinks() {
        return neuralLinks;
    }

    public Neuron linkToNeuron(Neuron neuron, Double transmissionValue) {

        NeuralLink neuralLink = new NeuralLink()
                .setTransmissionValue(transmissionValue)
                .setNeuron(neuron)
                .setSourceNeuronId(this.id)
                .setTargetNeuronId(neuron.getId());

        neuron.setLayer(this.layer + 1);

        this.neuralLinks.add(neuralLink);

        return this;
    }
}
