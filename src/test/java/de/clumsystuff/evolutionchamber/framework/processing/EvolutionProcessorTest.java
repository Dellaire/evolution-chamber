package de.clumsystuff.evolutionchamber.framework.processing;

import de.clumsystuff.evolutionchamber.framework.configuration.EvolutionChamberProperties;
import de.clumsystuff.evolutionchamber.framework.data.core.Population;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class EvolutionProcessorTest {

    @Test
    public void createGenerations() {

        EvolutionChamberProperties evolutionChamberProperties = new EvolutionChamberProperties();
        evolutionChamberProperties.setNumberOfGenerations(3);
        evolutionChamberProperties.setSelectionSize(2);

        Population population = mock(Population.class);
        when(population.getBestIndividuals(anyInt())).thenReturn(List.of());
        when(population.getWorstIndividuals(anyInt())).thenReturn(List.of());
        when(population.removeWorstIndividuals(anyInt())).thenReturn(population);

        EvolutionProcessor evolutionProcessor = new EvolutionProcessor(evolutionChamberProperties);
        evolutionProcessor.createGenerations(population);

        verify(population, times(3)).getBestIndividuals(2);
        verify(population, times(3)).getBestIndividualsWithFitness(1);
        verify(population, times(3)).removeWorstIndividuals(2);
    }
}
