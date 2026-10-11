package com.ledger.service.command.exception;

public class NotMatchingDataException extends RuntimeException {
    public NotMatchingDataException(String message) {
        super(message);
    }
}
