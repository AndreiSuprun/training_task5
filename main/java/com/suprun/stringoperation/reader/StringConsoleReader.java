package com.suprun.stringoperation.reader;

import java.util.Scanner;

// class is used for reading string data from console
public class StringConsoleReader {

    // method for reading string from console
    public String readFromConsole() {
        Scanner scanner = new Scanner(System.in);
        String output = "";
        System.out.printf("Enter text for performing string operations%n");
        while(output.isEmpty() && scanner.hasNextLine()){
            output = scanner.nextLine();
        }
        return output;
    }
}