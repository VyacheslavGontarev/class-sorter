package ru.autobus.fileutils;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class FileManager<T> {

    public boolean checkFileExists(String filePath){
        File file = new File(filePath);
        if (!file.exists()){
            System.out.println("Файл не существует или путь указан некорректно");
            return false;
        }
        if (file.isFile()){
            System.out.println("Файл " + filePath + " существует");
            return true;
        } else if (file.isDirectory()) {
            System.out.println("Указан каталог (папка), название файла в пути не указано");
            return false;
        } else {
            System.out.println("Объект не существует или не определён.");
            return false;
        }
    }

    public void createFile(String filePath){
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

    public void clearFile(String filePath){
        if (checkFileExists(filePath)) {
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


        public void appendToFile(List<T> data, String filePath){
        if (checkFileExists(filePath)) {
            try (FileWriter fileWriter = new FileWriter(filePath, true)){ // true - включает режим APPEND
                if (data != null && data.size() > 0){
                    for (T element : data){
                        fileWriter.write("Record begin: \n");
                        fileWriter.write(element.toString());
                        fileWriter.write("\n");
                    }
                    fileWriter.write("Record end: \n");
                    System.out.println("Информация в файл " + filePath + " записана.");
                } else {
                    System.out.println("Данные для записи в файл отсутствуют");
                }
            } catch (IOException e){
                System.err.println("Ошибка при добавлении в файл: " + e.getMessage());
            }
        } else {
            System.out.println("Проверка существования файла не пройдена, добавление в файл отменено");
        }

    }

    public static void main(String[] args) {
        FileManager fileManager = new FileManager();

        String filePathExist = "C:\\MY\\1_PROGRAMMING\\JAVA\\Java Developer\\Итоговый проект\\temp\\output.txt";
        String pathExist = "C:\\MY\\1_PROGRAMMING\\JAVA\\Java Developer\\Итоговый проект\\temp\\";
        String fileNotExist = "C:\\MY\\1_PROGRAMMING\\JAVA\\Java Developer\\Итоговый проект\\temp\\none.txt";
        String pathNotExist = "C:\\MY\\1_PROGRAMMING\\JAVA\\Java Developer\\Итоговый проект\\temp\\none";

        if (fileManager.checkFileExists(fileNotExist)){
            fileManager.clearFile(fileNotExist);
        } else fileManager.createFile(fileNotExist);
    }


}
