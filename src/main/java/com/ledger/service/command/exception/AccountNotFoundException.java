package com.ledger.service.command.exception;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(String message) {
        super(message);
    }
}
