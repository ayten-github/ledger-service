package com.ledger.service.command.exception;

public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException(String message) {
        super(message);
    }
}
