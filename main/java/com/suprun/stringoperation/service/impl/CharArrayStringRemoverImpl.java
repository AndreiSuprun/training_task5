package com.suprun.stringoperation.service.impl;

import com.suprun.stringoperation.exception.ValidationException;
import com.suprun.stringoperation.service.StringRemover;

// class is used for removal string operations with char array operations
public class CharArrayStringRemoverImpl implements StringRemover {

    private static final String SPACE = " ";
    private static final String EMPTY_STRING = "";
    private static final char UTILITY_SYMBOL = '#';
    private static final char DOT = '.';
    private static final char COMA = ',';
    private static final char HYPHEN = '-';
    private static final String SPACE_SELECTOR = "[ ]+";
    private static final String UTILITY_SYMBOL_SELECTOR = "#+";
    private static final String CONSONANTS = "bcdfghjklmnpqrstvwxyzбвгджзйклмнпрстфхцчшщ";

    // method for removing specified length words
    @Override
    public String removeWordBeginConsonant(String inputString, int wordLength) throws ValidationException {
        if (inputString == null || wordLength < 0) {
            throw new ValidationException("Invalid input parameter");
        }
        String[] words = inputString.split(SPACE);
        for (int i = 0; i < words.length; i++) {
            char[] word = words[i].toCharArray();
            boolean isContainPunctuation = (word[word.length - 1] == COMA || word[word.length - 1] == DOT);
            int newWordLength = isContainPunctuation ? wordLength+1 : wordLength;
            if (CONSONANTS.indexOf(Character.toLowerCase(word[0]))!= -1 && word.length == newWordLength) {
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
        String[] words = inputString.split(SPACE);
        for (int i = 0; i< words.length;i++){
            char[] word = words[i].toCharArray();
            for(int j = 0; j < word.length; j++){
                if (!Character.isLetter(word[j]) && word[j] != HYPHEN){
                    if (isBetweenLetterSequences(word, j)){
                        word[j] = SPACE.charAt(0);
                    } else {
                        word[j] = UTILITY_SYMBOL;
                    }
                }
            }
            words[i] = String.valueOf(word);
        }
        return String.join(SPACE, words).replaceAll(UTILITY_SYMBOL_SELECTOR, EMPTY_STRING);
    }

    /* method for checking if non letter symbol is between sequences of letters,
     sequence contains two or more letters, so symbol index must be in range (3, length-3)
    */
    private boolean isBetweenLetterSequences(char[] input, int index){
        if(index < 3 || index > input.length-3) {
            return false;
        }
        return Character.isLetter(input[index - 2]) && Character.isLetter(input[index - 1]) &&
                Character.isLetter(input[index + 1]) && Character.isLetter(input[index + 2]);
    }
}