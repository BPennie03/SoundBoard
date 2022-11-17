package com.tomfoolery.soundboard.exceptions;

public class OperatingSystemNotFoundException extends Exception {
    public OperatingSystemNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}