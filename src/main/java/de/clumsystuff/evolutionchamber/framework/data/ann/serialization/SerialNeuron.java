package de.clumsystuff.evolutionchamber.framework.data.ann.serialization;

import java.util.HashMap;
import java.util.Map;

public class SerialNeuron {

    private Double activationThreshold = 0.0;
    private Map<String, Double> transmissionValues = new HashMap<>();

    public Double getActivationThreshold() {
        return activationThreshold;
    }

    public SerialNeuron setActivationThreshold(Double activationThreshold) {
        this.activationThreshold = activationThreshold;
        return this;
    }

    public Map<String, Double> getTransmissionValues() {
        return transmissionValues;
    }

    public SerialNeuron setTransmissionValues(Map<String, Double> transmissionValues) {
        this.transmissionValues = transmissionValues;
        return this;
    }
}
