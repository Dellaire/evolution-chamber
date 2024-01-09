package de.clumsystuff.evolutionchamber.evolutions.picalculation;

import de.clumsystuff.evolutionchamber.framework.data.core.FitnessFunction;
import de.clumsystuff.evolutionchamber.framework.data.core.Individual;
import de.clumsystuff.evolutionchamber.framework.data.core.Population;
import de.clumsystuff.evolutionchamber.framework.processing.EvolutionProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PiCalculator implements CommandLineRunner {

    @Autowired
    private DoubleIndividualRepository doubleIndividualRepository;

    @Autowired
    private EvolutionProcessor evolutionProcessor;

    @Override
    public void run(String... args) {

        List<Individual> individuals = new ArrayList<>(this.doubleIndividualRepository.findAll());

        FitnessFunction fitnessFunction = individual -> {
            DoubleIndividual doubleIndividual = (DoubleIndividual) individual;
            return 1.0 / (Math.abs(Math.sin(doubleIndividual.getValue())) + Math.abs(Math.cos(doubleIndividual.getValue()) + 1.0));
        };

        Population population = new Population(individuals, fitnessFunction);

        this.evolutionProcessor.createGenerations(population);
    }
}
