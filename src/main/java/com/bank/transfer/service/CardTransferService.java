package com.bank.transfer.service;

import com.bank.transfer.entity.CardTransfer;
import java.util.List;

public interface CardTransferService {
    void add (CardTransfer cardTransfer);

    void delete(CardTransfer cardTransfer);

    List<CardTransfer> getCardTransfersByCardNumber(Long cardNumber);
}
