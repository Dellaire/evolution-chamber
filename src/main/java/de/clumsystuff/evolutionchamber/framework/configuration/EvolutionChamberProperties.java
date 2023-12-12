package de.clumsystuff.evolutionchamber.framework.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("evolution")
public class EvolutionChamberProperties {

    private Integer numberOfGenerations;
    private Integer selectionSize;

    public Integer getNumberOfGenerations() {
        return numberOfGenerations;
    }

    public void setNumberOfGenerations(Integer numberOfGenerations) {
        this.numberOfGenerations = numberOfGenerations;
    }

    public Integer getSelectionSize() {
        return selectionSize;
    }

    public void setSelectionSize(Integer selectionSize) {
        this.selectionSize = selectionSize;
    }
}
