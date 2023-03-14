package com.tomfoolery.soundboard.exceptions;

/**
 * Custom exception for if the user's identified operating system is not found
 */
public class OperatingSystemNotFoundException extends Exception {
    public OperatingSystemNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}