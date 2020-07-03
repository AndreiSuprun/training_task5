package com.suprun.stringoperation.service.impl;

import com.suprun.stringoperation.exception.ValidationException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class StringRemoverImplTest {

    StringRemoverImpl stringRemover;

    @BeforeClass
    public void beforeClass() {
        stringRemover = new StringRemoverImpl();
    }

    @DataProvider
    public static Object[][] removeWordEquals() {
        return new Object[][]{{"The class String includes methods for examining individual characters of the sequence," +
                " for comparing strings, for searching strings, for extracting substrings, and for creating a copy of a" +
                " string with all characters translated to uppercase or to lowercase", 7,
                "The class String includes for examining individual characters of the sequence, for comparing for" +
                        " searching for extracting substrings, and for creating a copy of a string with all characters" +
                        " translated to uppercase or to lowercase"}};
    }

    @Test(dataProvider = "removeWordEquals")
    public void testRemoveWordBeginConsonantEquals(String inputString, int index, String expectedResult)
            throws ValidationException {
        String actual = stringRemover.removeWordBeginConsonant(inputString, index);
        assertEquals(actual, expectedResult);
    }

    @DataProvider
    public static Object[][] removeWordNotEquals() {
        return new Object[][]{{"The class String includes methods for examining individual characters of the sequence," +
                " for comparing strings, for searching strings, for extracting substrings, and for creating a copy of a" +
                " string with all characters translated to uppercase or to lowercase", 7,
                "The class String examining individual characters of sequence, for comparing strings, for strings, " +
                        " extracting and for creating a copy of string with all characters to uppercase or" +
                        " to lowercase"}};
    }

    @Test(dataProvider = "removeWordNotEquals")
    public void testRemoveWordBeginConsonantNotEquals(String inputString, int index, String expectedResult)
            throws ValidationException {
        String actual = stringRemover.removeWordBeginConsonant(inputString, index);
        assertNotEquals(actual, expectedResult);
    }

    @Test(expectedExceptions = ValidationException.class)
    public void testRemoveWordBeginConsonantExceptionString() throws ValidationException {
        stringRemover.removeWordBeginConsonant(null, 7);
    }

    @Test(expectedExceptions = ValidationException.class)
    public void testRemoveWordBeginConsonantExceptionIndex() throws ValidationException {
        stringRemover.removeWordBeginConsonant("Strings", -5);
    }

    @DataProvider
    public static Object[][] removeNonLetterSymbolsEquals() {
        return new Object[][]{{"The class Str#ing includ@es methods for exam%ining !individual charac*ters of the " +
                "sequence, for compa-ring strings, for sear+ching strings", "The class Str ing includ es methods for " +
                "exam ining individual charac ters of the sequence for compa-ring strings for sear ching strings"}};
    }

    @Test(dataProvider = "removeNonLetterSymbolsEquals")
    public void testRemoveNonLetterSymbolsEquals(String inputString, String expectedResult)
            throws ValidationException {
        String actual = stringRemover.removeNonLetterSymbols(inputString);
        assertEquals(actual, expectedResult);
    }

    @DataProvider
    public static Object[][] removeNonLetterSymbolsNotEquals() {
        return new Object[][]{{"The class Str#ing includ@es methods for exam%ining !individual charac*ters of the" +
                " sequence, for compa-ring strings, for sear+ching strings", "The class Str ing includ es methods for " +
                "exam%ining individual characters of the sequence for compa-ring strings for sear+ching strings"}};
    }

    @Test(dataProvider = "removeNonLetterSymbolsNotEquals")
    public void testRemoveNonLetterSymbolsNotEquals(String inputString, String expectedResult)
            throws ValidationException {
        String actual = stringRemover.removeNonLetterSymbols(inputString);
        assertNotEquals(actual, expectedResult);
    }

    @Test(expectedExceptions = ValidationException.class)
    public void testRemoveNonLetterSymbolsExceptionString() throws ValidationException {
        stringRemover.removeNonLetterSymbols(null);
    }

    @AfterClass
    public void afterClass() {
        stringRemover = null;
    }
}