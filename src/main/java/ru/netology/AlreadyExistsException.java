package ru.netology;

public class AlreadyExistsException extends Exception {

    public AlreadyExistsException(String message) {
        super(message);
    }
}