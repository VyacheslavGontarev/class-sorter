package test.java;


import main.java.fillers.FileFiller;
import main.java.fillers.FillerStrategy;
import main.java.model.Autobus;
import main.java.model.AutobusBuilder;
import main.java.model.AutobusComparator;
import org.junit.jupiter.api.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileFillerTest {
Path tempFile;
Comparator comparator = new AutobusComparator();

@BeforeEach
    void setUp() {
    try {
        tempFile = Files.createTempFile("autobuses", ".txt");
    Files.write(tempFile, List.of(
            "1, Volvo, 10000",
            "2, Mercedes, 20000",
            "invalid,line",
            "3, MAN, -500"
    ));
    } catch (IOException e) {
        System.out.println(e.getMessage());
    }
}

@AfterEach
    void tearDown() {
    try {
        Files.deleteIfExists(tempFile);
    } catch (IOException e) {
        System.out.println(e.getMessage());
    }
}

    @Test
    void testFill_ValidLines() {
        FileFiller filler = new FileFiller(tempFile.toString());
        List<Autobus> result = filler.fill(4);
        assertEquals(2, result.size());
        Autobus expected = new AutobusBuilder().withNumber(1).withModel("Volvo").withMileage(10000).build();
        Autobus actual = result.get(0);
        assertEquals(0, comparator.compare(expected, actual));
    }

    @Test
    void testFillerStrategy() {
        FileFiller filler = new FileFiller(tempFile.toString());
        List<Autobus> directArray = filler.fill(4);
        FillerStrategy strategy = new FillerStrategy();
        strategy.setFiller(filler);
        List<Autobus> strategyArray = strategy.executeFiller(4);
        assertEquals(directArray.size(), strategyArray.size());
        for (int i = 0; i < directArray.size(); i++) {
            Autobus expected = directArray.get(i);
            Autobus actual = strategyArray.get(i);
            assertEquals(0,comparator.compare(expected, actual));
        }
    }

    @Test
    void testFill_SizeLimit() {
        FileFiller filler = new FileFiller(tempFile.toString());
        List<Autobus> result = filler.fill(1);
        assertEquals(1, result.size());
    }
}
