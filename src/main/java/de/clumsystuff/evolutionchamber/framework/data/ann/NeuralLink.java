package de.clumsystuff.evolutionchamber.framework.data.ann;

import de.clumsystuff.evolutionchamber.evolutions.picalculation.RandomHelper;

public class NeuralLink {

    private Double transmissionValue = 0.0;
    private Neuron neuron;

    public NeuralLink mutate() {

        return this.setTransmissionValue(this.transmissionValue + RandomHelper.RANDOM.nextDouble() - 0.5);
    }

    public NeuralLink crossover(NeuralLink neuralLink) {

        return new NeuralLink()
                .setTransmissionValue((this.transmissionValue + neuralLink.getTransmissionValue()) / 2)
                .setNeuron(this.getNeuron().crossover(neuralLink.getNeuron()));
    }

    public Double getTransmissionValue() {
        return transmissionValue;
    }

    public NeuralLink setTransmissionValue(Double transmissionValue) {
        this.transmissionValue = transmissionValue;
        return this;
    }

    public Neuron getNeuron() {
        return neuron;
    }

    public NeuralLink setNeuron(Neuron neuron) {
        this.neuron = neuron;
        return this;
    }
}
