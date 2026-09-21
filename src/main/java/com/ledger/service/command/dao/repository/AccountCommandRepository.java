package com.ledger.service.command.dao.repository;

import com.ledger.service.command.dao.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountCommandRepository extends JpaRepository<AccountEntity,Long>{

    void removeById(Long id);

}
