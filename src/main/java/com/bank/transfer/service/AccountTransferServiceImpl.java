package com.bank.transfer.service;

import com.bank.transfer.entity.AccountTransfer;
import com.bank.transfer.repository.AccountTransferRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Сервис оплат с использованием банковских счетов
 */
@Service
@AllArgsConstructor
public class AccountTransferServiceImpl implements AccountTransferService {
    private final AccountTransferRepository accountTransferRepository;
    @Override
    public void add(AccountTransfer accountTransfer) {
        accountTransferRepository.save(accountTransfer);
    }
    @Override
    public void deleteAllWithThisAccountNumber(Long accountNumber) {

        List<AccountTransfer> list = accountTransferRepository.findByAccountNumber(accountNumber);

        for (AccountTransfer acc: list) {
            accountTransferRepository.delete(acc);
        }
    }
    @Override
    public List<AccountTransfer> getAccountTransfersByAccountNumber(Long accountNumber) {
        return accountTransferRepository.findByAccountNumber(accountNumber);
    }
    @Override
    public boolean checkIfExistsByAccountNumber(Long accountNumber) {
        return accountTransferRepository.existsByAccountNumber(accountNumber);
    }
}
