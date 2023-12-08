package de.clumsystuff.evolutionchamber.processing;

import de.clumsystuff.evolutionchamber.data.FitnessFunction;
import de.clumsystuff.evolutionchamber.data.Individual;
import de.clumsystuff.evolutionchamber.data.Population;
import de.clumsystuff.evolutionchamber.demo.persistence.DoubleIndividual;
import de.clumsystuff.evolutionchamber.demo.persistence.DoubleIndividualRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class EvolutionProcessorTest {

    @Autowired
    private DoubleIndividualRepository doubleIndividualRepository;

    @Autowired
    private EvolutionProcessor evolutionProcessor;

    @Test
    public void createGenerations() {

        List<Individual> individuals = new ArrayList<>(this.doubleIndividualRepository.findAll());

        FitnessFunction fitnessFunction = individual -> 1.0 / Math.abs(((DoubleIndividual) individual).getValue() - Math.PI);

        Population population = new Population(individuals, fitnessFunction);

        this.evolutionProcessor.createGenerations(population);
    }
}
