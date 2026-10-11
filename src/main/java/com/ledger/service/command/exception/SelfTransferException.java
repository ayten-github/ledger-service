package com.ledger.service.command.exception;

public class SelfTransferException extends RuntimeException {
    public SelfTransferException(String message) {
        super(message);
    }
}

