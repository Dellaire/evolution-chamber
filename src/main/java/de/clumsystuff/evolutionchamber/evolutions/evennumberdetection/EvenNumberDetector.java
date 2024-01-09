package de.clumsystuff.evolutionchamber.evolutions.evennumberdetection;

import de.clumsystuff.evolutionchamber.framework.data.ann.NeuralLink;
import de.clumsystuff.evolutionchamber.framework.data.ann.Neuron;
import de.clumsystuff.evolutionchamber.framework.processing.EvolutionProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EvenNumberDetector implements CommandLineRunner {

    @Autowired
    private NeuralNetworkRepository neuralNetworkRepository;

    @Autowired
    private EvolutionProcessor evolutionProcessor;

    @Override
    public void run(String... args) {

        Neuron neuron1 = new Neuron().setId("1");
        Neuron neuron2 = new Neuron().setId("2");
        Neuron neuron3 = new Neuron().setId("3");
        Neuron neuron4 = new Neuron().setId("4");
        Neuron neuron5 = new Neuron().setId("5");
        Neuron neuron6 = new Neuron().setId("6");

        neuron1.setNeuralLinks(List.of(
                new NeuralLink().setNeuron(neuron3).setTransmissionValue(1.0),
                new NeuralLink().setNeuron(neuron4).setTransmissionValue(1.0),
                new NeuralLink().setNeuron(neuron5).setTransmissionValue(1.0)));

        neuron2.setNeuralLinks(List.of(
                new NeuralLink().setNeuron(neuron3).setTransmissionValue(1.0),
                new NeuralLink().setNeuron(neuron4).setTransmissionValue(1.0),
                new NeuralLink().setNeuron(neuron5).setTransmissionValue(1.0)));

        neuron3.setNeuralLinks(List.of(new NeuralLink().setNeuron(neuron6).setTransmissionValue(1.0)));
        neuron4.setNeuralLinks(List.of(new NeuralLink().setNeuron(neuron6).setTransmissionValue(1.0)));
        neuron5.setNeuralLinks(List.of(new NeuralLink().setNeuron(neuron6).setTransmissionValue(1.0)));

        List<Neuron> inputLayer = List.of(neuron1, neuron2);
        List<Neuron> outputLayer = List.of(neuron6);

        NeuralNetworkEntity neuralNetwork = new NeuralNetworkEntity();
        neuralNetwork.setInputLayer(inputLayer);
        neuralNetwork.setOutputLayer(outputLayer);

        neuralNetwork.apply(List.of(1.0, 1.0));

        this.neuralNetworkRepository.deleteAll();
        this.neuralNetworkRepository.save(neuralNetwork);

        System.out.println(neuralNetwork.evaluate());
    }
}
