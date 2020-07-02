package com.suprun.stringoperation.service.impl;

import com.suprun.stringoperation.exception.ValidationException;
import com.suprun.stringoperation.service.StringEditor;

import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExStringEditorImpl implements StringEditor {

    private static final String EMPTY_STRING = "";
    private static final String WORD_SYMBOLS = "[\\p{L}['-]]";
    private static final String REPLACE_WORD_BEGIN = "\\b"+WORD_SYMBOLS+"{";
    private static final String REPLACE_WORD_END = "}\\b";
    private static final String REPLACE_LETTER_BEGIN = WORD_SYMBOLS+"{";
    private static final String REPLACE_LETTER_END = ",}";
    private static final String CASE_INSENSITIVE = "(?i)";

    @Override
    public String replaceLetterByIndex(String inputString, int index, char letter) throws ValidationException {
        if (inputString == null || index < 0) {
            throw new ValidationException("Invalid input parameter");
        }
        String wordSelector = REPLACE_LETTER_BEGIN+index+REPLACE_LETTER_END;
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

    @Override
    public String replaceNextLetter(String inputString, char find, char replaceFrom, char replaceTo)
            throws ValidationException {
        if (inputString == null) {
            throw new ValidationException("Invalid input parameter");
        }
        String regEx = CASE_INSENSITIVE+find+replaceFrom+EMPTY_STRING;
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

    @Override
    public String replaceWordWithSubstring(String inputString, int wordLength, String subString)
            throws ValidationException {
        if (inputString == null || subString == null || wordLength < 0) {
            throw new ValidationException("Invalid input parameter");
        }
        String regEx = REPLACE_WORD_BEGIN+wordLength+REPLACE_WORD_END;
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