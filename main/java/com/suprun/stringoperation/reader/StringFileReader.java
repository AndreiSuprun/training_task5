package com.suprun.stringoperation.reader;

import com.suprun.stringoperation.exception.ValidationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StringFileReader {

    // default data file path
    private final String defaultFile = "file.txt";

    // method for filling ArrayWrapper from file data
    public String readStringFromFile(String filePath) throws ValidationException {
        if (filePath == null) {
            throw new ValidationException("Invalid input parameter");
        }
        String data = fileRead(filePath);
        if (data.isEmpty()) {
            System.out.println("Can not read from file, please enter text from console");
            StringConsoleReader consoleReader = new StringConsoleReader();
            data = consoleReader.readFromConsole();
        }
        return data;
    }

    public String fileRead(String filePath){
        Path file = null;
        if (Files.isRegularFile(Paths.get(filePath))){
            file = Paths.get(filePath);
        } else {

            URL fileLocation = this.getClass().getClassLoader().getResource(defaultFile);
            if (fileLocation != null) {
                file = Paths.get(fileLocation.toString().substring(6));
                System.out.printf("Failed to load array values from specified file %s, so array filled from default file%n",
                        filePath);}
        }
        StringBuilder lines = new StringBuilder();
        String line;
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            while ((line = reader.readLine()) != null){
                lines.append(line);
                lines.append(System.lineSeparator());
            }
            return lines.toString();
        } catch (IOException e) {
            System.out.printf("Failed to load array values from file %s%n", file.toString());
            return line = "";
        }
    }
}