package com.ledger.service.enums;

public enum Settled {
    NO_SENDER("no_sender"),
    NO_RECEIVER("no_receiver"),
    YES("yes");

    private final String value;

    Settled(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
