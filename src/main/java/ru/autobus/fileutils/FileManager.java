package ru.autobus.fileutils;

import ru.autobus.model.Autobus;
import ru.autobus.model.MyArrayList;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class FileManager<T> {
    private String filePath;
    private MyArrayList<T> data;

    public FileManager(String filePath){
        this.filePath = filePath;
    }


    public boolean checkFileExists(){
        File file = new File(filePath);
        if (!file.exists()){
            System.out.println("Файл не существует или путь указан некорректно");
            return false;
        }
        if (file.isFile()){
            return true;
        } else if (file.isDirectory()) {
            System.out.println("Указан каталог (папка), название файла в пути не указано");
            return false;
        } else {
            System.out.println("Объект не существует или не определён.");
            return false;
        }
    }

    public void createFile(){
        File file = new File(filePath);
        if (file.exists()) {
            if (file.isFile())
            System.out.println("Файл уже существует");
            else if (file.isDirectory()) {
                System.out.println("Указан каталог (папка), файл не создан");
            } else {
                System.out.println("Объект не существует или не определён.");
            }
        } else {
            try {
                file.createNewFile();
                System.out.println("Файл успешно создан");
            } catch (IOException e) {
                System.err.println("Ошибка при создании файла: " + e.getMessage());
            }
        }
    }

    public void clearFile(){
        if (checkFileExists()) {
            try (FileWriter fileWriter = new FileWriter(filePath)){
                // открытие в режиме перезаписи очищает файл
                System.out.println("Файл " + filePath + " очищен");
            } catch (IOException e){
                System.err.println("Ошибка при очистке файла: " + e.getMessage());
            }
        } else {
            System.out.println("Проверка существования файла не пройдена, очистка файла отменена");
        }
    }


    public void appendToFile(MyArrayList<T> data){
        if (checkFileExists()) {
            try (FileWriter fileWriter = new FileWriter(filePath, true)){ // true - включает режим APPEND
                if (data != null && data.size() > 0){
                    fileWriter.write("Record begin: \n");

                    data.stream()
                        .map(Object::toString)
                        .forEach(str-> {
                            try {
                                fileWriter.write(str + "\n");
                            } catch (IOException e) {
                                System.err.println("Ошибка при добавлении в файл (внутр): " + e.getMessage());
                            }
                        });
                    fileWriter.write("Record end: \n");
                    System.out.println("Информация в файл " + filePath + " записана.");
                } else {
                    System.out.println("Данные для записи в файл отсутствуют");
                }
            } catch (IOException e){
                System.err.println("Ошибка при добавлении в файл (внеш): " + e.getMessage());
            }
        } else {
            System.out.println("Проверка существования файла не пройдена, добавление в файл отменено");
        }
    }

    public void appendToFile(Autobus target, long count){
        if (checkFileExists()) {
            try (FileWriter fileWriter = new FileWriter(filePath, true)){ // true - включает режим APPEND
                if (target != null){
                    fileWriter.write("Искомый автобус: " + target + "\n");
                    fileWriter.write("Найдено одинаковых автобусов: " + count + "\n");
                    fileWriter.write("Record end: \n");
                    System.out.println("Информация в файл " + filePath + " записана.");
                } else {
                    System.out.println("Данные для записи в файл отсутствуют");
                }
            } catch (IOException e){
                System.err.println("Ошибка при добавлении в файл (внеш): " + e.getMessage());
            }
        } else {
            System.out.println("Проверка существования файла не пройдена, добавление в файл отменено");
        }
    }

    public void runAppendToFileDialog(Scanner scanner, MyArrayList<T> data){
        System.out.println("Записать результат в файл? (Y/N)");
        while (true){
            String choose = scanner.nextLine().trim().toLowerCase();
            switch (choose){
                case "y":
                    appendToFile(data);
                    break;
                case "n":
                    break;
                default:
                    System.out.println("Только Y или N");
                    continue;
            }
            break;

        }
    }

    public void runAppendToFileDialog(Scanner scanner, Autobus target, long count){
        System.out.println("Записать результат в файл? (Y/N)");
        while (true){
            String choose = scanner.nextLine().trim().toLowerCase();
            switch (choose){
                case "y":
                    appendToFile(target, count);
                    break;
                case "n":
                    break;
                default:
                    System.out.println("Только Y или N");
                    continue;
            }
            break;
        }
    }
}
