package de.clumsystuff.evolutionchamber.evolutions.evennumberdetection;

import de.clumsystuff.evolutionchamber.framework.data.ann.NeuralNetwork;
import de.clumsystuff.evolutionchamber.framework.data.ann.serialization.NeuralNetworkFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootTest
public class EvenNumberDetectionTest {

    @Autowired
    private NeuralNetworkRepository neuralNetworkRepository;

    @Test
    public void detectEvenNumber() throws URISyntaxException, IOException {

        NeuralNetwork neuralNetwork1 = NeuralNetworkFactory.readFromFile("testData/serialNeuralNetwork1.json");
        NeuralNetwork neuralNetwork2 = NeuralNetworkFactory.readFromFile("testData/serialNeuralNetwork2.json");

        NeuralNetwork childNeuralNetwork = (NeuralNetwork) neuralNetwork1.crossover(neuralNetwork2);

        NeuralNetworkEntity neuralNetworkEntity = new NeuralNetworkEntity();
        neuralNetworkEntity.setNeurons(NeuralNetworkFactory.createSerialNeuralNetwork(childNeuralNetwork).getNeurons());
        this.neuralNetworkRepository.deleteAll();
        this.neuralNetworkRepository.save(neuralNetworkEntity);
    }
}
