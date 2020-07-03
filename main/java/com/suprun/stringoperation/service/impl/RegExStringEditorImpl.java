package com.suprun.stringoperation.service.impl;

import com.suprun.stringoperation.exception.ValidationException;
import com.suprun.stringoperation.service.StringEditor;

import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// class is used for edit string with regular expressions
public class RegExStringEditorImpl implements StringEditor {

    private static final String REPLACE_WORD_SELECTOR = "\\b[\\p{L}['-]]{%d}\\b";
    private static final String LETTER_BY_INDEX_SELECTOR = "[\\p{L}['-]]{%d,}";
    private static final String NEXT_LETTER_SELECTOR = "(?i)%c%c";

    // method for letter in word by index
    @Override
    public String replaceLetterByIndex(String inputString, int index, char letter) throws ValidationException {
        if (inputString == null || index < 0) {
            throw new ValidationException("Invalid input parameter");
        }
        String wordSelector = String.format(LETTER_BY_INDEX_SELECTOR, index);
        Pattern pattern = Pattern.compile(wordSelector);
        Matcher matcher = pattern.matcher(inputString);
        Function<MatchResult, String> replacer = new Function<MatchResult, String>() {
            @Override
            public String apply(MatchResult matchResult) {
                StringBuilder word = new StringBuilder(matchResult.group());
                String output = word.replace(index-1, index, String.valueOf(letter)).toString();
                return output;
            }
        };
        return matcher.replaceAll(replacer);
    }

    // method for replacing next to specified letter
    @Override
    public String replaceNextLetter(String inputString, char findingLetter, char replaceFrom, char replaceTo)
            throws ValidationException {
        if (inputString == null) {
            throw new ValidationException("Invalid input parameter");
        }
        String regEx = String.format(NEXT_LETTER_SELECTOR, findingLetter, replaceFrom);
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(inputString);
        Function<MatchResult, String> replacer = new Function<MatchResult, String>() {
            char replacer = replaceTo;
            @Override
            public String apply(MatchResult matchResult) {
                String input = matchResult.group();
                boolean isLowerCase = Character.isLowerCase(input.charAt(1));
                replacer = isLowerCase ? Character.toLowerCase(replacer) : Character.toUpperCase(replacer);
                return input.replace(input.charAt(1), replacer);
            }
        };
        return matcher.replaceAll(replacer);
    }

    // method for replacing specified length word with specified substring
    @Override
    public String replaceWordWithSubstring(String inputString, int wordLength, String subString)
            throws ValidationException {
        if (inputString == null || subString == null || wordLength < 0) {
            throw new ValidationException("Invalid input parameter");
        }
        String regEx = String.format(REPLACE_WORD_SELECTOR, wordLength);
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(inputString);
        Function<MatchResult, String> replacer = new Function<MatchResult, String>() {
            @Override
            public String apply(MatchResult matchResult) {
                String input = matchResult.group();
                return input.replace(input, subString);
            }
        };
        return matcher.replaceAll(replacer);
    }
}