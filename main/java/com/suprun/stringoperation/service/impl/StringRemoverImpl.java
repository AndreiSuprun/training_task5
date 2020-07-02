package com.suprun.stringoperation.service.impl;

import com.suprun.stringoperation.exception.ValidationException;
import com.suprun.stringoperation.service.StringRemover;

public class StringRemoverImpl implements StringRemover {

    private static final String SPACE = " ";
    private static final String EMPTY_STRING = "";
    private static final String SPACE_SELECTOR = "[ ]+";
    private static final String CONSONANTS = "bcdfghjklmnpqrstvwxyzбвгджзйклмнпрстфхцчшщ";

    @Override
    public String removeWord(String inputString, int wordLength) throws ValidationException {
        if (inputString == null || wordLength < 0) {
            throw new ValidationException("Invalid input parameter");
        }
        String[] words = inputString.split(SPACE);
        for (int i = 0; i < words.length; i++){
            String word = words[i];
            boolean isContainPunctuation = (word.contains(",") || word.contains("."));
            int newWordLength = isContainPunctuation ? wordLength+1 : wordLength;
            if (CONSONANTS.indexOf(Character.toLowerCase(word.charAt(0))) != -1 && word.length() == newWordLength) {
                words[i] = EMPTY_STRING;
            }
        }
        return String.join(SPACE, words).replaceAll(SPACE_SELECTOR, SPACE);
    }

    @Override
    public String removeNonLetterSymbols(String inputString) throws ValidationException {
        if (inputString == null) {
            throw new ValidationException("Invalid input parameter");
        }
        StringBuilder word;
        String[] words = inputString.split(SPACE);
        for (int i = 0; i< words.length;i++){
            word = new StringBuilder(words[i]);
            for(int j = 0; j < word.length(); j++){
                if (!Character.isLetter(word.charAt(j)) && word.charAt(j) != '-'){
                    if (checkRange(word, j)){
                        word.setCharAt(j, SPACE.charAt(0));
                    } else {
                        word.deleteCharAt(j);
                        j--;
                    }
                }
            }
            words[i] = word.toString(); }
        return String.join(SPACE, words);
    }

    private boolean checkRange(StringBuilder input, int index){
        if(index < 3 || index > input.length()-3) {
            return false;
        }
        return Character.isLetter(input.charAt(index - 2)) && Character.isLetter(input.charAt(index - 1)) &&
                Character.isLetter(input.charAt(index + 1)) && Character.isLetter(input.charAt(index + 2));
    }
}