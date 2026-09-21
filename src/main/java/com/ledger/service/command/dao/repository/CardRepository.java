package com.ledger.service.command.dao.repository;

import com.ledger.service.command.dao.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<CardEntity,Long> {
    Optional<CardEntity> findByCardNumber(String cardNumber);
}
