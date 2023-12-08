package de.clumsystuff.evolutionchamber.processing;

import de.clumsystuff.evolutionchamber.configuration.EvolutionChamberProperties;
import de.clumsystuff.evolutionchamber.data.Individual;
import de.clumsystuff.evolutionchamber.data.Population;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Component
public class EvolutionProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(EvolutionProcessor.class);
    private static final Random RANDOM = new Random();

    private final EvolutionChamberProperties evolutionChamberProperties;

    public EvolutionProcessor(EvolutionChamberProperties evolutionChamberProperties) {
        this.evolutionChamberProperties = evolutionChamberProperties;
    }

    Population createGenerations(Population population) {

        Population latestPopulation = population;
        for (int i = 0; i < this.evolutionChamberProperties.getNumberOfGenerations(); i++) {
            latestPopulation = this.createGeneration(latestPopulation);
        }

        return latestPopulation;
    }

    Population createGeneration(Population population) {

        List<Individual> bestIndividuals = population.getBestIndividuals(this.evolutionChamberProperties.getSelectionSize());
        List<Individual> newIndividuals = bestIndividuals.stream()
                .map(individual -> {
                    Individual partner = bestIndividuals.get(RANDOM.nextInt(bestIndividuals.size()));
                    Individual child = individual.crossover(partner);
                    return child.mutate();
                })
                .toList();
        population.addIndividuals(newIndividuals);

        Map<Individual, Double> currentlyBestIndividuals = population.getBestIndividualsWithFitness(1);
        LOGGER.debug("Best individual: " + currentlyBestIndividuals.keySet().stream().findFirst().get() + ", "
                + "Fitness: " + currentlyBestIndividuals.values().stream().findFirst().get());

        return population.removeWorstIndividuals(this.evolutionChamberProperties.getSelectionSize());
    }
}
