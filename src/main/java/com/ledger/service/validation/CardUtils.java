package com.ledger.service.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.NotNull;

public class CardUtils implements ConstraintValidator<ValidCardNumber, String> {

        private static final String CARD_NUMBER_PATTERN = "\\d{13,19}";

        @Override
        public boolean isValid(@NotNull String cardNumber, ConstraintValidatorContext context) {
            if (cardNumber == null) {
                // Null kontrolü ayrı @NotNull anotasyonuna bırakılır
                return true;
            }

            if (!cardNumber.matches(CARD_NUMBER_PATTERN)) {
                return false;
            }

            return isLuhnValid(cardNumber);
        }

        private boolean isLuhnValid(String cardNumber) {
            int sum = 0;
            boolean alternate = false;

            for (int i = cardNumber.length() - 1; i >= 0; i--) {
                int digit = cardNumber.charAt(i) - '0';

                if (alternate) {
                    digit *= 2;
                    if (digit > 9) {
                        digit -= 9;
                    }
                }

                sum += digit;
                alternate = !alternate;
            }

            return sum % 10 == 0;
        }
}
