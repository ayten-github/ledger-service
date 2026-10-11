package com.ledger.service.command.mapper;

import com.ledger.service.command.dao.dto.CardCommandDto;
import com.ledger.service.command.dao.entity.CardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CardCommandMapper {

    @Mapping(target = "account", ignore = true)
    CardCommandDto toCardCommandDto(CardEntity cardEntity);

    @Mapping(target = "account", ignore = true)
    CardEntity toCardEntity(CardCommandDto cardCommandDto);
}
