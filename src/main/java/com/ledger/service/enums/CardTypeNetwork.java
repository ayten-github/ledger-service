package com.ledger.service.enums;

import java.util.regex.Pattern;

public enum CardTypeNetwork {
    VISA("^4.*"),            // starts with 4, any length
    MASTERCARD("^5.*"),      // starts with 5, any length
    AMERICAN_EXPRESS("^3.*"),// starts with 3, any length
    UNION_PAY("^62.*"),      // starts with 62, any length
    UNKNOWN(".*");           // matches anything else (fallback)

    private Pattern pattern;

    CardTypeNetwork() {
        this.pattern = null;

    }

    CardTypeNetwork(String pattern) {
        this.pattern = Pattern.compile(pattern);
    }

    public static CardTypeNetwork detect(String cardNumber) {

        for (CardTypeNetwork cardType : CardTypeNetwork.values()) {
            if (null == cardType.pattern) continue;
            if (cardType.pattern.matcher(cardNumber).matches()) return cardType;
        }

        return UNKNOWN;
    }
}
