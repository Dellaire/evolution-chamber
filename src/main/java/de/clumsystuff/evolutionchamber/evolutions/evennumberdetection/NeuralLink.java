package de.clumsystuff.evolutionchamber.evolutions.evennumberdetection;

public class NeuralLink {

    private Double transmissionValue = 0.0;
    private Neuron neuron;

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
