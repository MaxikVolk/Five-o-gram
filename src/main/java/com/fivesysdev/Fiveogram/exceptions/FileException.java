package com.fivesysdev.Fiveogram.exceptions;

public class FileException extends RuntimeException{
    @Override
    public String getMessage() {
        return "File exception";
    }
}
