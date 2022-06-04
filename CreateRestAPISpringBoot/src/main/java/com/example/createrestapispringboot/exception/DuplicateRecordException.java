package com.example.createrestapispringboot.exception;

public class DuplicateRecordException extends Exception{
    public DuplicateRecordException(String message) {
        super(message);
    }
}
