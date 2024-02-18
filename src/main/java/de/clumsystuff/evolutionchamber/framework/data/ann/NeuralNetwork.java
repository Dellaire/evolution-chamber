package de.clumsystuff.evolutionchamber.framework.data.ann;

import de.clumsystuff.evolutionchamber.framework.data.core.Individual;

import java.util.*;

public class NeuralNetwork implements Individual {

    private String id;
    private List<Neuron> inputLayer = new ArrayList<>();
    private List<Neuron> outputLayer = new ArrayList<>();
    private List<Neuron> neurons = new ArrayList<>();
    private List<NeuralLink> neuralLinks = new ArrayList<>();

    protected NeuralNetwork() {
    }

    @Override
    public Individual mutate() {

        for (Neuron neuron : this.neurons) {
            neuron.mutate();
        }

        for (NeuralLink neuralLink : this.neuralLinks) {
            neuralLink.mutate();
        }

        return this;
    }

    @Override
    public Individual crossover(Individual individual) {

        NeuralNetwork otherNeuralNetwork = (NeuralNetwork) individual;
        if (this.neurons.size() != otherNeuralNetwork.getNeurons().size()) {
            throw new RuntimeException("Crossovers between neural networks are only allowed when the numbers of neurons are identical.");
        }
        if (this.neuralLinks.size() != otherNeuralNetwork.getNeuralLinks().size()) {
            throw new RuntimeException("Crossovers between neural networks are only allowed when the numbers of neural links are identical.");
        }

        Map<String, Neuron> newNeurons = new HashMap<>();
        List<Neuron> childInputLayer = new ArrayList<>();
        List<Neuron> childOutputLayer = new ArrayList<>();
        for (int i = 0; i < this.neurons.size(); i++) {

            Neuron childNeuron = this.neurons.get(i).crossover(otherNeuralNetwork.getNeurons().get(i));
            newNeurons.put(childNeuron.getId(), childNeuron);
            if (this.inputLayer.contains(this.neurons.get(i))) {
                childInputLayer.add(childNeuron);
            }
            if (this.outputLayer.contains(this.neurons.get(i))) {
                childOutputLayer.add(childNeuron);
            }
        }

        for (int i = 0; i < this.neuralLinks.size(); i++) {

            NeuralLink childNeuralLink = this.neuralLinks.get(i).crossover(otherNeuralNetwork.getNeuralLinks().get(i));
            Neuron sourceNeuron = newNeurons.get(childNeuralLink.getSourceNeuronId());
            Neuron targetNeuron = newNeurons.get(childNeuralLink.getTargetNeuronId());
            childNeuralLink.setNeuron(targetNeuron);
            sourceNeuron.linkToNeuron(targetNeuron, childNeuralLink.getTransmissionValue());
        }

        return new Builder()
                .setInputLayer(childInputLayer)
                .setOutputLayer(childOutputLayer)
                .build();
    }

    public void apply(List<Double> inputVector) {

        if (this.inputLayer.size() != inputVector.size()) {
            throw new RuntimeException("The input vector must have the same size as the input layer.");
        }

        for (int i = 0; i < inputVector.size(); i++) {
            this.inputLayer.get(i).setActivation(inputVector.get(i));
        }

        List<Neuron> allNeurons = this.getNeurons();
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

    public List<Neuron> getInputLayer() {
        return inputLayer;
    }

    public List<Neuron> getOutputLayer() {
        return outputLayer;
    }

    public List<Neuron> getNeurons() {
        return neurons;
    }

    public List<NeuralLink> getNeuralLinks() {
        return neuralLinks;
    }

    public static class Builder {

        private final NeuralNetwork neuralNetwork = new NeuralNetwork();

        public NeuralNetwork build() {

            this.neuralNetwork.neurons = this.getAllNeurons();
            this.neuralNetwork.neuralLinks = this.getAllNeuralLinks();

            return this.neuralNetwork;
        }

        private List<Neuron> getAllNeurons() {

            return this.neuralNetwork.inputLayer.stream()
                    .map(Neuron::getLinkedNeurons)
                    .flatMap(Collection::stream)
                    .distinct()
                    .sorted(Comparator.comparing(Neuron::getLayer).thenComparing(Neuron::getId))
                    .toList();
        }

        private List<NeuralLink> getAllNeuralLinks() {

            return this.neuralNetwork.inputLayer.stream()
                    .map(Neuron::getLinkedNeuralLinks)
                    .flatMap(Collection::stream)
                    .distinct()
                    .sorted(Comparator.comparing(NeuralLink::getSourceNeuronId).thenComparing(NeuralLink::getTargetNeuronId))
                    .toList();
        }

        public Builder setId(String id) {
            this.neuralNetwork.id = id;
            return this;
        }

        public Builder setInputLayer(List<Neuron> inputLayer) {
            this.neuralNetwork.inputLayer = inputLayer;
            return this;
        }

        public Builder setOutputLayer(List<Neuron> outputLayer) {
            this.neuralNetwork.outputLayer = outputLayer;
            return this;
        }
    }
}
