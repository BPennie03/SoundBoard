package com.tomfoolery.soundboard.exceptions;

public class NoSuchEntryException extends Exception{
    public NoSuchEntryException(String errorMessage) {
        super(errorMessage);
    }
}
