package com.bank.transfer.service;

import com.bank.transfer.entity.PhoneTransfer;
import com.bank.transfer.repository.PhoneTransferRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Сервис оплат с использованием мобильных устройств
 */

@Service
@AllArgsConstructor
public class PhoneTransferServiceImpl implements PhoneTransferService {
    public final PhoneTransferRepository phoneTransferRepository;

    /**
     * Метод добавляет один платеж с использованием мобильного устройства
     */

    @Override
    public void add(PhoneTransfer phoneTransfer) {
        phoneTransferRepository.save(phoneTransfer);
    }

    @Override
    public void delete(PhoneTransfer phoneTransfer) {
        phoneTransferRepository.delete(phoneTransfer);
    }

    /**
     * Метод возвращает список всех платежей, выполненных с мобильного устройства,
     * привязанного к данному номеру сотового оператора
     */
    @Override
    public List<PhoneTransfer> getPhoneTransfersByPhoneNumber(Long phoneNumber) {
        return phoneTransferRepository.findByPhoneNumber(phoneNumber);
    }
}
