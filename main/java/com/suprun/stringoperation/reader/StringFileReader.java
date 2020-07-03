package com.suprun.stringoperation.reader;

import com.suprun.stringoperation.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// class is used for reading string from file
public class StringFileReader {

    private static final Logger logger = LoggerFactory.getLogger(StringFileReader.class);
    // default data file path
    private static final String defaultFile = "file.txt";
    private static final String SPACE = " ";

    // method for reading string from file data or console
    public String readStringFromFile(String filePath) throws ValidationException {
        if (filePath == null) {
            throw new ValidationException("Invalid input parameter");
        }
        String data = fileRead(filePath);
        if (data.isEmpty()) {
            logger.warn("Could not read string data from file, requested string data from console");
            StringConsoleReader consoleReader = new StringConsoleReader();
            data = consoleReader.readFromConsole();
        }
        return data;
    }

    // method for reading string data from specified or default file
    private String fileRead(String filePath) {
        Path file = null;
        if (Files.isRegularFile(Paths.get(filePath))) {
            file = Paths.get(filePath);
        } else {
            URL fileLocation = this.getClass().getClassLoader().getResource(defaultFile);
            if (fileLocation != null) {
                file = Paths.get(fileLocation.toString().substring(6));
                logger.warn("Failed to load string data from specified file {}, so string was loaded from default file",
                        filePath);
            }
        }
        StringBuilder lines = new StringBuilder();
        String line = "";
        if (file != null) {
            try (BufferedReader reader = Files.newBufferedReader(file)) {
                while ((line = reader.readLine()) != null) {
                    lines.append(line);
                    lines.append(SPACE).append(System.lineSeparator());
                }
                return lines.toString();
            } catch (IOException e) {
                logger.warn("Failed string data from file {}", file.toString(), e);
            }
        }
        return line = "";
    }
}