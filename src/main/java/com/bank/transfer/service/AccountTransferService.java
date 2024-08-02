package com.bank.transfer.service;

import com.bank.transfer.entity.AccountTransfer;
import java.util.List;

public interface AccountTransferService {
    void add(AccountTransfer accountTransfer);

    void deleteAllWithThisAccountNumber(Long accountNumber);

    List<AccountTransfer> getAccountTransfersByAccountNumber(Long accountNumber);
    boolean checkIfExistsByAccountNumber(Long accountNumber);
}
