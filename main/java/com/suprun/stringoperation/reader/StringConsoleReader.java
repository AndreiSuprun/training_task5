package com.suprun.stringoperation.reader;

import java.util.Scanner;

public class StringConsoleReader {

    public String readFromConsole() {
        Scanner scanner = new Scanner(System.in);
        String output = "";
        System.out.printf("Enter text for performing string operations%n");
        if (scanner.hasNextLine()){
            output = scanner.nextLine();
        }
        return output;
    }
}
