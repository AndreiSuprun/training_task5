package com.suprun.stringoperation.service.impl;

import com.suprun.stringoperation.exception.ValidationException;
import com.suprun.stringoperation.service.StringRemover;

// class is used for removal string operations with string class methods
public class StringRemoverImpl implements StringRemover {

    private static final String SPACE = " ";
    private static final String EMPTY_STRING = "";
    private static final String COMA = ",";
    private static final String DOT = ".";
    private static final char HYPHEN = '-';
    private static final String SPACE_SELECTOR = "[ ]+";
    private static final String CONSONANTS = "bcdfghjklmnpqrstvwxyzбвгджзйклмнпрстфхцчшщ";

    // method for removing specified length words
    @Override
    public String removeWordBeginConsonant(String inputString, int wordLength) throws ValidationException {
        if (inputString == null || wordLength < 0) {
            throw new ValidationException("Invalid input parameter");
        }
        String[] words = inputString.split(SPACE);
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            boolean isContainPunctuation = (word.contains(COMA) || word.contains(DOT));
            int newWordLength = isContainPunctuation ? wordLength + 1 : wordLength;
            if (CONSONANTS.indexOf(Character.toLowerCase(word.charAt(0))) != -1 && word.length() == newWordLength) {
                words[i] = EMPTY_STRING;
            }
        }
        return String.join(SPACE, words).replaceAll(SPACE_SELECTOR, SPACE);
    }

    //method for removing non letter symbols from string
    @Override
    public String removeNonLetterSymbols(String inputString) throws ValidationException {
        if (inputString == null) {
            throw new ValidationException("Invalid input parameter");
        }
        StringBuilder word;
        String[] words = inputString.split(SPACE);
        for (int i = 0; i < words.length; i++) {
            word = new StringBuilder(words[i]);
            for (int j = 0; j < word.length(); j++) {
                if (!Character.isLetter(word.charAt(j)) && word.charAt(j) != HYPHEN) {
                    if (isBetweenSequences(word, j)) {
                        word.setCharAt(j, SPACE.charAt(0));
                    } else {
                        word.deleteCharAt(j);
                        j--;
                    }
                }
            }
            words[i] = word.toString();
        }
        return String.join(SPACE, words);
    }

    /*method for checking if non letter symbol is between sequences of letters,
    sequence contains two or more letters, so symbol index must be in range (3, length-3)
     */
    private boolean isBetweenSequences(StringBuilder input, int index) {
        if (index < 3 || index > input.length() - 3) {
            return false;
        }
        return Character.isLetter(input.charAt(index - 2)) && Character.isLetter(input.charAt(index - 1)) &&
                Character.isLetter(input.charAt(index + 1)) && Character.isLetter(input.charAt(index + 2));
    }
}