package de.clumsystuff.evolutionchamber.demo.persistence;

import de.clumsystuff.evolutionchamber.data.FitnessFunction;
import de.clumsystuff.evolutionchamber.data.Individual;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@TypeAlias("DoubleIndividual")
public class DoubleIndividual implements Individual {

    @Id
    private  String id;

    private double value;

    @Override
    public Double getFitness(FitnessFunction fitnessFunction) {

        return fitnessFunction.getFitness(this);
    }

    @Override
    public Individual mutate() {

        return new DoubleIndividual().setValue(this.value + RandomHelper.RANDOM.nextDouble() - 0.5);
    }

    @Override
    public Individual crossover(Individual individual) {

        DoubleIndividual doubleIndividual = (DoubleIndividual) individual;

        return new DoubleIndividual().setValue((this.value + doubleIndividual.getValue()) / 2);
    }

    public String getId() {
        return id;
    }

    public DoubleIndividual setId(String id) {
        this.id = id;
        return this;
    }

    public double getValue() {
        return value;
    }

    public DoubleIndividual setValue(double value) {
        this.value = value;
        return this;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}
