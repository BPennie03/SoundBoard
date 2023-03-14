package com.tomfoolery.soundboard.exceptions;

/**
 * Custom exception for if a value at a specific key in the Json is not found
 */
public class NoSuchEntryException extends Exception{
    public NoSuchEntryException(String errorMessage) {
        super(errorMessage);
    }
}
