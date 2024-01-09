package de.clumsystuff.evolutionchamber.evolutions.evennumberdetection;

import de.clumsystuff.evolutionchamber.framework.data.ann.NeuralNetwork;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("NeuralNetwork")
@TypeAlias("NeuralNetwork")
public class NeuralNetworkEntity extends NeuralNetwork {
}
