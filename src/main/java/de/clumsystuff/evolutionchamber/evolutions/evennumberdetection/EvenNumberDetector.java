package de.clumsystuff.evolutionchamber.evolutions.evennumberdetection;

import de.clumsystuff.evolutionchamber.framework.processing.EvolutionProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class EvenNumberDetector implements CommandLineRunner {

    @Autowired
    private NeuralNetworkMapper neuralNetworkMapper;

    @Autowired
    private NeuralNetworkRepository neuralNetworkRepository;

    @Autowired
    private EvolutionProcessor evolutionProcessor;

    @Override
    public void run(String... args) {

        Neuron neuron6 = new Neuron();
        neuron6.setNeuralLinks(Map.of());

        Neuron neuron3 = new Neuron();
        neuron3.setNeuralLinks(Map.of(neuron6, 1.0));
        Neuron neuron4 = new Neuron();
        neuron4.setNeuralLinks(Map.of(neuron6, 1.0));
        Neuron neuron5 = new Neuron();
        neuron5.setNeuralLinks(Map.of(neuron6, 1.0));

        Neuron neuron1 = new Neuron();
        neuron1.setNeuralLinks(Map.of(neuron3, 1.0, neuron4, 1.0, neuron5, 1.0));
        Neuron neuron2 = new Neuron();
        neuron2.setNeuralLinks(Map.of(neuron3, 1.0, neuron4, 1.0, neuron5, 1.0));

        List<Neuron> inputLayer = List.of(neuron1, neuron2);
        List<Neuron> outputLayer = List.of(neuron6);

        NeuralNetwork neuralNetwork = new NeuralNetwork()
                .setInputLayer(inputLayer)
                .setOutputLayer(outputLayer);

        this.neuralNetworkRepository.saveAll(this.neuralNetworkMapper.mapToEntities(neuralNetwork));
    }
}
