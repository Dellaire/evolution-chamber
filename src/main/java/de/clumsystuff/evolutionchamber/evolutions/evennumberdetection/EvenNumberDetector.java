package de.clumsystuff.evolutionchamber.evolutions.evennumberdetection;

import de.clumsystuff.evolutionchamber.framework.data.ann.NeuralLink;
import de.clumsystuff.evolutionchamber.framework.data.ann.NeuralNetwork;
import de.clumsystuff.evolutionchamber.framework.data.ann.Neuron;
import de.clumsystuff.evolutionchamber.framework.processing.EvolutionProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.List;

//@Component
public class EvenNumberDetector implements CommandLineRunner {

    @Autowired
    private NeuralNetworkRepository neuralNetworkRepository;

    @Autowired
    private EvolutionProcessor evolutionProcessor;

    @Override
    public void run(String... args) {

        /*Neuron neuron1 = new Neuron().setId("1");
        Neuron neuron2 = new Neuron().setId("2");
        Neuron neuron3 = new Neuron().setId("3");
        Neuron neuron4 = new Neuron().setId("4");
        Neuron neuron5 = new Neuron().setId("5");
        Neuron neuron6 = new Neuron().setId("6");

        neuron1.setFarwardLinks(List.of(
                new NeuralLink().setNeuron(neuron3).setTransmissionValue(1.0),
                new NeuralLink().setNeuron(neuron4).setTransmissionValue(1.0),
                new NeuralLink().setNeuron(neuron5).setTransmissionValue(1.0)));

        neuron2.setFarwardLinks(List.of(
                new NeuralLink().setNeuron(neuron3).setTransmissionValue(1.0),
                new NeuralLink().setNeuron(neuron4).setTransmissionValue(1.0),
                new NeuralLink().setNeuron(neuron5).setTransmissionValue(1.0)));

        neuron3.setFarwardLinks(List.of(new NeuralLink().setNeuron(neuron6).setTransmissionValue(1.0)));
        neuron4.setFarwardLinks(List.of(new NeuralLink().setNeuron(neuron6).setTransmissionValue(1.0)));
        neuron5.setFarwardLinks(List.of(new NeuralLink().setNeuron(neuron6).setTransmissionValue(1.0)));

        List<Neuron> inputLayer = List.of(neuron1, neuron2);
        List<Neuron> outputLayer = List.of(neuron6);

        NeuralNetwork neuralNetwork = new NeuralNetwork();
        neuralNetwork.setInputLayer(inputLayer);
        neuralNetwork.setOutputLayer(outputLayer);

        //neuralNetwork.apply(List.of(1.0, 1.0));
        neuralNetwork.crossover(neuralNetwork);

        NeuralNetworkEntity neuralNetworkEntity = new NeuralNetworkEntity(neuralNetwork);

        this.neuralNetworkRepository.deleteAll();
        this.neuralNetworkRepository.save(neuralNetworkEntity);

        System.out.println(neuralNetwork.evaluate());*/
    }
}
