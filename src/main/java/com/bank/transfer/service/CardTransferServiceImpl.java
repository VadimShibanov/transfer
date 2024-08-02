package com.bank.transfer.service;

import com.bank.transfer.entity.CardTransfer;
import com.bank.transfer.repository.CardTransferRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Сервис оплат с использованием банковских карт
 */

@Service
@AllArgsConstructor
public class CardTransferServiceImpl implements CardTransferService {

    private final CardTransferRepository cardTransferRepository;

    /**
     * Метод добавляет один платеж с использованием банковской карты
     */
    @Override
    public void add(CardTransfer cardTransfer) {
        cardTransferRepository.save(cardTransfer);
    }

    @Override
    public void delete(CardTransfer cardTransfer) {
        cardTransferRepository.delete(cardTransfer);
    }

    /**
     * Метод возвращает список всех платежей, выполненных с использованием данной банковской карты
     */
    @Override
    public List<CardTransfer> getCardTransfersByCardNumber(Long cardNumber) {
        return cardTransferRepository.findByCardNumber(cardNumber);
    }
}
