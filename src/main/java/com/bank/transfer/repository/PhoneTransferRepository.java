package com.bank.transfer.repository;

import com.bank.transfer.entity.PhoneTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Репозиторий оплаты с использованием банковского счета,
 * реализующийся на основе {@link org.springframework.data.jpa.repository.JpaRepository}
 */
@Repository
public interface PhoneTransferRepository extends JpaRepository<PhoneTransfer,Long> {
    List<PhoneTransfer> findByPhoneNumber(Long phoneNumber);
}
