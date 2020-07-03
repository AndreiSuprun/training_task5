package com.suprun.stringoperation.service.impl;

import com.suprun.stringoperation.exception.ValidationException;
import com.suprun.stringoperation.service.StringRemover;

import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// class is used for removal string operations with regular expressions
public class RegExStringRemoverImpl implements StringRemover {

    private static final String SPACE = " ";
    private static final String EMPTY_STRING = "";
    private static final String BEGIN_WITH_CONSONANTS_SELECTOR =
            "\\b(?i:[bcdfghjklmnpqrstvwxyzбвгджзйклмнпрстфхцчшщ])[\\p{L}['-]]{%d}\\b[,.]*\\s*";
    private static final String WORD_WITH_NON_LETTER_SYMBOL = "(?<=\\p{L}{2})\\P{L}(?=\\p{L}{2})";
    private static final String NON_LETTER_SYMBOL = "[\\P{L}&&[^-\\s]]+";

    // method for removing specified length words
    @Override
    public String removeWordBeginConsonant(String inputString, int wordLength) throws ValidationException {
        if (inputString == null || wordLength < 0) {
            throw new ValidationException("Invalid input parameter");
        }
        String wordStartWithConsonant = String.format(BEGIN_WITH_CONSONANTS_SELECTOR, wordLength-1);
        return inputString.replaceAll(wordStartWithConsonant, EMPTY_STRING);
    }

    //method for removing non letter symbols from string
    @Override
    public String removeNonLetterSymbols(String inputString) throws ValidationException {
        if (inputString == null) {
            throw new ValidationException("Invalid input parameter");
        }
        Pattern pattern = Pattern.compile(WORD_WITH_NON_LETTER_SYMBOL);
        Matcher matcher = pattern.matcher(inputString);
        Function<MatchResult, String> replacer = new Function<MatchResult, String>() {
            @Override
            public String apply(MatchResult matchResult) {
                String input = matchResult.group();
                return input.replaceAll(NON_LETTER_SYMBOL, SPACE);
            }
        };
        return matcher.replaceAll(replacer).replaceAll(NON_LETTER_SYMBOL, EMPTY_STRING);
    }
}