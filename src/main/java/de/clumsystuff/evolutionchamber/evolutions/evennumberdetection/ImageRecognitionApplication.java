package de.clumsystuff.evolutionchamber.evolutions.evennumberdetection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "de.clumsystuff.evolutionchamber.framework",
        "de.clumsystuff.evolutionchamber.evolutions.imagerecognition"})
public class ImageRecognitionApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImageRecognitionApplication.class, args);
    }
}
