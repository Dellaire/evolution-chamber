package de.clumsystuff.evolutionchamber.evolutions.evennumberdetection;

import de.clumsystuff.evolutionchamber.framework.data.ann.NeuralLink;
import de.clumsystuff.evolutionchamber.framework.data.ann.NeuralNetwork;
import de.clumsystuff.evolutionchamber.framework.data.ann.Neuron;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class EvenNumberDetectionTest {

    @Autowired
    private NeuralNetworkRepository neuralNetworkRepository;

    @Test
    public void detectEvenNumber() {

        NeuralNetwork neuralNetwork1 = this.createNeuralNetwork();
        NeuralNetwork neuralNetwork2 = this.createNeuralNetwork();
        neuralNetwork2.mutate();

        NeuralNetwork childNeuralNetwork = (NeuralNetwork) neuralNetwork1.crossover(neuralNetwork2);

        NeuralNetworkEntity neuralNetworkEntity = new NeuralNetworkEntity(childNeuralNetwork);

        this.neuralNetworkRepository.deleteAll();
        this.neuralNetworkRepository.save(neuralNetworkEntity);
    }

    private NeuralNetwork createNeuralNetwork() {

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

        NeuralNetwork neuralNetwork = new NeuralNetwork();
        neuralNetwork.setInputLayer(inputLayer);
        neuralNetwork.setOutputLayer(outputLayer);

        return neuralNetwork;
    }
}
