package com.ledger.service.command.service;

import com.ledger.service.command.dao.dto.AccountCommandDto;
import com.ledger.service.command.dao.entity.AccountEntity;
import com.ledger.service.command.dao.repository.AccountCommandRepository;
import com.ledger.service.command.dao.repository.TransactionRepository;
import com.ledger.service.command.mapper.AccountCommandMapper;
import com.ledger.service.validation.UpdateUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLEngineResult;
import javax.security.auth.login.AccountNotFoundException;

@Service
@RequiredArgsConstructor
public class AccountCommandService {

    private final AccountCommandMapper accountCommandMapper;
    private final AccountCommandRepository accountCommandRepository;
    private final TransactionRepository transactionCommandRepository;
    private final UpdateUtils updateUtils;


    @Transactional
    public AccountEntity findById(Long id) {
        return accountCommandRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("No account found"));
    }

    @Transactional
    public AccountCommandDto createAccount(AccountCommandDto accountCommandDto) {
        AccountEntity accountEntity = accountCommandMapper.toAccountEntity(accountCommandDto);

        return accountCommandMapper.toAccountCommandDto(accountCommandRepository.save(accountEntity));
    }

    @Transactional
    public AccountCommandDto updateAccount(Long id, String[] property, Object... value) throws Exception {
        AccountEntity accountEntity = findById(id);
        accountEntity = (AccountEntity) updateUtils.update(accountEntity,property,value);

        AccountEntity saved = accountCommandRepository.save(accountEntity);
        return accountCommandMapper.toAccountCommandDto(saved);
    }


    @Transactional
    public AccountCommandDto deleteAccount(Long id) {
        AccountEntity accountEntity = findById(id);
        transactionCommandRepository.deleteByFromAccountOrToAccount(accountEntity);
        accountCommandRepository.deleteById(id);
        return accountCommandMapper.toAccountCommandDto(accountEntity);
    }

    @Transactional
    public AccountCommandDto closeAccount(Long id) {
        AccountEntity accountEntity = findById(id);
        accountEntity.setStatus(SSLEngineResult.Status.CLOSED);
        return accountCommandMapper.toAccountCommandDto(accountCommandRepository.save(accountEntity));
    }
}