package com.suprun.stringoperation.service.impl;

import com.suprun.stringoperation.exception.ValidationException;
import com.suprun.stringoperation.service.StringRemover;

public class CharArrayStringRemoverImpl implements StringRemover {

    private static final String SPACE = " ";
    private static final String EMPTY_STRING = "";
    private static final char UTILITY_SYMBOL = '#';
    private static final String SPACE_SELECTOR = "[ ]+";
    private static final String UTILITY_SYMBOL_SELECTOR = "#+";
    private static final String CONSONANTS = "bcdfghjklmnpqrstvwxyzбвгджзйклмнпрстфхцчшщ";

    @Override
    public String removeWord(String inputString, int wordLength) throws ValidationException {
        if (inputString == null || wordLength < 0) {
            throw new ValidationException("Invalid input parameter");
        }
        String[] words = inputString.split(SPACE);
        for (int i = 0; i < words.length; i++) {
            char[] word = words[i].toCharArray();
            boolean isContainPunctuation = (word[word.length - 1] == ',' || word[word.length - 1] == '.');
            int newWordLength = isContainPunctuation ? wordLength+1 : wordLength;
            if (CONSONANTS.indexOf(Character.toLowerCase(word[0]))!= -1 && word.length == newWordLength) {
                words[i] = EMPTY_STRING;
            }
        }
        return String.join(" ", words).replaceAll(SPACE_SELECTOR, SPACE);
    }

    @Override
    public String removeNonLetterSymbols(String inputString) throws ValidationException {
        if (inputString == null) {
            throw new ValidationException("Invalid input parameter");
        }
        String[] words = inputString.split(SPACE);
        for (int i = 0; i< words.length;i++){
            char[] word = words[i].toCharArray();
            for(int j = 0; j < word.length; j++){
                if (!Character.isLetter(word[j]) && word[j] != '-'){
                    if (checkRange(word, j)){
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

    private boolean checkRange(char[] input, int index){
        if(index < 3 || index > input.length-3) {
            return false;
        }
        return Character.isLetter(input[index - 2]) && Character.isLetter(input[index - 1]) &&
                Character.isLetter(input[index + 1]) && Character.isLetter(input[index + 2]);
    }
}
