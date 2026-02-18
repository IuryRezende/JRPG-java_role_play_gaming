package org.example.Exceptions;

public class InsufficientMana extends RuntimeException {
    public InsufficientMana(String message) {
        super(message);
    }
}
