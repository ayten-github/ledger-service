package com.ledger.service.command.mapper;

import com.ledger.service.command.dao.dto.TransactionCommandDto;
import com.ledger.service.command.dao.entity.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionCommandMapper {

    @Mapping(target = "fromAccount", ignore = true)
    @Mapping(target = "toAccount", ignore = true)
    @Mapping(target = "toCard", ignore = true)
    @Mapping(target = "fromCard", ignore = true)
    TransactionCommandDto toTransactionCommandDto(TransactionEntity transactionEntity);

    @Mapping(target = "fromAccount", ignore = true)
    @Mapping(target = "toAccount", ignore = true)
    @Mapping(target = "toCard", ignore = true)
    @Mapping(target = "fromCard", ignore = true)
    TransactionEntity toTransactionEntity(TransactionCommandDto transactionCommandDto);

}
