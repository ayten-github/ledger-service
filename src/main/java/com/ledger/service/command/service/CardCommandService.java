package com.ledger.service.command.service;

import com.ledger.service.command.dao.dto.CardCommandDto;
import com.ledger.service.command.dao.entity.AccountEntity;
import com.ledger.service.command.dao.entity.CardEntity;
import com.ledger.service.command.dao.repository.CardRepository;
import com.ledger.service.command.exception.CardNotFoundException;
import com.ledger.service.command.mapper.CardCommandMapper;
import com.ledger.service.enums.CardTypeNetwork;
import com.ledger.service.validation.UpdateUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardCommandService {

    private final CardCommandMapper cardCommandMapper;
    private final CardRepository cardCommandRepository;
    private final AccountCommandService accountCommandService;
    private final UpdateUtils updateUtils;


    public CardEntity findById(Long id) {
        return cardCommandRepository.findById(id)
                .orElseThrow(() -> new CardNotFoundException("No card found with id: " + id));
    }

    public CardEntity findByCardNumber(String cardNumber) {
        return cardCommandRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new CardNotFoundException("No card found with the number " + cardNumber));
    }


    @Transactional
    public CardCommandDto createCard(CardCommandDto cardCommandDto, Long accountId) {
        CardEntity cardEntity = cardCommandMapper.toCardEntity(cardCommandDto);
        cardEntity.setCardTypeNetwork(CardTypeNetwork.detect(cardCommandDto.getCardNumber()));
        AccountEntity accountEntity = accountCommandService.findById(accountId);
        cardEntity.setAccount(accountEntity);
        return cardCommandMapper.toCardCommandDto(cardCommandRepository.save(cardEntity));
    }

    @Transactional
    public CardCommandDto updateCard(Long accountId,Long id, String[] property, Object... value) throws Exception {
        CardEntity cardEntity = findById(id);
        if(!cardEntity.getAccount().getId().equals(accountId)) {
            throw new NotMatchingDataException("Card does not belong to the given account");
        }
        cardEntity = (CardEntity) updateUtils.update(cardEntity,property,value);
        return cardCommandMapper.toCardCommandDto(cardCommandRepository.save(cardEntity));
    }

    @Transactional
    public CardCommandDto deleteCard(Long cardId,Long accountId) {
        CardEntity cardEntity = findById(cardId);
        AccountEntity accountEntity = accountCommandService.findById(accountId);

        if (!cardEntity.getAccount().getId().equals(accountId)) {
            throw new IllegalArgumentException("Card does not belong to the given account");
        }
        accountEntity.getCards().remove(cardEntity);

        cardCommandRepository.deleteById(cardId);
        return cardCommandMapper.toCardCommandDto(cardEntity);
    }

    @Scheduled(cron = "59 59 16 * * *", zone = "Asia/Baku")
    public void settleCards() {
        List<CardEntity> cards = cardCommandRepository.findAll().stream()
                .filter(cardEntity -> !cardEntity.getSettled().equals(Settled.YES))
                .toList();

        cards.forEach(cardEntity -> {
            cardEntity.setBalance(
                    cardEntity.getBalance().add(cardEntity.getBlockedAmount())
            );
            cardEntity.setBlockedAmount(BigDecimal.ZERO);
            cardEntity.setSettled(Settled.YES);
            cardCommandRepository.save(cardEntity);
        });

    }


}
