package de.clumsystuff.evolutionchamber.framework.data.ann.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.clumsystuff.evolutionchamber.framework.data.ann.NeuralLink;
import de.clumsystuff.evolutionchamber.framework.data.ann.NeuralNetwork;
import de.clumsystuff.evolutionchamber.framework.data.ann.Neuron;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class NeuralNetworkFactory {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static NeuralNetwork readFromFile(String jsonFilePath) throws URISyntaxException, IOException {

        String serialNeuralNetworkAsJson = Files.readString(Paths.get(Objects.requireNonNull(
                SerialNeuralNetwork.class.getClassLoader().getResource(jsonFilePath)).toURI()), StandardCharsets.UTF_8);

        return readFromString(serialNeuralNetworkAsJson);
    }

    public static NeuralNetwork readFromString(String jsonContent) throws IOException {

        return createNeuralNetwork(objectMapper.readValue(jsonContent, SerialNeuralNetwork.class));
    }

    public static NeuralNetwork createNeuralNetwork(SerialNeuralNetwork serialNeuralNetwork) {

        Map<String, Neuron> buildNeurons = serialNeuralNetwork.getNeurons().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> new Neuron(entry.getKey())
                        .setActivationThreshold(entry.getValue().getActivationThreshold())));

        serialNeuralNetwork.getNeurons().forEach((key1, value1) ->
                value1.getTransmissionValues().forEach((key, value) -> {

                    Neuron neuronToLink = buildNeurons.get(key1);
                    Neuron neuronToLinkTo = buildNeurons.get(key);

                    neuronToLink.linkToNeuron(neuronToLinkTo, value);
                    neuronToLinkTo.setLayer(neuronToLink.getLayer() + 1);
                }));

        int lastLayer = buildNeurons.values().stream()
                .map(Neuron::getLayer)
                .reduce(0, Math::max);

        List<Neuron> inputLayer = buildNeurons.values().stream()
                .filter(neuron -> neuron.getLayer() == 0)
                .toList();

        List<Neuron> outputLayer = buildNeurons.values().stream()
                .filter(neuron -> neuron.getLayer() == lastLayer)
                .toList();

        return new NeuralNetwork.Builder()
                .setInputLayer(inputLayer)
                .setOutputLayer(outputLayer)
                .build();
    }

    public static SerialNeuralNetwork createSerialNeuralNetwork(NeuralNetwork neuralNetwork) {

        Map<String, SerialNeuron> serialNeurons = new HashMap<>();
        neuralNetwork.getNeurons().forEach(neuron -> {

            Map<String, Double> transmissionValues = neuron.getNeuralLinks().stream()
                    .collect(Collectors.toMap(NeuralLink::getTargetNeuronId, NeuralLink::getTransmissionValue));

            SerialNeuron serialNeuron = new SerialNeuron()
                    .setActivationThreshold(neuron.getActivationThreshold())
                    .setTransmissionValues(transmissionValues);

            serialNeurons.put(neuron.getId(), serialNeuron);
        });

        return new SerialNeuralNetwork().setNeurons(serialNeurons);
    }
}
