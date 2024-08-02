package com.bank.transfer.service;

import com.bank.transfer.entity.PhoneTransfer;
import java.util.List;
public interface PhoneTransferService {
    void add(PhoneTransfer phoneTransfer);

    void delete(PhoneTransfer phoneTransfer);

    List<PhoneTransfer> getPhoneTransfersByPhoneNumber(Long phoneNumber);
}
