package de.clumsystuff.evolutionchamber.evolutions.evennumberdetection;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface NeuralNetworkRepository extends MongoRepository<NeuralNetwork, String> {
}
