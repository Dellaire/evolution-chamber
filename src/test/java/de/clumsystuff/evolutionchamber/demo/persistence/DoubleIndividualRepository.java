package de.clumsystuff.evolutionchamber.demo.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DoubleIndividualRepository extends MongoRepository<DoubleIndividual, String> {
}
