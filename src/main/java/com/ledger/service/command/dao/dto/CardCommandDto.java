package com.ledger.service.command.dao.dto;

import com.ledger.service.enums.CardTypeFunction;
import com.ledger.service.enums.CardTypeNetwork;
import com.ledger.service.enums.Currency;
import com.ledger.service.enums.Settled;
import com.ledger.service.validation.OnCreate;
import com.ledger.service.validation.OnUpdate;
import com.ledger.service.validation.ValidCardNumber;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardCommandDto extends Dto {


    @NotEmpty(message = "Card holder name cannot be empty")
    private String cardHolderName;

    @NotNull(message = "Card number cannot be null", groups = {OnUpdate.class, OnCreate.class})
    @ValidCardNumber
    private String cardNumber;

    @NotEmpty(message = "Expiration date cannot be empty", groups = {OnCreate.class, OnUpdate.class})
    @Pattern(regexp = "^(0[1-9]|1[0-2])/([0-9]{2}|[0-9]{4})$",
            message = "Expiration date must be in MM/YY or MM/YYYY format")
    private String expirationDate;

    @NotEmpty(message = "CVV cannot be empty", groups = {OnCreate.class, OnUpdate.class})
    @Pattern(regexp = "\\d{3,4}", message = "CVV must be 3 or 4 digits")
    private String cvv;

    private CardTypeNetwork cardTypeNetwork;

    @NotNull(message = "Card type function cannot be null", groups = {OnCreate.class, OnUpdate.class})
    private CardTypeFunction cardTypeFunction;

    @NotNull(message = "Card balance cannot be null", groups = {OnCreate.class, OnUpdate.class})
    private BigDecimal balance = BigDecimal.ZERO;

    @NotNull(message = "Account cannot be null", groups = {OnCreate.class, OnUpdate.class})
    private AccountCommandDto account;

    @NotNull(message = "Blocked amount cannot be null", groups = {OnCreate.class, OnUpdate.class})
    private Settled settled = Settled.YES;

    @NotNull(message = "Currency cannot be null", groups = {OnCreate.class, OnUpdate.class})
    private Currency currency = Currency.AZN;

    @Valid
    private List<TransactionCommandDto> sentTransactions = new ArrayList<>();

    @Valid
    private List<TransactionCommandDto> receivedTransactions = new ArrayList<>();
}
