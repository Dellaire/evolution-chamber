package de.clumsystuff.evolutionchamber.evolutions.evennumberdetection;

import de.clumsystuff.evolutionchamber.framework.data.ann.serialization.SerialNeuralNetwork;
import de.clumsystuff.evolutionchamber.framework.data.ann.serialization.SerialNeuron;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document("NeuralNetwork")
@TypeAlias("NeuralNetwork")
public class NeuralNetworkEntity extends SerialNeuralNetwork {

    public NeuralNetworkEntity() {
    }

    public NeuralNetworkEntity(Map<String, SerialNeuron> neurons) {
        this.setNeurons(neurons);
    }
}
