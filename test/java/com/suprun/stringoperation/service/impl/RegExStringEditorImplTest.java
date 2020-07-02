package com.suprun.stringoperation.service.impl;

import com.suprun.stringoperation.exception.ValidationException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class RegExStringEditorImplTest {

    RegExStringEditorImpl stringEditor;
    @BeforeClass
    public void beforeClass() {
        stringEditor = new RegExStringEditorImpl();
    }

    @DataProvider
    public static Object[][] replaceByIndexEquals() {
        return new Object[][]{{"The class String includes methods for examining individual characters of the sequence," +
                " for comparing strings, for searching strings, for extracting substrings, and for creating a copy of a" +
                " string with all characters translated to uppercase or to lowercase", 4, 'Q',
                "The claQs StrQng incQudes metQods for exaQining indQvidual chaQacters of the seqQence, for comQaring " +
                        "strQngs, for seaQching strQngs, for extQacting subQtrings, and for creQting a copQ of a strQng " +
                        "witQ all chaQacters traQslated to uppQrcase or to lowQrcase"}};
    }

    @Test(dataProvider = "replaceByIndexEquals")
    public void testReplaceLetterByIndexEquals(String inputString, int index, char replacingChar, String expectedResult)
            throws ValidationException {
        String actual = stringEditor.replaceLetterByIndex(inputString, index, replacingChar);
        assertEquals(actual, expectedResult);
    }

    @DataProvider
    public static Object[][] replaceByIndexNotEquals() {
        return new Object[][]{{"The class String includes methods for examining individual characters of the sequence," +
                " for comparing strings, for searching strings, for extracting substrings, and for creating a copy of a" +
                " string with all characters translated to uppercase or to lowercase", 4, 'Q',
                "The claQs StrQng includes methods for examining individual characters of the sequence, for comparing " +
                        "strings, for searching strings, for extracting substrings, and for creating a copy of string " +
                        "with all characters translated to uppercase or to lowercase"}};
    }

    @Test(dataProvider = "replaceByIndexNotEquals")
    public void testReplaceLetterByIndexNotEquals(String inputString, int index, char replacingChar,
                                                  String expectedResult) throws ValidationException {
        String actual = stringEditor.replaceLetterByIndex(inputString, index, replacingChar);
        assertNotEquals(actual, expectedResult);
    }

    @Test(expectedExceptions = ValidationException.class)
    public void testReplaceLetterByIndexExceptionString() throws ValidationException {
        stringEditor.replaceLetterByIndex(null, 4, 'Q');
    }

    @Test(expectedExceptions = ValidationException.class)
    public void testReplaceLetterByIndexExceptionIndex() throws ValidationException {
        stringEditor.replaceLetterByIndex("String", -2, 'Q');
    }

    @DataProvider
    public static Object[][] replaceNextLetterEquals() {
        return new Object[][]{{"The class String includes methods for examining individual characters of the sequence," +
                " for comparing strings, for searching strings, for extracting substrings, and for creating a copy of a" +
                " string with all characters translated to uppercase or to lowercase", 's','t', 'o',
                "The class Soring includes methods for examining individual characters of the sequence, for comparing " +
                        "sorings, for searching sorings, for extracting subsorings, and for creating a copy of a soring " +
                        "with all characters translated to uppercase or to lowercase"}};
    }
    @Test(dataProvider = "replaceNextLetterEquals")
    public void testReplaceNextLetterEquals(String input, char find, char replaceFrom, char replaceTo,
                                            String expectedResult) throws ValidationException {
        String actual = stringEditor.replaceNextLetter(input, find, replaceFrom, replaceTo);
        assertEquals(actual, expectedResult);
    }

    @DataProvider
    public static Object[][] replaceNextLetterNotEquals() {
        return new Object[][]{{"The class String includes methods for examining individual characters of the sequence," +
                " for comparing strings, for searching strings, for extracting substrings, and for creating a copy of a" +
                " string with all characters translated to uppercase or to lowercase", 's','t', 'o',
                "The class String includes methods for examining individual characters of the sequence, for comparing " +
                        "strings, for searching strings, for extracting substrings, and for creating a copy of string " +
                        "with all characters translated to uppercase or to lowercase"}};
    }
    @Test(dataProvider = "replaceNextLetterNotEquals")
    public void testReplaceNextLetterNotEquals(String input, char find, char replaceFrom, char replaceTo,
                                               String expectedResult) throws ValidationException {
        String actual = stringEditor.replaceNextLetter(input, find, replaceFrom, replaceTo);
        assertNotEquals(actual, expectedResult);
    }

    @Test(expectedExceptions = ValidationException.class)
    public void testReplaceNextLetterExceptionString() throws ValidationException {
        stringEditor.replaceNextLetter(null, 's', 't', 'o');
    }

    @DataProvider
    public static Object[][] replaceWordWithSubstringEquals() {
        return new Object[][]{{"The class String includes methods for examining individual characters of the sequence," +
                " for comparing strings, for searching strings, for extracting substrings, and for creating a copy of a" +
                " string with all characters translated to uppercase or to lowercase", 7, "CTRL",
                "The class String includes CTRL for examining individual characters of the sequence, for comparing CTRL," +
                        " for searching CTRL, for extracting substrings, and for creating a copy of a string with all " +
                        "characters translated to uppercase or to lowercase"}};
    }

    @Test(dataProvider = "replaceWordWithSubstringEquals")
    public void testReplaceWordWithSubstringEquals(String inputString, int index, String substring, String expectedResult)
            throws ValidationException {
        String actual = stringEditor.replaceWordWithSubstring(inputString, index, substring);
        assertEquals(actual, expectedResult);
    }

    @DataProvider
    public static Object[][] replaceWordWithSubstringNotEquals() {
        return new Object[][]{{"The class String includes methods for examining individual characters of the sequence," +
                " for comparing strings, for searching strings, for extracting substrings, and for creating a copy of a" +
                " string with all characters translated to uppercase or to lowercase", 7, "CTRL",
                "The class String includes strings for examining individual characters of the sequence, for comparing strings, for searching strings, for extracting substrings, and for creating a copy of string with all characters translated to uppercase or to lowercase"}};
    }

    @Test(dataProvider = "replaceWordWithSubstringNotEquals")
    public void testReplaceWordWithSubstringNotEquals(String inputString, int index, String substring, String expectedResult)
            throws ValidationException {
        String actual = stringEditor.replaceWordWithSubstring(inputString, index, substring);
        assertNotEquals(actual, expectedResult);
    }

    @Test(expectedExceptions = ValidationException.class)
    public void testReplaceWordWithSubstringExceptionString() throws ValidationException {
        stringEditor.replaceWordWithSubstring(null, 7, "CTRL");
    }

    @Test(expectedExceptions = ValidationException.class)
    public void testReplaceWordWithSubstringExceptionSubstring() throws ValidationException {
        stringEditor.replaceWordWithSubstring("STRINGS", 7, null);
    }

    @AfterClass
    public void afterClass() {
        stringEditor = null;
    }
}