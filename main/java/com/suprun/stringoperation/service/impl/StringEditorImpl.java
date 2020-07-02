package com.suprun.stringoperation.service.impl;

import com.suprun.stringoperation.exception.ValidationException;
import com.suprun.stringoperation.service.StringEditor;

public class StringEditorImpl implements StringEditor {

    private static final String SPACE = " ";

    @Override
    public String replaceLetterByIndex(String inputString, int index, char replacingChar) throws ValidationException {
        if (inputString == null || index < 0) {
            throw new ValidationException("Invalid input parameter");
        }
        StringBuilder sb = new StringBuilder();
        for (String word : inputString.split(SPACE)){
            if (word.length() >= index){
                sb.append(word, 0, index-1).append(replacingChar).append(word,index, word.length());
            } else { sb.append(word);}
            sb.append(SPACE);
        }
        return sb.toString().trim();
    }

    @Override
    public String replaceNextLetter(String input, char find, char replaceFrom, char replaceTo) throws ValidationException {
        if (input == null) {
            throw new ValidationException("Invalid input parameter");
        }
        StringBuilder stringBuilder = new StringBuilder();
        String[] words = input.split(SPACE);
        char findUpperCase = Character.toUpperCase(find);
        char replaceFromUpperCase = Character.toUpperCase(replaceFrom);
        for (int i = 0; i< words.length; i++){
            String word = words[i];
            String wordUpperCase = word.toUpperCase();
            if (wordUpperCase.indexOf(findUpperCase) > -1 && wordUpperCase.indexOf(findUpperCase) < word.length()-1){
                int index = wordUpperCase.indexOf(findUpperCase);
                while (index!=-1 && wordUpperCase.charAt(index+1) == replaceFromUpperCase){
                    if (Character.isUpperCase(word.charAt(index+1))) {
                    stringBuilder.append(word, 0, index+1).append(Character.toUpperCase(replaceTo)).
                            append(word, index+2, word.length());
                    } else {
                        stringBuilder.append(word, 0, index+1).append(Character.toLowerCase(replaceTo)).
                            append(word, index+2, word.length());
                    }
                    word = stringBuilder.toString();
                    index = wordUpperCase.indexOf(findUpperCase, index+1);
                    stringBuilder.setLength(0);
                }
            }
            words[i] = word;
        }
        return String.join(SPACE, words);
    }

    @Override
    public String replaceWordWithSubstring(String inputString, int wordLength, String subString) throws ValidationException {
        if (inputString == null || subString == null || wordLength < 0) {
            throw new ValidationException("Invalid input parameter");
        }
        String[] words = inputString.split(SPACE);
        for (int i = 0; i< words.length;i++){
            String word = words[i];
            boolean isContainPunctuation = (word.contains(",") || word.contains("."));
            if (word.length() == wordLength && !isContainPunctuation){
                word = word.replace(word, subString);
            } else {
                if (word.length() == wordLength+1 && isContainPunctuation) {
                    word = word.replace(word.substring(0, wordLength), subString);
                }
            }
            words[i] = word;
        }
        return String.join(SPACE, words);
    }
}
