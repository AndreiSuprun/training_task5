package com.suprun.stringoperation.service;

import com.suprun.stringoperation.exception.ValidationException;

public interface StringEditor {

    String replaceLetterByIndex(String inputString, int index, char letter) throws ValidationException;

    String replaceNextLetter(String input, char findingLetter, char replaceFrom, char replaceTo) throws ValidationException;

    String replaceWordWithSubstring(String inputString, int wordLength, String subString) throws ValidationException;
}
