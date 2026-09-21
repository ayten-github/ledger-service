package com.ledger.service.command.dao.dto;

import com.ledger.service.validation.OnCreate;
import com.ledger.service.validation.OnUpdate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCommandDto extends Dto {


    @Valid
    @NotNull(message = "Sender account cannot be null",groups = {OnCreate.class, OnUpdate.class})
    private AccountCommandDto fromAccount;

    @Valid
    @NotNull(message = "Receiver account cannot be null",groups = {OnCreate.class,OnUpdate.class})
    private AccountCommandDto toAccount;

    @NotNull(message = "Amount cannot be null",groups = {OnCreate.class,OnUpdate.class})
    private BigDecimal amount;

    private LocalDateTime timestamp = LocalDateTime.now();

    @Valid
    @NotNull(message = "Sender card cannot be null",groups = {OnCreate.class,OnUpdate.class})
    private CardCommandDto fromCard;

    @Valid
    @NotNull(message = "Receiver card cannot be null",groups = {OnCreate.class,OnUpdate.class})
    private CardCommandDto toCard;
}
