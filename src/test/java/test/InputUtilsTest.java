package test;

import org.junit.jupiter.api.Test;
import ru.autobus.model.Autobus;
import ru.autobus.threads.InputUtils;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class InputUtilsTest {

    @Test
    void readPositiveInt() {
        String input = "42\n"; // корректное положительное число
        Scanner scanner = new Scanner(input);

        int result = InputUtils.readPositiveInt(scanner);

        assertEquals(42, result);
    }

    @Test
    void readNonEmptyString() {
        String input = "ModelX\n";
        Scanner scanner = new Scanner(input);

        String result = InputUtils.readNonEmptyString(scanner);

        assertEquals("ModelX", result);
    }

    @Test
    void readFromConsole() {
        String input = "123\nModelX\n456\n";
        Scanner scanner = new Scanner(input);

        Autobus autobus = InputUtils.readFromConsole(scanner);

        assertEquals(123, autobus.getNumber());
        assertEquals("ModelX", autobus.getModel());
        assertEquals(456, autobus.getMileage());
    }
}