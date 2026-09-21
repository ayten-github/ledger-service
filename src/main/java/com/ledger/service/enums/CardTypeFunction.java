package com.ledger.service.enums;

public enum CardTypeFunction {
    CREDIT("credit"),
    DEBIT("debit"),
    VIRTUAL("virtual"),
    CORPORATE("corporate"),
    STUDENT("student");

    private final String value;

    CardTypeFunction(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
