package com.ledger.service.command.dao.dto;

import com.ledger.service.validation.OnCreate;
import com.ledger.service.validation.OnUpdate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
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
public class AccountCommandDto extends Dto {


    @NotNull(message = "Username cannot be null",groups = {OnCreate.class,OnUpdate.class})
    private String username;

    @NotNull(message = "Customer name cannot be null",groups = {OnCreate.class, OnUpdate.class})
    private String customerName;

    @NotNull(message = "Password should be provided",groups = {OnCreate.class,OnUpdate.class})
    private String password;

    @Email(message = "Invalid email for creation",groups = {OnCreate.class,OnUpdate.class})
    private String email;

    @NotNull(message = "Account balance cannot be null",groups = {OnCreate.class,OnUpdate.class})
    private BigDecimal balance = BigDecimal.ZERO;

    @Valid
    private List<TransactionCommandDto> sentTransactions = new ArrayList<>();

    @Valid
    private List<TransactionCommandDto> receivedTransactions = new ArrayList<>();

    @Valid
    private List<CardCommandDto> cards;

}
