package de.clumsystuff.evolutionchamber.evolutions.evennumberdetection;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Document
@TypeAlias("Neuron")
public class NeuronEntity {

    @Id
    private String id;

    private String networkId;
    private Double activation;
    private Double activationThreshold;
    private List<NeuralLink> neuralLinks = new ArrayList<>();

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
