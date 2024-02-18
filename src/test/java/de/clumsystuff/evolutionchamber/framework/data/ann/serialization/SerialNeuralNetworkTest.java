package de.clumsystuff.evolutionchamber.framework.data.ann.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.clumsystuff.evolutionchamber.framework.data.ann.NeuralNetwork;
import de.clumsystuff.evolutionchamber.framework.data.ann.Neuron;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class SerialNeuralNetworkTest {

    @Test
    public void createNeuralNetwork() throws URISyntaxException, IOException {

        NeuralNetwork neuralNetwork = NeuralNetworkFactory.readFromFile("testData/serialNeuralNetwork1.json");

        assertThat(neuralNetwork.getInputLayer().get(1)
                .getActivationThreshold()).isEqualTo(0.5);
        assertThat(neuralNetwork.getInputLayer().get(1)
                .getNeuralLinks().get(1)
                .getTransmissionValue()).isEqualTo(0.2);
        assertThat(neuralNetwork.getInputLayer().get(1)
                .getNeuralLinks().get(1).getNeuron()
                .getActivationThreshold()).isEqualTo(0.5);
        assertThat(neuralNetwork.getInputLayer().get(1)
                .getNeuralLinks().get(1).getNeuron()
                .getNeuralLinks().get(0).getTransmissionValue()).isEqualTo(0.1);
        assertThat(neuralNetwork.getInputLayer().get(1)
                .getNeuralLinks().get(1).getNeuron()
                .getNeuralLinks().get(0).getNeuron()
                .getActivationThreshold()).isEqualTo(0.5);

        NeuralNetworkFactory.createSerialNeuralNetwork(neuralNetwork);
    }

    @Test
    public void createSerialNeuralNetwork() throws IOException, URISyntaxException {

        Neuron neuron1 = new Neuron("1").setActivationThreshold(0.5);
        Neuron neuron2 = new Neuron("2").setActivationThreshold(0.5);
        Neuron neuron3 = new Neuron("3").setActivationThreshold(0.5);
        Neuron neuron4 = new Neuron("4").setActivationThreshold(0.5);
        Neuron neuron5 = new Neuron("5").setActivationThreshold(0.5);
        Neuron neuron6 = new Neuron("6").setActivationThreshold(0.5);

        neuron1.linkToNeuron(neuron3, 0.1);
        neuron1.linkToNeuron(neuron4, 0.2);
        neuron1.linkToNeuron(neuron5, 0.3);
        neuron2.linkToNeuron(neuron3, 0.1);
        neuron2.linkToNeuron(neuron4, 0.2);
        neuron2.linkToNeuron(neuron5, 0.3);
        neuron3.linkToNeuron(neuron6, 0.1);
        neuron4.linkToNeuron(neuron6, 0.1);
        neuron5.linkToNeuron(neuron6, 0.1);

        List<Neuron> inputLayer = List.of(neuron1, neuron2);
        List<Neuron> outputLayer = List.of(neuron6);
        NeuralNetwork neuralNetwork = new NeuralNetwork.Builder()
                .setInputLayer(inputLayer)
                .setOutputLayer(outputLayer)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();

        String serializedNeuralNetwork =
                objectMapper.writeValueAsString(NeuralNetworkFactory.createSerialNeuralNetwork(neuralNetwork));

        String rawExpectedSerializedNeuralNetwork = Files.readString(
                        Paths.get(
                                Objects.requireNonNull(
                                                SerialNeuralNetwork.class.getClassLoader()
                                                        .getResource("testData/serialNeuralNetwork1.json"))
                                        .toURI()), StandardCharsets.UTF_8);

        String expectedSerializedNeuralNetwork = objectMapper.writeValueAsString(objectMapper.readTree(rawExpectedSerializedNeuralNetwork));

        assertThat(serializedNeuralNetwork).isEqualTo(expectedSerializedNeuralNetwork);
    }
}
