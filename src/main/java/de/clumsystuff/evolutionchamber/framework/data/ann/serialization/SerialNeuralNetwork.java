package de.clumsystuff.evolutionchamber.framework.data.ann.serialization;

import java.util.HashMap;
import java.util.Map;

public class SerialNeuralNetwork {

    private Map<String, SerialNeuron> neurons = new HashMap<>();

    public Map<String, SerialNeuron> getNeurons() {
        return neurons;
    }

    public SerialNeuralNetwork setNeurons(Map<String, SerialNeuron> neurons) {
        this.neurons = neurons;
        return this;
    }
}
