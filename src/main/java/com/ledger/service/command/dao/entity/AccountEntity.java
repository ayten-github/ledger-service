package com.ledger.service.command.dao.entity;

import java.math.BigDecimal;
import java.util.List;

public class AccountEntity extends BaseEntity{

    private String username;
    private String customerName;
    private String password;
    private String email;
    private BigDecimal balance;
    private List<CardEntity> cards;
    private List<TransactionEntity> sendTransactions;
    private List<TransactionEntity> receivedTransactions;

}
