package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.autobus.fileutils.FileManager;
import ru.autobus.model.Autobus;
import ru.autobus.model.AutobusBuilder;
import ru.autobus.model.MyArrayList;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class FileManagerTest {
    private FileManager<String> fileManager;
    private FileManager<String> fileManager2;

    private final String TEST_FILE_PATH = "test.txt";
    private final String TEST_FILE_PATH2 = "test2.txt";

    @BeforeEach
    void setUp() {
        fileManager = new FileManager<>(TEST_FILE_PATH);
        fileManager2 = new FileManager<>(TEST_FILE_PATH2);

    }

    @AfterEach
    void tearDown() {
        File file = new File(TEST_FILE_PATH);
        File file2 = new File(TEST_FILE_PATH2);

        if (file.exists()) {
            if (!file.delete()) {
                System.err.println("Файл " + TEST_FILE_PATH + " не удалён.");
            }
        }
        if (file2.exists()) {
            if (!file2.delete()) {
                System.err.println("Файл " + TEST_FILE_PATH2 + " не удалён.");
            }
        }

    }

    // Test 1: Проверка существования файла
    @Test
    void testCheckFileExistsWhenFileDoesntExist() {
        assertFalse(fileManager.checkFileExists(), "Файл не должен существовать");
    }

    // Test 2: Проверка создания файла
    @Test
    void testCreateFile() {
        fileManager.createFile();
        assertTrue(new File(TEST_FILE_PATH).exists(), "Файл должен быть создан");
    }

    // Test 3: Проверка очистки файла
    @Test
    void testClearFile() {
        fileManager.createFile();
        fileManager.clearFile();
        assertTrue(new File(TEST_FILE_PATH).length() == 0, "Файл должен быть очищен");
    }

    // Test 4: Проверка добавления данных в файл
    @Test
    void testAppendToFile() throws IOException {
        File file2 = new File(TEST_FILE_PATH2);
        file2.createNewFile();

        MyArrayList<String> data = new MyArrayList<>();
        data.add("Data 1");
        data.add("Data 2");
        fileManager2.appendToFile(data);

        char[] buffer;
        try (FileReader reader = new FileReader(TEST_FILE_PATH2)) {
            buffer = new char[(int) new File(TEST_FILE_PATH2).length()];
            reader.read(buffer);
        }
        String content = new String(buffer);
        assertTrue(content.contains("Data 1"), "Данные должны быть записаны в файл");
        assertTrue(content.contains("Data 2"), "Данные должны быть записаны в файл");

    }

    // Test 5: Проверка диалога подтверждения записи (YES)
    @Test
    void testRunAppendToFileDialogYes() throws IOException {
        File file2 = new File(TEST_FILE_PATH2);
        file2.createNewFile();

        Scanner scanner = new Scanner("y\n");
        MyArrayList<String> data = new MyArrayList<>();
        data.add("Test Data");
        fileManager2.runAppendToFileDialog(scanner, data);
        assertTrue(new File(TEST_FILE_PATH2).length() > 0, "Файл должен быть заполнен");
    }

    // Test 6: Проверка диалога подтверждения записи (NO)
    @Test
    void testRunAppendToFileDialogNo() throws IOException {
        File file2 = new File(TEST_FILE_PATH2);
        file2.createNewFile();

        Scanner scanner = new Scanner("n\n");
        MyArrayList<String> data = new MyArrayList<>();
        data.add("Test Data");
        fileManager.runAppendToFileDialog(scanner, data);
        assertFalse(new File(TEST_FILE_PATH).length() > 0, "Файл не должен быть заполнен");
    }

    // Test 7: Проверка записи поиска дубликата
    @Test
    void testAppendBusData() throws IOException {
        File file2 = new File(TEST_FILE_PATH2);
        file2.createNewFile();

        Autobus bus1 = new AutobusBuilder()
                .withNumber(1)
                .withModel("bus1")
                .withMileage(1)
                .build();

        Autobus bus2 = new AutobusBuilder()
                .withNumber(1)
                .withModel("bus1")
                .withMileage(1)
                .build();

        fileManager2.appendToFile(bus1, 2);

        char[] buffer;
        try (FileReader reader = new FileReader(TEST_FILE_PATH2)) {
            buffer = new char[(int) new File(TEST_FILE_PATH2).length()];
            reader.read(buffer);
        }
        String content = new String(buffer);
        assertTrue(content.contains("Искомый автобус:"), "Запись данных должна быть успешной");
        assertTrue(content.contains("Найдено одинаковых автобусов: 2"), "Количество автобусов должно быть правильным");
    }

    // Тест 8: Проверка поведения метода createFile, если файл существует, но это каталог

    @Test
    void testCreateFile_WhenDirectoryExists() throws IOException {
        File dir = new File("testDir");
        if (!dir.exists()) {
            dir.mkdir();
        }
        FileManager<String> fm = new FileManager<>("testDir");
        fm.createFile();
        assertTrue(dir.exists() && dir.isDirectory());
        dir.delete();
    }

    // Тест 9: Проверка поведения метода checkFileExists для каталога

    @Test
    void testCheckFileExists_WhenPathIsDirectory() throws IOException {
        File dir = new File("testDir");
        if (!dir.exists()) {
            dir.mkdir();
        }
        FileManager<String> fm = new FileManager<>("testDir");
        assertFalse(fm.checkFileExists());
        dir.delete();
    }

    // Тест 10: Проверка метода appendToFile при передаче null и пустых данных

    @Test
    void testAppendToFile_WithNullAndEmptyData() throws IOException {
        File file = new File(TEST_FILE_PATH);
        file.createNewFile();

        MyArrayList<String> emptyData = new MyArrayList<>();
        // appendToFile с null
        fileManager.appendToFile(null);
        // appendToFile с пустым списком
        fileManager.appendToFile(emptyData);

        assertTrue(file.exists());
    }

    // Тест 11: Проверка диалога runAppendToFileDialog на ввод неверных значений с последующим "y"

    @Test
    void testRunAppendToFileDialog_InvalidThenYes() throws IOException {
        File file = new File(TEST_FILE_PATH);
        file.createNewFile();

        Scanner scanner = new Scanner("abc\nY\n");
        MyArrayList<String> data = new MyArrayList<>();
        data.add("Test Data");
        fileManager.runAppendToFileDialog(scanner, data);
        assertTrue(file.length() > 0);
    }
}