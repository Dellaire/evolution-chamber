package de.clumsystuff.evolutionchamber.evolutions.picalculation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "de.clumsystuff.evolutionchamber.framework",
        "de.clumsystuff.evolutionchamber.evolutions.picalculation"})
public class PiCalculationApplication {

    public static void main(String[] args) {
        SpringApplication.run(PiCalculationApplication.class, args);
    }
}
