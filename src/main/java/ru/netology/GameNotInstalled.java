package ru.netology;

public class GameNotInstalled extends RuntimeException {
    public GameNotInstalled (String msg) {
        super (msg);
    }
}
