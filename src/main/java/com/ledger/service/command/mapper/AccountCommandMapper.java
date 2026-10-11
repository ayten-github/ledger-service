package com.ledger.service.command.mapper;

import com.ledger.service.command.dao.dto.AccountCommandDto;
import com.ledger.service.command.dao.entity.AccountEntity;
import jakarta.transaction.Transactional;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AccountCommandMapper {

    AccountCommandDto toAccountCommandDto(AccountEntity accountEntity);


 @Transactional
    AccountEntity toAccountEntity(AccountCommandDto accountCommandDto);
}
