package com.ledger.service.command.dao.repository;

import com.ledger.service.command.dao.entity.AccountEntity;
import com.ledger.service.command.dao.entity.TransactionEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity,Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM TransactionEntity t WHERE t.fromAccount = :account OR t.toAccount = :account")
    void deleteByFromAccountOrToAccount(@Param("account") AccountEntity account);


    Object findByFromAccountId(Long fromAccountId);

    List<TransactionEntity> findByToAccountId(Long toAccountId);

}
