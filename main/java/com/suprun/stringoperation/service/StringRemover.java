package com.suprun.stringoperation.service;

import com.suprun.stringoperation.exception.ValidationException;

public interface StringRemover {

   String removeWordBeginConsonant(String inputString, int wordLength) throws ValidationException;

   String removeNonLetterSymbols(String inputString) throws ValidationException;
}
