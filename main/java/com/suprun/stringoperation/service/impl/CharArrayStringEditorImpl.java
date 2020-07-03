package com.suprun.stringoperation.service.impl;

import com.suprun.stringoperation.exception.ValidationException;
import com.suprun.stringoperation.service.StringEditor;

// class is used for edit string with char array operations
public class CharArrayStringEditorImpl implements StringEditor {

    private static final String SPACE = " ";
    private static final char DOT = '.';
    private static final char COMA = ',';

    // method for letter in word by index
    @Override
    public String replaceLetterByIndex(String inputString, int index, char letter) throws ValidationException {
        if (inputString == null || index < 0) {
            throw new ValidationException("Invalid input parameter");
        }
        String[] words = inputString.split(SPACE);
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() >= index) {
                char[] word = words[i].toCharArray();
                word[index - 1] = letter;
                words[i] = String.copyValueOf(word);
            }
        }
        return String.join(SPACE, words);
    }

    // method for replacing next to specified letter
    @Override
    public String replaceNextLetter(String input, char findingLetter, char replaceFrom, char replaceTo)
            throws ValidationException {
        if (input == null) {
            throw new ValidationException("Invalid input parameter");
        }
        String[] words = input.split(SPACE);
        for (int i = 0; i < words.length; i++) {
            char[] word = words[i].toCharArray();
            for (int j = 0; j < word.length - 1; j++) {
                if (Character.toUpperCase(word[j]) == Character.toUpperCase(findingLetter) &&
                        Character.toUpperCase(word[j + 1]) == Character.toUpperCase(replaceFrom)) {
                    if (Character.isUpperCase(word[j + 1])) {
                        word[j + 1] = Character.toUpperCase(replaceTo);
                    } else {
                        word[j + 1] = Character.toLowerCase(replaceTo);
                    }
                    words[i] = String.copyValueOf(word);
                }
            }
        }
        return String.join(SPACE, words);
    }

    // method for replacing specified length word with specified substring
    @Override
    public String replaceWordWithSubstring(String inputString, int wordLength, String subString)
            throws ValidationException {
        if (inputString == null || subString == null || wordLength < 0) {
            throw new ValidationException("Invalid input parameter");
        }
        String[] words = inputString.split(SPACE);
        for (int i = 0; i < words.length; i++) {
            char[] word = words[i].toCharArray();
            boolean isContainPunctuation = (word[word.length - 1] == COMA || word[word.length - 1] == DOT);
            if (word.length == wordLength && !isContainPunctuation) {
                word = subString.toCharArray();
                words[i] = String.copyValueOf(word);
            } else {
                if (word.length == wordLength + 1 && isContainPunctuation) {
                    char punctuation = word[word.length - 1];
                    String stringWithPunctuation = subString.concat(String.valueOf(punctuation));
                    word = stringWithPunctuation.toCharArray();
                    words[i] = String.copyValueOf(word);
                }
            }
        }
        return String.join(SPACE, words);
    }
}