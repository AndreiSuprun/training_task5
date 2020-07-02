package com.suprun.stringoperation.reader;

import com.suprun.stringoperation.exception.ValidationException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class StringFileReaderTest {

    StringFileReader stringFileReader;

    @BeforeClass
    public void beforeClass() {
        stringFileReader = new StringFileReader();
    }

    @Test
    public void testReadStringFromFileEquals() throws ValidationException {
        String actual = stringFileReader.readStringFromFile("h");
        String expectedResult = "By default, the regular expressions ^ and $ ignore line terminators and only match" +
                " at the beginning and the end, respectively, of the entire input sequence."+System.lineSeparator();
        assertEquals(actual, expectedResult);
    }

    @Test
    public void testReadStringFromFileNotEquals() throws ValidationException {
        String actual = stringFileReader.readStringFromFile("h");
        String expectedResult = "By default, line terminators and only match" +
                " at the beginning and the end, respectively, of the entire input sequence."+System.lineSeparator();
        assertNotEquals(actual, expectedResult);
    }

    @Test(expectedExceptions = ValidationException.class)
    public void testReadStringFromFileException() throws ValidationException {
        stringFileReader.readStringFromFile(null);
    }

    @Test
    public void testFileReadEqual() {
        String actual = stringFileReader.fileRead("h");
        String expectedResult = "By default, the regular expressions ^ and $ ignore line terminators and only match" +
                " at the beginning and the end, respectively, of the entire input sequence."+System.lineSeparator();
        assertEquals(actual, expectedResult);
    }

    @Test
    public void testFileReadNotEqual() {
        String actual = stringFileReader.fileRead("h");
        String expectedResult = "By default, the regular expres $ ignore line terminators and only match" +
                " at the beginning the entire input sequence."+System.lineSeparator();
        assertNotEquals(actual, expectedResult);
    }

    @AfterClass
    public void afterClass() {
        stringFileReader = null;
    }
}