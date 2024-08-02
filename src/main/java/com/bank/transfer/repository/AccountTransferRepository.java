package com.bank.transfer.repository;

import com.bank.transfer.entity.AccountTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Репозиторий оплаты с использованием банковского счета,
 * реализующийся на основе {@link org.springframework.data.jpa.repository.JpaRepository}
 */
@Repository
public interface AccountTransferRepository extends JpaRepository<AccountTransfer,Long> {
    List<AccountTransfer> findByAccountNumber(Long accountNumber);
    boolean existsByAccountNumber(Long accountNumber);
}
