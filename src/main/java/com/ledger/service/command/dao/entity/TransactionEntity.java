package com.ledger.service.command.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_account",nullable = false)
    private AccountEntity fromAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "to_account",nullable = false)
    private AccountEntity toAccount;

    @Column(name ="amount",nullable = false)
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_card",nullable = false)
    private CardEntity fromCard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="to_card",nullable = false)
    private CardEntity toCard;

    @CreationTimestamp
    @Column(name="timestamp",nullable = false)
    private final LocalDateTime  timestamp=LocalDateTime.now();

}
