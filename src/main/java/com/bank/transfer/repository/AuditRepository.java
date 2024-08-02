package com.bank.transfer.repository;

import com.bank.transfer.entity.Audit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Репозиторий, использующийся при обмене данных по запросу банковского аудита.
 * Реализуется на основе {@link org.springframework.data.jpa.repository.JpaRepository}
 */
@Repository
public interface AuditRepository extends JpaRepository<Audit, Long> {
    List<Audit> findByEntityType(String entityType);
}
